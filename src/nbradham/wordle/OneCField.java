package nbradham.wordle;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Handles text input on the GUI.
 * 
 * @author Nickolas Bradham
 *
 */
final class OneCField extends JTextField {
	private static final long serialVersionUID = 1L;

	private final Guess par;
	private final int ind;

	/**
	 * Creates a new OneCField instance.
	 * 
	 * @param parent The parent Guess associated with this field.
	 * @param index  This OneCField's index in the Guess.
	 */
	OneCField(Guess parent, int index) {
		super(1);
		par = parent;
		ind = index;

		setEditable(false);
		setDocument(new PlainDocument() {
			private static final long serialVersionUID = 1L;

			@Override
			public final void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
				if (getLength() + str.length() <= 1) {
					super.insertString(offs, str.toUpperCase(), a);
					if (par.moveCursor(ind + 1))
						setEditable(false);
				}
			}
		});
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && getText().length() < 1 && par.clearAndMove(ind - 1)) {
					setEditable(false);
					e.consume();
				}
			}
		});
	}
}