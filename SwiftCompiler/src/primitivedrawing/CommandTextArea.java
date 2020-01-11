package primitivedrawing;

import java.awt.TextArea;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * TextArea which allows inputting of commands
 * @author marko
 *
 */
public class CommandTextArea extends TextArea implements KeyListener {

	private static final long serialVersionUID = 1L;
	
	CommandFrame frame;
	
	public CommandTextArea(boolean editable, CommandFrame frame) {
		setEditable(editable);
		this.frame = frame;
		addKeyListener(this);
	}
	
	void sendInput(String input, CommandTextArea to) {
		to.setText(to.getText() + "\n" + input);
	}

	@Override
	public void keyTyped(KeyEvent e) { }

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (isEditable()) {
				String input = getText().replaceAll("\\n", "");
				sendInput(input, frame.commandList);
				frame.commandEntry.setText("");
				frame.commandProcessor.parseCommandString(input);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) { }

}
