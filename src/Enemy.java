import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Enemy extends Entity{

	public Enemy(int x, int y, int speed, Map map) {
		super(x,y, speed, map, "enemy_tank_down.png");
		moving();
	}

	public void draw(Graphics g) {

		if (movingUp)
			image = new ImageIcon("player_tank_up.png");
		else if (movingDown)
			image = new ImageIcon("player_tank_down.png");
		else if (movingLeft)
			image = new ImageIcon("player_tank_left.png");
		else if (movingRight)
			image = new ImageIcon("player_tank_right.png");

		image.paintIcon(null, g, x, y);

	}

	public void keyPressed(int key) {
	       if (key == KeyEvent.VK_W) {
	            movingUp = true;
	        }
	        if (key == KeyEvent.VK_S) {
	            movingDown = true;
	        }
	        if (key == KeyEvent.VK_A) {
	            movingLeft = true;
	        }
	        if (key == KeyEvent.VK_D) {
	            movingRight = true;
	        }
	}
	
	public void keyReleased(int key) {
        if (key == KeyEvent.VK_W) {
            movingUp = false;
        }
        if (key == KeyEvent.VK_S) {
            movingDown = false;
        }
        if (key == KeyEvent.VK_A) {
            movingLeft = false;
        }
        if (key == KeyEvent.VK_D) {
            movingRight = false;
        }
    }
}