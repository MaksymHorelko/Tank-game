package entities;

import java.util.ArrayList;
import java.util.Random;

import map.Map;

interface EnemyOperations {
	public static String type = "enemy_tank";

	public void chooseDirection();

	public void canMoveInCurrentDirection();
}

public final class Enemy extends Entity implements EnemyOperations {

	private ArrayList<String> possibleDirections = new ArrayList<>();
	private Random random = new Random();

	public Enemy(int x, int y, Map map) {
		super(x, y, 50, 50, map, type);
	}
	
//	@Override
	public void keyPressed() {
		canMoveInCurrentDirection();

		chooseDirection();

		switch (direction) {

		case "up":
			movingUp = true;
			movingDown = false;
			movingLeft = false;
			movingRight = false;
			break;

		case "down":
			movingUp = false;
			movingDown = true;
			movingLeft = false;
			movingRight = false;
			break;

		case "left":
			movingUp = false;
			movingDown = false;
			movingLeft = true;
			movingRight = false;
			break;

		case "right":
			movingUp = false;
			movingDown = false;
			movingLeft = false;
			movingRight = true;
			break;
		}
		
		if (isReadyForShoot)
			shooted = true;
	}

	@Override
	public void chooseDirection() {
		int randomDirection = random.nextInt(possibleDirections.size());

		this.direction = possibleDirections.get(randomDirection);
	}

	@Override
	public void canMoveInCurrentDirection() {
		
		if (hasCollisionAbove()) {
			possibleDirections.add("up");
		}

		if (hasCollisionBelow()) {
			possibleDirections.add("down");
		}

		if (hasCollisionLeft()) {
			possibleDirections.add("left");
		}

		if (hasCollisionRight()) {
			possibleDirections.add("right");
		}
	}
}