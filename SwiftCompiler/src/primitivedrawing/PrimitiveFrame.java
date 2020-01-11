package primitivedrawing;

import javax.swing.JFrame;

/**
 * JFrame which the application occupies. 
 * @author marko
 *
 */
public abstract class PrimitiveFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Any subclass instance that closes will terminate the application. 
	 */
	public PrimitiveFrame() {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
