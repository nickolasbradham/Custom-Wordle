package nbradham.wordle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

final class Wordle {

	private static final String ACT_GUESS = "guess";

	private final JFrame frame = new JFrame("Wordle");
	private final Guess[] guesses = { new Guess(1), new Guess(2), new Guess(3), new Guess(4), new Guess(5) };

	private Wordle() {
		SwingUtilities.invokeLater(() -> {
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

			for (Guess g : guesses)
				frame.add(g);

			JButton guess = new JButton("Guess");
			guess.setActionCommand(ACT_GUESS);
			guess.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (e.getActionCommand().equals(ACT_GUESS)) {
						System.out.println("Guessing");
						// TODO test guess.
					}
				}
			});
			frame.getRootPane().setDefaultButton(guess);
			frame.add(guess);

			frame.pack();
			frame.setVisible(true);

			guesses[0].moveCursor(0);
		});
	}

	public static void main(String[] args) {
		new Wordle();
	}
}