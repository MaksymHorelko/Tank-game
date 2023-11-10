package graphics;

import entities.Enemy;
import entities.Entity;
import entities.Player;
import map.Map;

import javax.swing.*;

import creation.Create;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public final class Gameplay extends JPanel implements ActionListener, KeyListener {

	private Map map;
	private Player player;

	private final int maxEnemies = 5;
	private ArrayList<Enemy> enemy = new ArrayList<>();

	private int score = 0;
	Create create;
	protected int lives = 5;

	private Timer timer;
	private int delay = 1;

	public Gameplay() {

		map = new Map();
		
		 create = new Create();
		create.enemyTank(enemy, map);
		
		player = new Player(200, 550, 5, map);
		createEnemyTank();

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
		
		for(Enemy enemy : enemy) {
			enemy.draw(g);
		}

		// player bullet
		if (player.isShooted()) {
			createBullet(player, g);
		}

		/*
		 * // enemy bullet if(player.isShooted()) { createBullet(player, g); }
		 */

		info.PrintStats.printStats(score, lives, g);
		// the scores

		if (lives == 0) {
			g.setColor(Color.white);
			g.setFont(new Font("serif", Font.BOLD, 60));
			g.drawString("Game Over", 200, 300);
			g.drawString("You Lost!", 220, 380);

			g.setColor(Color.white);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("(Space to Restart)", 230, 430);
		}

		g.dispose();
	}

	private void createBullet(Entity entity, Graphics g) {

		if (entity.isReadyForShoot() && entity.getBullet() == null) {
			player.createBullet();
		}
		entity.getBullet().draw(g);

		if (!entity.getBullet().isOutOfBounds()) {
			entity.dellBullet();

		}
	}
	
	private void createEnemyTank() {
		int delay = 2000;
		ActionListener listener = new AbstractAction() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        if(enemy.size() < maxEnemies) {
		        	enemy.add(new Enemy(200, 0, 5, map));
		        }
		    }
		};
		Timer timer = new Timer(delay, listener);
		timer.start();
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
