package entities;

import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

import map.Map;

public abstract class Entity implements MovementHandler, getInfoAboutEntity, BulletManager {
	public boolean play = true;

	private String imageName;
	private ImageIcon image;
	private String fileType = ".png";

	protected Map map;

	private Bullet bullet;
	private long delayForShoot = 750L;

	protected int x;
	protected int y;

	private int width;
	private int hight;

	protected int speed = 5;

	protected boolean shooted = false;
	protected boolean isReadyForShoot = true;

	protected String direction = "up";

	protected boolean isPresed;

	public CollisionHandler collisionHandler;

	protected boolean hasCollisionWithOtherObjects = false;

	protected boolean movingUp;
	protected boolean movingDown;
	protected boolean movingLeft;
	protected boolean movingRight;

	protected Entity(int x, int y, int width, int hight, Map map, String imageName) {
		this.x = x;
		this.y = y;

		this.width = width;
		this.hight = hight;

		collisionHandler = new CollisionHandler();
		this.imageName = imageName;

		if (imageName.contains("player")) {
			image = new ImageIcon(imageName + "_" + "up" + fileType);
		} else if (imageName.contains("enemy")) {
			image = new ImageIcon(imageName + "_" + "down" + fileType);
		}

		this.map = map;
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
			if (collisionHandler.isAbleToMove(this))
				moveUp();

		} else if (movingDown) {
			if (collisionHandler.isAbleToMove(this))
				moveDown();

		} else if (movingLeft) {
			if (collisionHandler.isAbleToMove(this))
				moveLeft();

		} else if (movingRight) {
			if (collisionHandler.isAbleToMove(this))
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
	public String getType() {
		return imageName;
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

	public Map getMap() {
		return map;
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

interface MovementHandler {
	public void moveUp();

	public void moveDown();

	public void moveLeft();

	public void moveRight();
}

interface getInfoAboutEntity {
	public int getX();

	public int getY();

	public String getType();

	public int getSpeed();

	public int getWidth();

	public int getHight();

	public Bullet getBullet();

	public boolean isShooted();

	public boolean isReadyForShoot();
}

interface BulletManager {
	public void createBullet();

	public void deleteBullet();
}

interface ShootingHandler {
	void shoot();
}