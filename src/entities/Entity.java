package entities;

import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

import map.Map;

public abstract class Entity implements MovementHandler, CollisionHandler, getInfoAboutEntity, BulletManager {
	public boolean play = true;

	private String imageName;
	private ImageIcon image;
	private String fileType = ".png";

	protected Map map;

	protected int xMin = 0;
	protected int xMax = 600;
	protected int yMin = 0;
	protected int yMax = 550;

	private Bullet bullet;
	protected long delayForShoot = 750L;

	protected int x;
	protected int y;

	private int width;
	private int hight;

	protected int speed = 5;

	protected boolean shooted = false;
	protected boolean isReadyForShoot = true;

	protected String direction = "up";

	protected boolean isPresed;

	protected boolean movingUp;
	protected boolean movingDown;
	protected boolean movingLeft;
	protected boolean movingRight;

	protected Entity(int x, int y, int width, int hight, Map map, String imageName) {
		this.x = x;
		this.y = y;

		this.width = width;
		this.hight = hight;

		this.imageName = imageName;

		if (imageName.contains("player")) {
			image = new ImageIcon(imageName + "_" + "up" + fileType);
		} else if (imageName.contains("enemy")) {
			image = new ImageIcon(imageName + "_" + "down" + fileType);
		}

		this.map = map;

		run();
	}

	@Override
	public void moveUp() {
		y -= speed;
	}

	@Override
	public void moveDown() {
		y += speed;
	}

	@Override
	public void moveLeft() {
		x -= speed;
	}

	@Override
	public void moveRight() {
		x += speed;
	}

	private void move() {
		if (movingUp) {
			if (hasCollisionAbove())
				moveUp();

		} else if (movingDown) {
			if (hasCollisionBelow())
				moveDown();

		} else if (movingLeft) {
			if (hasCollisionLeft())
				moveLeft();

		} else if (movingRight) {
			if (hasCollisionRight())
				moveRight();
		}
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public int getSpeed() {
		return speed;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHight() {
		return hight;
	}

	@Override
	public Bullet getBullet() {
		return bullet;
	}

	@Override
	public boolean isShooted() {
		return shooted;
	}

	@Override
	public boolean isReadyForShoot() {
		return isReadyForShoot;
	}

	public String getDirection() {
		return direction;
	}

	public void draw(Graphics g) {
		if (movingUp)
			image = new ImageIcon(imageName + "_" + getDirection() + fileType);
		else if (movingDown)
			image = new ImageIcon(imageName + "_" + getDirection() + fileType);
		else if (movingLeft)
			image = new ImageIcon(imageName + "_" + getDirection() + fileType);
		else if (movingRight)
			image = new ImageIcon(imageName + "_" + getDirection() + fileType);

		image.paintIcon(null, g, x, y);
	}

	private boolean hitBlock(int a, int b) {
		return !map.checkCollision(x + a, y + b, width, hight) && !map.checkSolidCollision(x + a, y + b, width, hight);
	}

	public boolean hasCollision(String direction) {
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

	@Override
	public boolean hasCollisionAbove() {
		return y > yMin && hitBlock(0, -speed);
	}

	@Override
	public boolean hasCollisionBelow() {
		return y < yMax && hitBlock(0, speed);
	}

	@Override
	public boolean hasCollisionLeft() {
		return x > xMin && hitBlock(-speed, 0);
	}

	@Override
	public boolean hasCollisionRight() {
		return x < xMax && hitBlock(speed, 0);
	}

	@Override
	public void createBullet() {
		bullet = new Bullet(this);
		isReadyForShoot = false;
		delay(delayForShoot);
	}

	@Override
	public void deleteBullet() {
		bullet.play = false;

		shooted = false;
		bullet = null;
	}

	private void delay(long delay) {
		TimerTask task = new TimerTask() {
			public void run() {
				isReadyForShoot = true;
			}
		};

		Timer timer = new Timer("Timer");

		timer.schedule(task, delay);
	}

	public void run() {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (play) {
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					move();
				}
			}
		});
		thread.start();
	}
}

interface CollisionHandler {
	public boolean hasCollisionAbove();

	public boolean hasCollisionBelow();

	public boolean hasCollisionLeft();

	public boolean hasCollisionRight();

}

interface MovementHandler {
	public void moveUp();

	public void moveDown();

	public void moveLeft();

	public void moveRight();
}

interface BulletManager {
	public void createBullet();

	public void deleteBullet();
}

interface ShootingHandler {
	void shoot();
}

interface getInfoAboutEntity {
	public int getX();

	public int getY();

	public int getSpeed();

	public int getWidth();

	public int getHight();

	public Bullet getBullet();

	public boolean isShooted();

	public boolean isReadyForShoot();
}