package primitivedrawing.Graphics;

import java.awt.Graphics;

import primitivedrawing.Position;

public class Triangle extends Sprite {
	
	int width;
	int height;
	Position position;
	
	public Triangle(Position position, int width, int height) {
		this.width = width;
		this.height = height;
		this.position = position;
	}
		
	@Override
	public
	void draw(Graphics g) {
		super.draw(g);
		Position p = position;
		g.drawPolygon(new int[]{p.x, p.x + width, p.x + width/2}, new int[]{p.y + height, p.y + height, p.y}, 3);
		g.drawPolygon(new int[]{p.x, p.x + width, p.x + width/2}, new int[]{p.y + height, p.y + height, p.y}, 3);		
	}

}
