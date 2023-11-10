package entities;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import map.Map;

public final class Enemy extends Entity {

	private static String type = "enemy_tank";
	private ArrayList <String> abilityToMove;

	public Enemy(int x, int y, int speed, Map map) {
		super(x, y, speed, 50, 50, map, type);
	}

	public void keyPressed() {
		movingUp = true;
		if (isOutOfBounds()) {
			abilityToMove.add("up");
		}
		movingUp = false;
		movingDown = true;
		if(isOutOfBounds()) {
			abilityToMove.add("down");
		}
		movingDown = false;
		movingLeft = true;
		if(isOutOfBounds()) {
			abilityToMove.add("left");
		}
		movingLeft = true;
		movingRight = false;
		if(isOutOfBounds()) {
			abilityToMove.add("right");
		}
	}

	public void pickDirection() {

	}
}