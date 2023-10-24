package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

/**
 * 
 * Class that represents the laser fired by {@link UCMShip}
 *
 */
public class UCMLaser {
	
	//TODO fill your code
	private final static int DAMAGE = 1;
	private final static int ARMOR = 1;
	private Position pos;
	private int life;
	private Move dir;
	private Game game;

	public UCMLaser(Move dir, Game game, Position pos) {
		this.dir = dir;
		this.game = game;
		this.life = this.ARMOR;
		this.pos = pos;
	}

	public boolean isAlive() {
		return (!isOut());
	}
	
	public int getLife() {
		
		return this.life;
	}
	
	private void die() {
		this.onDelete();
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
		return (Messages.LASER_SYMBOL);
	}

	public String toString() {
		return (getSymbol());
	}

	public int getDamage() {
		return (this.DAMAGE);
	}


	/**
	 *  Method called when the laser disappears from the board
	 */
	public void onDelete() {
		game.enableLaser();
	}

	/**
	 *  Implements the automatic movement of the laser	
	 */
	public void automaticMove () {
		this.performMovement(dir);
		if(this.isOut()) {
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
	public boolean performAttack(RegularAlien other) {
		//TODO fill your code
		return false;
	}

	/**
	 * Method that implements the attack by the laser to a destroyer alien.
	 * It checks whether both objects are alive and in the same position.
	 * If so call the "actual" attack method {@link weaponAttack}.
	 * @param other the destroyer alien possibly under attack
	 * @return <code>true</code> if the alien has been attacked by the laser.
	 */

	/*
	public boolean performAttack(DestroyerAlien other) {
		//TODO fill your code
		return false;
	}
	*/
	
	//TODO fill your code


	//ACTUAL ATTACK METHODS
	

	/**
	 * 
	 * @param other regular alien under attack by the laser
	 * @return always returns <code>true</code>
	 */
	private boolean weaponAttack(RegularAlien other) {
		return other.receiveAttack(this);	
	}

	//TODO fill your code


	// RECEIVE ATTACK METHODS
	
	/**
	 * Method to implement the effect of bomb attack on a laser
	 * @param weapon the received bomb
	 * @return always returns <code>true</code>
	 */
	/*
	public boolean receiveAttack(Bomb weapon) {
		receiveDamage(weapon.getDamage());
		return true;
	}
	*/

}
