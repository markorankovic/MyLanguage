package primitivedrawing;

import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import swift.Scope;

/**
 * TextArea which allows inputting of commands
 * @author marko
 *
 */
public class CommandTextArea extends TextArea implements TextListener {

	private static final long serialVersionUID = 1L;
	
	CommandFrame frame;
	
	Scope scope = new Scope();
	
	public CommandTextArea(boolean editable, CommandFrame frame) {
		setEditable(editable);
		this.frame = frame;
		addTextListener(this);
	}
	
	void sendInput(String input, CommandTextArea to) {
		to.setText(to.getText() + "\n" + input);
	}

	 @Override
	 public Dimension getPreferredSize() {
	      return new Dimension(500, 200);  
	 } 
	
	@Override
	public void textValueChanged(TextEvent e) {
		try {
			scope.map.clear();
			((DrawingCommandProcessor) frame.dcp).dc.clear();
			scope.run(getText());
		} catch (Exception e1) {
		}
	}
	
}
