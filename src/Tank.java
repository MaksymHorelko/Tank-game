
import javax.swing.ImageIcon;

public class Tank {
	protected ImageIcon image;

	protected Map map;

	protected int x;
	protected int y;

	protected int speed;

	protected boolean shooted;

	protected String direction = "UP";
	
	protected boolean movingUp;
	protected boolean movingDown;
	protected boolean movingLeft;
	protected boolean movingRight;

	Tank(int x, int y, int speed, Map map, String imageName) {
		this.x = x;
		this.y = y;

		image = new ImageIcon(imageName);
		this.map = map;

		this.speed = speed;
	}

	public int getOX() {
		return x;
	}
	
	public int getOY() {
		return y;
	}
 	
	public String getDirection() {
		return direction;
	}

	boolean checkCollision(int a, int b) {
		return !map.checkCollision(x + a, y + b, 50, 50) && !map.checkSolidCollision(x + a, y + b, 50, 50);
	}
	
	public void moving() {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (movingUp) {
						if (y > 0 && checkCollision(0, -5))
							y -= speed;

					} else if (movingDown) {
						if (y < 550 && checkCollision(0, 5))
							y += speed;

					} else if (movingLeft) {
						if (x > 0 && checkCollision(-5,0))
							x -= speed;

					} else if (movingRight) {
						if (x < 600 && checkCollision(5,0))
							x += speed;

					}
				}
			}
		});
		thread.start();
	}

}
