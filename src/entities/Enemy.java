package entities;

import java.util.ArrayList;
import java.util.Random;

import map.Map;

interface EnemyOperations {

	public void chooseDirection();

	public void canMoveInCurrentDirection();
}

public final class Enemy extends Entity implements EnemyOperations {

	private static String type = "enemy_tank";

	private ArrayList<String> possibleDirections = new ArrayList<>();
	private Random random = new Random();

	public Enemy(int x, int y, Map map) {
		super(x, y, 50, 50, map, type);
	}

	public void keyPressed() {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (play) {
					try {
						Thread.sleep(750);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

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
			}
		});
		thread.start();
	}

	@Override
	public void chooseDirection() {
		int randomDirection = random.nextInt(possibleDirections.size());

		this.direction = possibleDirections.get(randomDirection);

		possibleDirections.clear();
	}

	@Override
	public void canMoveInCurrentDirection() {

		if (!collisionHandler.hasCollisionWithMap(this, "up")) {
			possibleDirections.add("up");
		}

		if (!collisionHandler.hasCollisionWithMap(this, "down")) {
			possibleDirections.add("down");
		}

		if (!collisionHandler.hasCollisionWithMap(this, "left")) {
			possibleDirections.add("left");
		}

		if (!collisionHandler.hasCollisionWithMap(this, "right")) {
			possibleDirections.add("right");
		}
	}
}