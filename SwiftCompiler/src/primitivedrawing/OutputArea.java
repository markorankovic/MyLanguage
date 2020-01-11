package primitivedrawing;
import java.awt.Dimension;
import java.awt.TextArea;

import swift.Scope;

public class OutputArea extends TextArea {
	
	private static final long serialVersionUID = 1L;
	
	CommandFrame frame;
	
	Scope scope = new Scope();
	
	public OutputArea(CommandFrame frame) {
		setEditable(false);
		this.frame = frame;
	}
	
	 @Override
	 public Dimension getPreferredSize() {
	      return new Dimension(500, 100);  
	 } 
	
}
