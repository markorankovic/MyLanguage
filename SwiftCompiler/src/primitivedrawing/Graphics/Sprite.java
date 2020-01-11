package primitivedrawing.Graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Entity to be rendered onto a canvas.
 * @author marko
 *
 */
public class Sprite {

	public Color color = new Color(0);
	
	public ArrayList<Sprite> children = new ArrayList<Sprite>();
	
	/**
	 * Renders shapes within the tree.
	 * @param graphics for which the tree of Sprites will be drawn to
	 */
	public void drawTree(Graphics graphics) {
		draw(graphics);
		for (Sprite child : children) {
			child.drawTree(graphics);
		}
	}
	
	
	public void add(Sprite sprite) {
		children.add(sprite);
	}

	/**
	 * Draws the individual Sprite.
	 * @param graphics for which the individual Sprite will be drawn to.
	 */
	public void draw(Graphics graphics) { 
		graphics.setColor(color);
	}
	
}
