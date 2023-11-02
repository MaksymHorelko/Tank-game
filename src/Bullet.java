import java.awt.Color;
import java.awt.Graphics;

public class Bullet extends Tank{

	public Bullet(int x, int y, int speed,Map map) {
		super(x, y, speed, map, "bullet.png");
	}
	
	public void moving() {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				while (x > 0 && y > 0 && y < 550 && x < 600 && !map.checkCollision(x, y - 5, 10, 10) && !map.checkSolidCollision(x, y - 5, 10, 10)) {
					if (direction.equals("RIGHT")) {
						x += speed;
					}
					else if (direction.equals("LEFT")) {
						x -= speed;
					}
					else if (direction.equals("UP")) {
						y -= speed;
					}
					else if (direction.equals("DOWN")) {
						y += speed;
					}
				}
			}
		});
		thread.start();
	}

	public void spawn() {
		if (direction.equals("UP")) {
			x += 20;
			y -= 5;
		} else if (direction.equals("DOWN")) {
			x += 20;
			y += 43;
		} else if (direction.equals("LEFT")) {
			x -= 6;
			y += 20;
		} else if (direction.equals("RIGHT")) {
			x += 44;
			y += 20;
		}
	}

	public void draw(Graphics g) {
		spawn();
		g.setColor(Color.yellow);
		g.fillOval(x, y, 10, 10);
//		moving();
	}
}