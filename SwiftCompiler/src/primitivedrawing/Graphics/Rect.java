package primitivedrawing.Graphics;

import java.awt.Graphics;

import primitivedrawing.Position;

public class Rect extends Sprite {

	int width;
	int height;
	Position position;
	
	public Rect(Position position, int width, int height) {
		this.width = width;
		this.height = height;
		this.position = position;
	}
	
	@Override
	public
	void draw(Graphics g) {
		super.draw(g);
		g.drawRect(position.x, position.y, width, height);
	}
	
}
