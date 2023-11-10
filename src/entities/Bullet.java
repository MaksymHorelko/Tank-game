package entities;

import java.awt.Color;
import java.awt.Graphics;

public final class Bullet extends Entity {

	private static String type = "bullet";

	public Bullet(Entity entity) {
		super(entity.x, entity.y, entity.speed * 2, 10, 10, entity.map, type);
		
		setSettings(entity.direction);
	}

	private void setSettings(String direction) {
		yMin += 10;
		yMax += 40;
		xMax += 40;

		if (direction.equals("up")) {
			movingUp = true;
			x += 20;
			y -= 5;
		} else if (direction.equals("down")) {
			movingDown = true;
			x += 20;
			y += 43;
		} else if (direction.equals("left")) {
			movingLeft = true;
			x -= 6;
			y += 20;
		} else if (direction.equals("right")) {
			movingRight = true;
			x += 44;
			y += 20;
		}
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.yellow);
		g.fillOval(x, y, 10, 10);
	}
}