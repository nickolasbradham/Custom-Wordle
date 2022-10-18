package nbradham.wordle;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * Handles core game setup code.
 * 
 * @author Nickolas Bradham
 *
 */
final class Wordle {

	private final JFrame frame = new JFrame("Custom Wordle");
	private final Guess[] guesses;
	private byte curGuess = 0;

	/**
	 * Creates the GUI.
	 * 
	 * @param length   The length of the word.
	 * @param guessNum Number of guesses to allow.
	 */
	private Wordle(byte length, byte guessNum) {
		guesses = new Guess[guessNum];
		for (byte n = 0; n < guesses.length; n++)
			guesses[n] = new Guess(n, length);

		Scanner scan = new Scanner(Wordle.class.getResourceAsStream("/words.txt"));
		ArrayList<String> words = new ArrayList<>();
		while (scan.hasNext()) {
			String s = scan.nextLine();
			if (s.length() == length)
				words.add(s);
		}

		SwingUtilities.invokeLater(() -> {
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			if (words.size() == 0) {
				JOptionPane.showMessageDialog(frame, String.format("No words found of length: %d", length));
				frame.dispose();
				curGuess = -1;
				return;
			}

			String word = words.get(new Random().nextInt(words.size()));

			frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

			for (Guess g : guesses)
				frame.add(g);

			JButton guess = new JButton("Guess");
			guess.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String g = guesses[curGuess].getText();
					if (g.length() < length) {
						JOptionPane.showMessageDialog(frame, "Guess must be complete.");
						return;
					}

					if (g.equals(word)) {
						JOptionPane.showMessageDialog(frame, "You guessed it.");
						frame.dispose();
						return;
					}

					if (!words.contains(g)) {
						JOptionPane.showMessageDialog(frame, "You must guess a word.");
						return;
					}

					for (byte n = 0; n < g.length(); n++) {
						if (g.charAt(n) == word.charAt(n)) {
							guesses[curGuess].color(n, Color.GREEN);
							continue;
						}

						boolean contains = false;
						for (char c : word.toCharArray())
							if (c == g.charAt(n)) {
								contains = true;
								break;
							}

						if (contains)
							guesses[curGuess].color(n, Color.YELLOW);
					}

					guesses[curGuess].disableInputs();

					if (++curGuess >= guesses.length) {
						JOptionPane.showMessageDialog(frame, String.format("The word was: %s", word));
						frame.dispose();
						return;
					}

					updateGuess(curGuess);
				}
			});
			frame.getRootPane().setDefaultButton(guess);
			frame.add(guess);

			frame.pack();

			updateGuess(0);
		});
	}

	/**
	 * Displays the GUI.
	 */
	private void show() {
		if (curGuess >= 0)
			SwingUtilities.invokeLater(() -> frame.setVisible(true));
	}

	/**
	 * Moves the cursor to the first box of guess {@code g}.
	 * 
	 * @param g The guess to move the cursor to.
	 */
	private void updateGuess(int g) {
		guesses[g].moveCursor(0);
	}

	/**
	 * Launches the game.
	 * 
	 * @param args parsed for game.
	 */
	public static void main(String[] args) {
		byte l = Byte.parseByte(JOptionPane.showInputDialog("Enter word length (>1):", 5)),
				g = Byte.parseByte(JOptionPane.showInputDialog("Enter number of guesses (>0))=:", 5));

		new Wordle(l, g).show();
	}
}