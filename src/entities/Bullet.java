package entities;

import java.awt.Color;
import java.awt.Graphics;

public final class Bullet extends Entity {
	private final static String type = "bullet";
	
	private final static int c = 3;
	
	public Bullet(Entity entity) {
		super(entity.x, entity.y, 10, 10, entity.map, type);

		this.direction = entity.direction;
		
		this.speed *= c;
		setSettings(entity.direction);
	}
	
	private void setSettings(String direction) {

		switch (direction) {
		case "up":
			movingUp = true;
			x += 20;
			y -= 5;
			break;

		case "down":
			movingDown = true;
			x += 20;
			y += 43;
			break;

		case "left":
			movingLeft = true;
			x -= 6;
			y += 20;
			break;

		case "right":
			movingRight = true;
			x += 44;
			y += 20;
			break;
		}
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.yellow);
		g.fillOval(x, y, 10, 10);
	}
}