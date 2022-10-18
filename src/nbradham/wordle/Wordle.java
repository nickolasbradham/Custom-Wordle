package nbradham.wordle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

final class Wordle extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	private static final String ACT_GUESS = "guess";

	private final Guess g1 = new Guess(1), g2 = new Guess(2), g3 = new Guess(3), g4 = new Guess(4), g5 = new Guess(5);

	private Wordle() {
		super("Wordle");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		add(g1);
		add(g2);
		add(g3);
		add(g4);
		add(g5);

		JButton guess = new JButton("Guess");
		guess.setActionCommand(ACT_GUESS);
		guess.addActionListener(this);
		add(guess);

		pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(ACT_GUESS)) {
			// TODO test guess.
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Wordle().setVisible(true));
	}
}