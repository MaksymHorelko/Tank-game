package creation;

import entities.Enemy;
import entities.Entity;
import entities.Player;
import map.Map;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Create {

	private int maxEnemies;
	private int delay = 2500;

	public Create(int maxEnemies) {
		this.maxEnemies = maxEnemies;
	}

	public Player createPlayerTank(Map map) {
		Player player = new Player(200, 550, map);
		player.run();
		return player;
	}

	public void createEnemyTank(ArrayList<Enemy> enemy, Map map) {
		Enemy firstEnemy = new Enemy(200, 0, map);
		firstEnemy.run();
		firstEnemy.keyPressed();

		enemy.add(firstEnemy);

		ActionListener listener = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean ableToSpawn = true;

				Enemy newEnemy = new Enemy(200, 0, map);

				for (Enemy currentEnemy : enemy) {
					if (currentEnemy.collisionHandler.hasCollisionWithObject(currentEnemy, newEnemy)) {
						ableToSpawn = false;
						break;
					}
				}

				if (enemy.size() == 0)
					ableToSpawn = true;

				if (ableToSpawn && enemy.size() < maxEnemies) {

					newEnemy.run();
					newEnemy.keyPressed();
					enemy.add(newEnemy);
				}
			}
		};
		Timer timer = new Timer(delay, listener);
		timer.start();
	}

	public void createBullet(Entity entity, Graphics g) {
		if (entity.isReadyForShoot() && entity.getBullet() == null) {
			entity.createBullet();
			entity.getBullet().run();
		}

		entity.getBullet().draw(g);

		if (!entity.getBullet().collisionHandler.isAbleToMove(entity.getBullet())) {
			entity.deleteBullet();
		}
	}
}
