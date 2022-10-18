package nbradham.wordle;

import javax.swing.JLabel;
import javax.swing.JPanel;

final class Guess extends JPanel {
	private static final long serialVersionUID = 1L;

	private final OneCField[] letters = new OneCField[5];

	Guess(int i) {
		super();
		add(new JLabel(String.format("Guess %d: ", i)));

		for (byte n = 0; n < letters.length; n++)
			add(letters[n] = new OneCField(this, n - 1, n + 1));
	}

	final boolean moveCursor(int box) {
		if (isInBounds(box)) {
			letters[box].setEnabled(true);
			letters[box].requestFocus();
			return true;
		}
		return false;
	}

	final boolean clearAndMove(int box) {
		if (isInBounds(box)) {
			letters[box].setText("");
			moveCursor(box);
			return true;
		}
		return false;
	}

	private boolean isInBounds(int box) {
		return box >= 0 && box < letters.length;
	}
}