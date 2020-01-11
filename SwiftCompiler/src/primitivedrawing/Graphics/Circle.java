package primitivedrawing.Graphics;

import java.awt.Graphics;

import primitivedrawing.Position;

public class Circle extends Sprite {
	
	int radius;
	Position position;
	
	public Circle(Position position, int r) {
		this.position = position;
		this.radius = r;
	}
	
	@Override
	public
	void draw(Graphics g) {
		super.draw(g);
		g.drawOval(position.x, position.y, radius * 2, radius * 2);
	}
	
}
