package info;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public final class PrintStats {
	public static void printStats(int playerScore, int lives, Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 15));
		g.drawString("Score", 710, 30);
		g.drawString(playerScore + "", 725, 60);

		g.drawString("Lives", 700, 150);
		g.drawString("Player:  " + lives, 670, 180);
	}
}
