package info;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public final class PrintStats {
	public static void printStats(int score, int lives, Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 15));
		g.drawString("Score", 710, 30);
		g.drawString(score + "", 725, 60);

		g.drawString("Lives", 700, 150);
		g.drawString("Player:  " + lives, 670, 180);
	}

	public static void endScreen(int score, Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("roboto", Font.BOLD, 60));
		g.drawString("Game Over", 200, 200);
		g.drawString("You Lost!", 220, 280);
		g.drawString("Your score: " + score, 180, 380);

		g.setColor(Color.white);
		g.setFont(new Font("robot", Font.BOLD, 30));
		g.drawString("(R to Restart)", 250, 430);
	}
}
