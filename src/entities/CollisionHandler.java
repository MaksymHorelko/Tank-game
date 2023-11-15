package entities;

import java.awt.Rectangle;

import map.Map;

public class CollisionHandler {
	private int x;
	private int y;

	private Map map;

	private int xMin = 0;
	private int xMax = 600;
	private int yMin = 0;
	private int yMax = 550;

	private boolean hasCollisionPlayerWithEnemyAbove;
	private boolean hasCollisionPlayerWithEnemyBelow;
	private boolean hasCollisionPlayerWithEnemyLeft;
	private boolean hasCollisionPlayerWithEnemyRight;

	private boolean hasCollisionEnemyWithEnemyAbove;
	private boolean hasCollisionEnemyWithEnemyBelow;
	private boolean hasCollisionEnemyWithEnemyLeft;
	private boolean hasCollisionEnemyWithEnemyRight;

	private int speed;

	private int width;
	private int hight;

	public void setSettings(Entity entity) {
		if (entity instanceof Bullet && map == null) {
			yMax += 30;
			xMax += 30;
		}

		this.speed = entity.getSpeed();

		this.x = entity.getX();
		this.y = entity.getY();

		this.width = entity.getWidth();
		this.hight = entity.getHight();

		this.map = entity.getMap();
	}

	public boolean isAbleToMove(Entity entity) {

		setSettings(entity);

		switch (entity.getDirection()) {

		case "up":
			return !hasCollisionAbove() && !hasCollisionPlayerWithEnemyAbove && !hasCollisionEnemyWithEnemyAbove;
		case "down":
			return !hasCollisionBelow() && !hasCollisionPlayerWithEnemyBelow && !hasCollisionEnemyWithEnemyBelow;

		case "left":
			return !hasCollisionLeft() && !hasCollisionPlayerWithEnemyLeft && !hasCollisionEnemyWithEnemyLeft;

		case "right":
			return !hasCollisionRight() && !hasCollisionPlayerWithEnemyRight && !hasCollisionEnemyWithEnemyRight;
		}

		return false;
	}

	private boolean hitBlock(int a, int b) {
		return !map.checkCollision(x + a, y + b, width, hight) && !map.checkSolidCollision(x + a, y + b, width, hight);
	}

	private boolean hasCollisionWithObject(Entity entity, int a, int b) {
		return new Rectangle(x + a, y + b, width, hight)
				.intersects(new Rectangle(entity.getX(), entity.getY(), entity.getWidth(), entity.getHight()));
	}

	private boolean hasCollisionWithObjectAbove(Entity entity) {
		return hasCollisionWithObject(entity, 0, -speed);
	}

	private boolean hasCollisionWithObjectBelow(Entity entity) {
		return hasCollisionWithObject(entity, 0, speed);
	}

	private boolean hasCollisionWithObjectLeft(Entity entity) {
		return hasCollisionWithObject(entity, -speed, 0);
	}

	private boolean hasCollisionWithObjectRight(Entity entity) {
		return hasCollisionWithObject(entity, speed, 0);
	}

	public boolean hasCollisionWithObject(Entity firstEntity, Entity entity) {
		String direction = firstEntity.getDirection();

		setSettings(firstEntity);

		switch (direction) {
		case "up":
			return hasCollisionPlayerWithEnemyAbove = hasCollisionWithObjectAbove(entity);

		case "down":
			return hasCollisionPlayerWithEnemyBelow = hasCollisionWithObjectBelow(entity);

		case "left":
			return hasCollisionPlayerWithEnemyLeft = hasCollisionWithObjectLeft(entity);

		case "right":
			return hasCollisionPlayerWithEnemyRight = hasCollisionWithObjectRight(entity);

		}
		return false;
	}

	public boolean hasCollisionEnemyWithEnemy(Enemy enemy1, Enemy enemy2) {
		String direction = enemy1.getDirection();

		setSettings(enemy1);

		switch (direction) {
		case "up":
			return hasCollisionEnemyWithEnemyAbove = hasCollisionWithObjectAbove(enemy2);

		case "down":
			return hasCollisionEnemyWithEnemyBelow = hasCollisionWithObjectBelow(enemy2);

		case "left":
			return hasCollisionEnemyWithEnemyLeft = hasCollisionWithObjectLeft(enemy2);

		case "right":
			return hasCollisionEnemyWithEnemyRight = hasCollisionWithObjectRight(enemy2);

		}
		return false;
	}

	public boolean hasCollisionWithMap(Entity entity, String direction) {

		setSettings(entity);

		switch (direction) {
		case "up":
			return hasCollisionAbove();

		case "down":
			return hasCollisionBelow();

		case "left":
			return hasCollisionLeft();

		case "right":
			return hasCollisionRight();

		}
		return false;
	}

	private boolean hasCollisionAbove() {
		return !(y > yMin && hitBlock(0, -speed));
	}

	private boolean hasCollisionBelow() {
		return !(y < yMax && hitBlock(0, speed));
	}

	private boolean hasCollisionLeft() {
		return !(x > xMin && hitBlock(-speed, 0));
	}

	private boolean hasCollisionRight() {
		return !(x < xMax && hitBlock(speed, 0));
	}
}