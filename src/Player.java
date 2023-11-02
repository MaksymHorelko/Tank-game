import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends Entity{

	public Player(int x, int y, int speed, Map map) {
		super(x, y, speed, map, "player_tank_up.png");
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
	            direction = "UP";
	        }
	       else if (key == KeyEvent.VK_S) {
	            movingDown = true;
	            direction = "DOWN";
	        }
	       else if (key == KeyEvent.VK_A) {
	            movingLeft = true;
	            direction = "LEFT";
	        }
	       else  if (key == KeyEvent.VK_D) {
	            movingRight = true;
	            direction = "RIGHT";
	        }
	        if (key == KeyEvent.VK_SPACE) {
	            shooted = true;
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
        if (key == KeyEvent.VK_SPACE) {
            shooted = false;
        }
    }
}