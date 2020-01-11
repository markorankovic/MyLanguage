package primitivedrawing;

/**
 * Any Graphics context which implements this interface will support the following methods.
 * @author marko
 *
 */
public interface DrawingContext {

	/**
	 * Clears the rendered graphics.
	 */
	public void clear();
	/**
	 * Resets position to default (x: 0, y: 0).
	 */
	public void reset();
	/**
	 * Sets position.
	 * @param x 
	 * @param y
	 */
	public void position(int x, int y);
	/**
	 * Draws to specified position.
	 * @param x
	 * @param y
	 */
	public void drawTo(int x, int y);
	/**
	 * Draws rectangle.
	 * @param width
	 * @param height
	 */
	public void drawRect(int width, int height);
	/**
	 * Draws circle.
	 * @param r
	 */
	public void drawCircle(int r);
	/**
	 * Draws triangle.
	 * @param width
	 * @param height
	 */
	public void drawTriangle(int width, int height);
	/**
	 * Sets color.
	 * @param r red
	 * @param g green
	 * @param b blue
	 */
	public void setColor(int r, int g, int b);
	/**
	 * Focuses on graphics context.
	 */
	public void getFocus();
	
}
