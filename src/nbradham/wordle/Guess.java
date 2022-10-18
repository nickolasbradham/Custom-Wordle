package nbradham.wordle;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

final class Guess extends JPanel {
	private static final long serialVersionUID = 1L;

	private final JTextField f1 = new JTextField(1), f2 = new JTextField(1), f3 = new JTextField(1),
			f4 = new JTextField(1), f5 = new JTextField(1);

	Guess(int i) {
		super();
		add(new JLabel(String.format("Guess %d: ", i)));
		add(f1);
		add(f2);
		add(f3);
		add(f4);
		add(f5);
	}
}