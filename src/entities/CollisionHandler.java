package entities;

import java.awt.Rectangle;

import map.Map;

interface collisionWithOtherObjects {
	
}

interface collisionsWithWalls {
	
}

public class CollisionHandler {
	private int x;
	private int y;
	
	private Map map;
	
	private int xMin = 0;
	private int xMax = 600;
	private int yMin = 0;
	private int yMax = 550;
	
	private boolean h1;
	private boolean h2;
	private boolean h3;
	private boolean h4;
	
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
			return !hasCollisionAbove() && !h1;
		case "down":
			return !hasCollisionBelow() && !h2;

		case "left":
			return !hasCollisionLeft() && !h3;

		case "right":
			return !hasCollisionRight() && !h4;
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
			return h1 = hasCollisionWithObjectAbove(entity);

		case "down":
			return h2 = hasCollisionWithObjectBelow(entity);

		case "left":
			return h3 = hasCollisionWithObjectLeft(entity);

		case "right":
			return h4 = hasCollisionWithObjectRight(entity);

		}
		return false;
	}

	public boolean hasCollision(Entity entity, String direction) {
		
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