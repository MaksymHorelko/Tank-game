package graphics;

import entities.Enemy;
import entities.Entity;
import entities.Player;
import map.Map;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public final class Gameplay extends JPanel implements ActionListener, KeyListener {

	private Map map;
	private Player player;

	private final int maxEnemies = 2;
	private ArrayList<Enemy> enemy = new ArrayList<>();

	private int score = 0;
	protected int lives = 1;

	private Timer timer;
	private int delay = 1;

	public Gameplay() {

		map = new Map();

		player = new Player(200, 550, map);
		if (maxEnemies > 0) {
			createEnemyTank();
		}
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

		for (Enemy enemy : enemy) {
			enemy.draw(g);
		}

		checkCollision();

		// player bullet
		if (player.isShooted()) {
			createBullet(player, g);
		}

		// enemy bullet
		for (Enemy currentEnemy : enemy) {
			if (currentEnemy.isShooted()) {
				createBullet(currentEnemy, g);
			}
		}

		info.PrintStats.printStats(score, lives, g);

		// the scores

		if (lives == 0) {
			g.setColor(Color.white);
			g.setFont(new Font("roboto", Font.BOLD, 60));
			g.drawString("Game Over", 200, 300);
			g.drawString("You Lost!", 220, 380);

			g.setColor(Color.white);
			g.setFont(new Font("robot", Font.BOLD, 30));
			g.drawString("(R to Restart)", 250, 430);
		}

		g.dispose();
	}

	private void checkCollision() {
		for (Enemy currentEnemy : enemy) {
			
			player.collisionHandler.hasCollisionWithObject(player, currentEnemy);
			
			currentEnemy.collisionHandler.hasCollisionWithObject(currentEnemy, player);
			
		}
	}

	private void createBullet(Entity entity, Graphics g) {

		if (entity.isReadyForShoot() && entity.getBullet() == null) {
			entity.createBullet();
		}

		entity.getBullet().draw(g);

		if (!entity.getBullet().collisionHandler.isAbleToMove(entity.getBullet())) {
			entity.deleteBullet();
		}
	}

	private void createEnemyTank() {

		enemy.add(new Enemy(200, 0, map));

		int delay = 2500;
		ActionListener listener = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (enemy.size() < maxEnemies) {
					enemy.add(new Enemy(200, 0, map));
				}
			}
		};
		Timer timer = new Timer(delay, listener);
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
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
