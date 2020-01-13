package primitivedrawing;

import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

import swift.Scope;

public class InputArea extends TextArea implements TextListener {

	private static final long serialVersionUID = 1L;
	
	CommandFrame frame;
	
	Scope scope = new Scope();
	
	public InputArea(CommandFrame frame) {
		setEditable(true);
		this.frame = frame;
		addTextListener(this);
	}
	
	 @Override
	 public Dimension getPreferredSize() {
	      return new Dimension(500, 300);  
	 } 
	
	@Override
	public void textValueChanged(TextEvent e) {
		((DrawingCommandProcessor) frame.dcp).dc.clear();
		scope.result = null;
		scope.map.clear();
		try {
			Object result = scope.run(getText());
			((DrawingGraphics)frame.dcp.dc).repaint();
			this.frame.outputArea.setText(result.toString());
		} catch (Exception err) {
			this.frame.outputArea.setText(err.getMessage());
		}
	}

}
