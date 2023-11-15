package map;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public final class Map {
	private final int[] bricksXPos = { 50, 350, 450, 550, 50, 300, 350, 450, 550, 150, 150, 450, 550, 250, 50, 100, 150,
			550, 250, 350, 450, 550, 50, 250, 350, 550, 50, 150, 250, 300, 350, 550, 50, 150, 250, 350, 450, 550, 50,
			250, 350, 550 };

	private final int[] bricksYPos = { 50, 50, 50, 50, 100, 100, 100, 100, 100, 150, 200, 200, 200, 250, 300, 300, 300,
			300, 350, 350, 350, 350, 400, 400, 400, 400, 450, 450, 450, 450, 450, 450, 500, 500, 500, 500, 500, 500,
			550, 550, 550, 550 };

	private final int[] solidBricksXPos = { 150, 350, 150, 500, 450, 300, 600, 400, 350, 200, 0, 200, 500 };

	private final int[] solidBricksYPos = { 0, 0, 50, 100, 150, 200, 200, 250, 300, 350, 400, 400, 450 };

	private final int[] brickON = new int[42];

	private final ImageIcon breakBrickImage;
	private final ImageIcon solidBrickImage;

	public Map() {
		breakBrickImage = new ImageIcon("breakable_brick.jpg");
		solidBrickImage = new ImageIcon("solid_brick.jpg");

		for (int i = 0; i < brickON.length; i++) {
			brickON[i] = 1;
		}
	}

	public void draw(Component c, Graphics g) {
		for (int i = 0; i < brickON.length; i++) {
			if (brickON[i] == 1) {
				breakBrickImage.paintIcon(c, g, bricksXPos[i], bricksYPos[i]);
			}
		}
	}

	public void drawSolids(Component c, Graphics g) {
		for (int i = 0; i < solidBricksXPos.length; i++) {
			solidBrickImage.paintIcon(c, g, solidBricksXPos[i], solidBricksYPos[i]);
		}
	}

	public boolean checkCollision(int x, int y, int width, int height) {
		for (int i = 0; i < brickON.length; i++) {
			if (brickON[i] == 1) {
				if (intersects(new Rectangle(x, y, width, height),
						new Rectangle(bricksXPos[i], bricksYPos[i], 50, 50))) {
					if (width != 50) {
						brickON[i] = 0;
					}
					return true;
				}
			}
		}
		return false;
	}

	public boolean checkSolidCollision(int x, int y, int width, int height) {
		for (int i = 0; i < solidBricksXPos.length; i++) {
			if (intersects(new Rectangle(x, y, width, height),
					new Rectangle(solidBricksXPos[i], solidBricksYPos[i], 50, 50))) {
				return true;
			}
		}
		return false;
	}

	private boolean intersects(Rectangle rect1, Rectangle rect2) {
		return rect1.intersects(rect2);
	}
}
