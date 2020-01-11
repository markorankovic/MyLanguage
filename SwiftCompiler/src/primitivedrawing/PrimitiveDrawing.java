package primitivedrawing;

/**
 * Class which creates an instance of the PrimitiveDrawing program.
 * @author marko
 *
 */

public class PrimitiveDrawing {
	
	public CommandFrame commandFrame;
	
	/**
	 * The program creates two separate frames, one for the command line, 
	 * the other for rendering the graphics.
	 */
	
	public PrimitiveDrawing() {
		System.setProperty("apple.laf.useScreenMenuBar", "true");
						
		this.commandFrame = new CommandFrame();       
				              
		commandFrame.setVisible(true);
	}
	
}
