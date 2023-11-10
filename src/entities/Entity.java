package entities;

import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

import map.Map;

public abstract class Entity implements getInfoAboutEntity {
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

	protected int x;
	protected int y;

	private int width;
	private int hight;

	protected int speed;

	protected boolean shooted = false;
	protected boolean isReadyForShoot = true;

	protected String direction = "up";

	protected boolean isPresed;
	
	protected DirectionHandler directionHandler = new DirectionHandler();
	
	protected boolean movingUp;
	protected boolean movingDown;
	protected boolean movingLeft;
	protected boolean movingRight;

	protected Entity(int x, int y, int speed, int width, int hight, Map map, String imageName) {
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

		this.speed = speed;

		moving();
	}

	@Override
	public int getOX() {
		return x;
	}

	@Override
	public int getOY() {
		return y;
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
		return movingUp ? "up" : movingDown ? "down" : movingLeft ? "left" : movingRight ? "right" : "up";
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

	protected boolean hitBlock(int a, int b) {
		return !map.checkCollision(x + a, y + b, width, hight) && !map.checkSolidCollision(x + a, y + b, width, hight);
	}

	public boolean isOutOfBounds( ) {
		if (movingUp)
			return y > yMin && hitBlock(0, -5);
		else if (movingDown)
			return y < yMax && hitBlock(0, 5);
		else if (movingLeft)
			return x > xMin && hitBlock(-5, 0);
		else if (movingRight)
			return x < xMax && hitBlock(5, 0);

		return true;
	}
	
	public void createBullet() {
		bullet = new Bullet(this);
		isReadyForShoot = false;
		delay(2000L);
	}
	
	public void dellBullet() {
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
	
	public void moving() {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (play) {
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (movingUp) {
						if (isOutOfBounds())
							y -= speed;

					} else if (movingDown) {
						if (isOutOfBounds())
							y += speed;

					} else if (movingLeft) {
						if (isOutOfBounds())
							x -= speed;

					} else if (movingRight) {
						if (isOutOfBounds())
							x += speed;
					}
				}
			}
		});
		thread.start();
	}
}

interface getInfoAboutEntity {
	public int getOX();

	public int getOY();

	public int getWidth();

	public int getHight();
	
	public Bullet getBullet();

	public boolean isShooted();
	
	public boolean isReadyForShoot();
}