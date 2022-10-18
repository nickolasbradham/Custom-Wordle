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

final class Wordle {

	private final JFrame frame = new JFrame("Wordle");
	private final Guess[] guesses = { new Guess(1), new Guess(2), new Guess(3), new Guess(4), new Guess(5) };
	private byte curGuess = 0;

	private Wordle() {

		Scanner scan = new Scanner(Wordle.class.getResourceAsStream("/words.txt"));
		ArrayList<String> words = new ArrayList<>();
		while (scan.hasNext())
			words.add(scan.nextLine());
		String word = words.get(new Random().nextInt(words.size()));

		SwingUtilities.invokeLater(() -> {
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

			for (Guess g : guesses)
				frame.add(g);

			JButton guess = new JButton("Guess");
			guess.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String g = guesses[curGuess].getText();
					if (g.length() != 5) {
						JOptionPane.showMessageDialog(frame, "Guess must be complete.");
						return;
					}

					if (g.equals(word)) {
						JOptionPane.showMessageDialog(frame, "You guessed it.");
						frame.dispose();
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
					updateGuess(++curGuess);
				}
			});
			frame.getRootPane().setDefaultButton(guess);
			frame.add(guess);

			frame.pack();

			updateGuess(0);
		});
	}

	private void show() {
		SwingUtilities.invokeLater(() -> frame.setVisible(true));
	}

	private void updateGuess(int g) {
		guesses[g].moveCursor(0);
	}

	public static void main(String[] args) {
		new Wordle().show();
	}
}