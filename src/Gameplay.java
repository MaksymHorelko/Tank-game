import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Gameplay extends JPanel implements ActionListener, KeyListener {
	private Map map;
	private Player player;
	private Bullet bullet;

	private int playerScore = 0;
	protected int enemyScore = 0;

	protected int lives = 5;

	private Timer timer;
	private int delay = 8;

	private boolean play = true;

	public Gameplay() {

		map = new Map();

		player = new Player(200, 550, 5, map);

		setFocusable(true);
		addKeyListener(this);

		timer = new Timer(delay, this);
		timer.start();
	}

	public void paint(Graphics g) {
		// play background
		g.setColor(Color.black);
		g.fillRect(0, 0, 650, 600);

		// right side background
		g.setColor(Color.DARK_GRAY);
		g.fillRect(660, 0, 140, 600);

		// draw solid bricks
		map.drawSolids(this, g);

		// draw Breakable bricks
		map.draw(this, g);

		player.draw(g);

//		if(player.shooted) {
//			bullet = new Bullet(player.getOX(), player.getOY(), map, player.getDirection());
//			bullet.draw(g);
//			bullet.moving();
//		}
//	
		
		
		// the scores
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 15));
		g.drawString("Scores", 700, 30);
		g.drawString("Player | | Enemy", 670, 60);

		g.drawString("" + playerScore, 670, 90); // 1

		g.drawString("" + enemyScore, 730, 90); // 2

		g.drawString("Lives", 700, 150);
		g.drawString("Player:  " + lives, 670, 180);

		if (lives == 0) {
			g.setColor(Color.white);
			g.setFont(new Font("serif", Font.BOLD, 60));
			g.drawString("Game Over", 200, 300);
			g.drawString("Player 2 Won", 180, 380);
			play = false;
			g.setColor(Color.white);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("(Space to Restart)", 230, 430);
		}

		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		repaint();
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		player.keyPressed(key);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		player.keyReleased(key);
	}
}
