package primitivedrawing;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import primitivedrawing.Graphics.Circle;
import primitivedrawing.Graphics.Line;
import primitivedrawing.Graphics.Rect;
import primitivedrawing.Graphics.Sprite;
import primitivedrawing.Graphics.Triangle;

/**
 * Canvas for drawing 2D graphics.
 * @author marko
 *
 */
public class DrawingGraphics extends Canvas implements DrawingContext, FocusListener  {

	private static final long serialVersionUID = 1L;

	Position currentPosition = new Position();
	Color currentColor = new Color(0, 0, 0);
		
	Sprite rootGraphic = new Sprite();
	
	public DrawingGraphics(int width, int height) {
		super.setSize(width, height);
		this.addFocusListener(this);
	}
		
	@Override
	public void paint(Graphics g) {
		rootGraphic.drawTree(g); // Draws all the primitives created
	}
		
	@Override
	public void clear() {
		rootGraphic.children.clear();
	}

	@Override
	public void reset() {
		currentPosition = new Position();
	}

	@Override
	public void position(int x, int y) {
		currentPosition = new Position(x, y);
	}

	@Override
	public void drawTo(int x, int y) {
		Line line = new Line(new Position(currentPosition.x, currentPosition.y), new Position(x, y));
		line.color = currentColor;
		rootGraphic.add(line);
	}

	@Override
	public void drawRect(int width, int height) {
		Rect rect = new Rect(new Position(currentPosition.x, currentPosition.y), width, height);
		rect.color = currentColor;
		rootGraphic.add(rect);
	}

	@Override
	public void drawCircle(int r) {
		Circle circle = new Circle(currentPosition, r);
		circle.color = currentColor;
		rootGraphic.add(circle);
	}

	@Override
	public void drawTriangle(int width, int height) {
		Triangle triangle = new Triangle(new Position(currentPosition.x, currentPosition.y), width, height);
		triangle.color = currentColor;
		rootGraphic.add(triangle);
	}

	@Override
	public void focusGained(FocusEvent e) {
		repaint();
	}

	@Override
	public void focusLost(FocusEvent e) { }

	@Override
	public void getFocus() {
		this.requestFocus();
	}

	@Override
	public void setColor(int r, int g, int b) {
		this.currentColor = new Color(r, g, b);
	}
	
}
