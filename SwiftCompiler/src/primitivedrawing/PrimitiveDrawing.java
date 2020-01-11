package primitivedrawing;

import java.awt.Component;

/**
 * Class which creates an instance of the PrimitiveDrawing program.
 * @author marko
 *
 */

public class PrimitiveDrawing {
	
	/**
	 * The program creates two separate frames, one for the command line, 
	 * the other for rendering the graphics.
	 */
	
	public PrimitiveDrawing() {
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		
		int width = 600;
		int height = 600;
				
		DrawingFrame gf = new DrawingFrame("primitivedrawing demo");
		DrawingCommandProcessor dcp = new DrawingCommandProcessor((DrawingContext) new DrawingGraphics(width, height));
		CommandFrame commandFrame = new CommandFrame(dcp);       
               
		gf.setSize(width, height);
		gf.setVisible(true);
		gf.add((Component) dcp.dc);
		              
		int w = 300;
		int h = 500;
		commandFrame.setTitle("commands");
		commandFrame.setSize(w, h);
		commandFrame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new PrimitiveDrawing();
	}

}
