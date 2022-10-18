package nbradham.wordle;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

final class OneCField extends JTextField {
	private static final long serialVersionUID = 1L;

	private final Guess par;
	private final int prev, next;

	OneCField(Guess parent, int prevInd, int nextInd) {
		super(1);
		par = parent;
		prev = prevInd;
		next = nextInd;

		setEnabled(false);
		setDocument(new PlainDocument() {
			private static final long serialVersionUID = 1L;

			@Override
			public final void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
				if (getLength() + str.length() <= 1) {
					super.insertString(offs, str, a);
					if (par.moveCursor(next))
						setEnabled(false);
				}
			}
		});
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && getText().length() < 1 && par.clearAndMove(prev))
					setEnabled(false);
			}
		});
	}
}