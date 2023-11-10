package entities;

import java.awt.event.KeyEvent;

import map.Map;

public final class Player extends Entity {

	private static String type = "player_tank";

	public Player(int x, int y, int speed, Map map) {
		super(x, y, speed, 50, 50, map, type);
	}

	public void keyPressed(int key) {
		if (key == KeyEvent.VK_W) {
			movingUp = true;
			movingDown = false;
			movingLeft = false;
			movingRight = false;
			direction = "up";
		} else if (key == KeyEvent.VK_S) {
			movingUp = false;
			movingDown = true;
			movingLeft = false;
			movingRight = false;
			direction = "down";
		} else if (key == KeyEvent.VK_A) {
			movingUp = false;
			movingDown = false;
			movingLeft = true;
			movingRight = false;
			direction = "left";
		} else if (key == KeyEvent.VK_D) {
			movingUp = false;
			movingDown = false;
			movingLeft = false;
			movingRight = true;
			direction = "right";
		}
		if (key == KeyEvent.VK_SPACE && isReadyForShoot) {
			shooted = true;
		}
	}

	public void keyReleased(int key) {
		if (key == KeyEvent.VK_W) {
			movingUp = false;
		}
		if (key == KeyEvent.VK_S) {
			movingDown = false;
		}
		if (key == KeyEvent.VK_A) {
			movingLeft = false;
		}
		if (key == KeyEvent.VK_D) {
			movingRight = false;
		}
	}
}