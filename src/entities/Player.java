package entities;

import java.awt.event.KeyEvent;

import map.Map;

interface PlayerOperations {

	public void keyPressed(int key);

	public void keyReleased(int key);
}

public final class Player extends Entity implements PlayerOperations {
	private static String type = "player_tank";

	public Player(int x, int y, Map map) {
		super(x, y, 50, 50, map, type);
	}

	@Override
	public void keyPressed(int key) {
		switch (key) {
		case KeyEvent.VK_W:
			movingUp = true;
			movingDown = false;
			movingLeft = false;
			movingRight = false;
			direction = "up";
			break;

		case KeyEvent.VK_S:
			movingUp = false;
			movingDown = true;
			movingLeft = false;
			movingRight = false;
			direction = "down";
			break;

		case KeyEvent.VK_A:
			movingUp = false;
			movingDown = false;
			movingLeft = true;
			movingRight = false;
			direction = "left";
			break;

		case KeyEvent.VK_D:
			movingUp = false;
			movingDown = false;
			movingLeft = false;
			movingRight = true;
			direction = "right";
			break;

		case KeyEvent.VK_SPACE:
			if (isReadyForShoot)
				shooted = true;
			break;
		}
	}

	@Override
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