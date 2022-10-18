package nbradham.wordle;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Handles a single guess on the GUI.
 * 
 * @author Nickolas Bradham
 *
 */
final class Guess extends JPanel {
	private static final long serialVersionUID = 1L;

	private final OneCField[] letters = new OneCField[5];

	/**
	 * Constructs a new Guess and sets its number.
	 * 
	 * @param num Displayed on the label in the GUI.
	 */
	Guess(int num) {
		super();
		add(new JLabel(String.format("Guess %d: ", num)));

		for (byte n = 0; n < letters.length; n++)
			add(letters[n] = new OneCField(this, n));
	}

	/**
	 * Checks if {@code box} is between 0 and number of fields (inclusive).
	 * 
	 * @param box The number to check.
	 * @return True if {@code box} is a valid field index.
	 */
	private boolean isInBounds(int box) {
		return box >= 0 && box < letters.length;
	}

	/**
	 * Moves the cursor to box {@code box} and makes it editable.
	 * 
	 * @param box The box to move the cursor to.
	 * @return True if the cursor was moved.
	 */
	final boolean moveCursor(int box) {
		if (isInBounds(box)) {
			letters[box].setEditable(true);
			letters[box].requestFocus();
			return true;
		}
		return false;
	}

	/**
	 * Moves the cursor to box {@code box} and clears the text.
	 * 
	 * @param box The box to move the cursor to.
	 * @return True if the cursor was moved.
	 */
	final boolean clearAndMove(int box) {
		if (isInBounds(box)) {
			letters[box].setText("");
			moveCursor(box);
			return true;
		}
		return false;
	}

	/**
	 * Retrieves the text from the fields.
	 * 
	 * @return A String with all characters from the fields, in the order they
	 *         appear.
	 */
	final String getText() {
		StringBuilder s = new StringBuilder();
		for (OneCField f : letters)
			s.append(f.getText());
		return s.toString();
	}

	/**
	 * Sets the text color of a field.
	 * 
	 * @param let The field to color.
	 * @param c   The color to set.
	 */
	final void color(int let, Color c) {
		letters[let].setForeground(c);
	}

	/**
	 * Sets all fields as not editable.
	 */
	final void disableInputs() {
		for (OneCField f : letters)
			f.setEditable(false);
	}
}