package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.logic.lists.BombList;
import tp1.view.Messages;

public class Bomb {
	private final static int DAMAGE = 1;
	private final static int ARMOR = 1;
	private Position pos;
	private int life;
	private Move dir;
	private DestroyerAlien alien;

	public Bomb(Move dir, Position pos, DestroyerAlien alien) {
		this.dir = dir;
		this.life = this.ARMOR;
		this.pos = pos;
		this.alien = alien;
	}

	public boolean isAlive() {
		return (!isOut() && this.life > 0);
	}
	
	public int getLife() {
		return this.life;
	}
	
	private void die() {
		this.alien.enableBomb();
	}
	
	public boolean isOnPosition(Position pos) {
		return (this.pos.equals(pos));
	}

	public Position getPosition() {
		return (this.pos);
	}

	public void receiveDamage(int damage) {
		this.life -= damage;
		if (this.life <= 0) {
			this.die();
		}
	}
	
	public boolean isOut() {
		return (this.pos.isOut());
	}
	
	public void performMovement(Move dir) {
		this.pos = this.pos.move(dir);
	}

	private String getSymbol() {
		return (Messages.BOMB_SYMBOL);
	}

	public String toString() {
		return (this.getSymbol());
	}

	public int getDamage() {
		return (this.DAMAGE);
	}

	public void automaticMove () {
		this.performMovement(dir);
		if(this.isOut()) {
			this.life = 0;
			this.die();
		}
	}

	
	// PERFORM ATTACK METHODS

	/**
	 * Method that implements the attack by the laser to a regular alien.
	 * It checks whether both objects are alive and in the same position.
	 * If so call the "actual" attack method {@link weaponAttack}.
	 * @param other the regular alien possibly under attack
	 * @return <code>true</code> if the alien has been attacked by the laser.
	 */
	public boolean performAttack(UCMShip player) {
		if (player.isAlive() && this.pos.equals(player.getPosition())) {
			return (this.weaponAttack(player));
		}
		return false;
	}

	private boolean weaponAttack(UCMShip player) {
		player.receiveAttack(this);
		return (true);
	}

}
