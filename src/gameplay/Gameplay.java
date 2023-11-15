package gameplay;

import entities.Enemy;
import entities.Entity;
import entities.Player;
import map.Map;
import creation.Create;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public final class Gameplay extends JPanel implements ActionListener, KeyListener {

	private boolean play;

	private Map map;
	private Create create;
	private Player player;
	private ArrayList<Enemy> enemies = new ArrayList<>();

	private final int maxEnemies = 7;

	private int score;
	protected int lives;

	private Timer timer;
	private int delay = 1;

	public Gameplay() {
		initializeGame();
	}

	private void initializeGame() {
		score = 0;
		lives = 5;

		map = new Map();

		create = new Create(maxEnemies);

		player = create.createPlayerTank(map);

		create.createEnemyTank(enemies, map);

		setFocusable(true);
		addKeyListener(this);

		timer = new Timer(delay, this);
		timer.start();

		play = true;
	}

	private void reloadGame() {
		enemies.clear();
	}

	public void paint(Graphics g) {
		// draw BG
		drawBackground(g);

		// draw tanks
		drawTanks(g);

		// check collision for everything
		checkCollision();

		// create bullets
		createBullets(g);

		drawBullets(g);

		// check bullet collision for player
		checkBulletCollision();

		// the scores
		info.PrintStats.printStats(score, lives, g);

		// end screen
		if (lives <= 0) {
			endScreen(g);
		}

		g.dispose();
	}

	private void drawBackground(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, 650, 600);
		g.setColor(Color.DARK_GRAY);
		g.fillRect(660, 0, 140, 600);
		map.drawSolids(this, g);
		map.draw(this, g);
	}

	private void endScreen(Graphics g) {
		lives = 0;
		play = false;
		info.PrintStats.endScreen(score, g);

		stopEntities();
	}

	private void drawTanks(Graphics g) {
		// draw player tank
		player.draw(g);

		// draw enemies tank
		for (Enemy enemy : enemies) {
			enemy.draw(g);
		}
	}

	private void createBullets(Graphics g) {
		// player bullet
		if (player.isShooted()) {
			create.createBullet(player, g);
		}

		// enemy bullet
		for (Enemy currentEnemy : enemies) {
			if (currentEnemy.isShooted()) {
				create.createBullet(currentEnemy, g);
			}
		}

	}

	private void drawBullets(Graphics g) {
		// draw bullet for player
		if (player.isShooted()) {
			create.createBullet(player, g);
		}

		// draw bullet for enemies
		for (Enemy currentEnemy : enemies) {
			if (currentEnemy.isShooted()) {
				create.createBullet(currentEnemy, g);
			}
		}
	}

	private void checkBulletCollision() {
		// check bullet collision for player
		if (player.isShooted()) {
			bulletCollision(player);
		}

		// check bullet collision for enemies
		for (Enemy currentEnemy : enemies) {
			if (currentEnemy.isShooted()) {
				bulletCollision(currentEnemy);
			}
		}
	}

	private void stopEntities() {
		player.play = false;
		for (Enemy currentEnemy : enemies) {
			currentEnemy.play = false;
		}
	}

	private void checkCollision() {
		checkCollisionPlayerWithEnemy();

		checkCollisionEnemyWithPlayer();

		checkCollisionEnemyWithEnemy();
	}

	private void checkCollisionPlayerWithEnemy() {
		for (Enemy currentEnemy : enemies) {
			if (player.collisionHandler.hasCollisionWithObject(player, currentEnemy))
				break;
		}
	}

	private void checkCollisionEnemyWithPlayer() {
		for (Enemy currentEnemy : enemies) {
			if (currentEnemy.collisionHandler.hasCollisionWithObject(currentEnemy, player))
				break;
		}
	}

	private void checkCollisionEnemyWithEnemy() {
		for (Enemy currentEnemy : enemies) {
			for (Enemy otherEnemy : enemies) {
				if (currentEnemy != otherEnemy)
					if (currentEnemy.collisionHandler.hasCollisionEnemyWithEnemy(currentEnemy, otherEnemy))
						break;
			}
		}
	}

	private void bulletCollision(Entity entity) {

		boolean isHitEnemy = false;
		boolean isHitPlayer = false;
		int i = -1;

		if (entity instanceof Enemy) {
			if (entity.getBullet().collisionHandler.hasCollisionWithObject(entity.getBullet(), player))
				isHitPlayer = true;

		} else
			for (Enemy currentEnemy : enemies) {
				i++;
				if (entity.getBullet().collisionHandler.hasCollisionWithObject(entity.getBullet(), currentEnemy)) {
					isHitEnemy = true;
					break;
				}
			}

		if (entity.getBullet().collisionHandler.hasCollisionWithMap(entity.getBullet(), entity.getDirection())) {
			entity.deleteBullet();

		} else if (isHitEnemy) {
			score++;
			entity.deleteBullet();
			deleteEnemyTank(enemies.get(i));

		} else if (isHitPlayer) {
			lives--;
			entity.deleteBullet();
			deletePlayerTank();
		}
	}

	private void deletePlayerTank() {
		player = create.createPlayerTank(map);
	}

	private void deleteEnemyTank(Entity entity) {
		entity.play = false;
		enemies.remove(entity);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (play) {
			player.keyPressed(key);
		} else {
			if (key == KeyEvent.VK_R) {
				reloadGame();
				initializeGame();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (play) {
			player.keyReleased(key);

		} else {
			if (key == KeyEvent.VK_R) {
				reloadGame();
				initializeGame();
			}
		}
	}
}