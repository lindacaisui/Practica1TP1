package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

/**
 * 
 * Class representing a regular alien
 *
 */
public class RegularAlien {

	//TODO fill your code
	private static int ARMOR = 2;
	private Position pos;
	private int life;
	private Game game;
	private Move dir;
	private int cyclesToMove;
	private int speed;
	private AlienManager alienManager;

	public RegularAlien(Position pos, Game game, AlienManager manager, int speed) {
		this.pos = pos;
		this.game = game;
		this.alienManager = manager;
		this.life = this.ARMOR;
		this.dir = Move.LEFT;
		this.speed = speed;
		this.cyclesToMove = speed;
	}

	public boolean isAlive() {
		return (life > 0);
	}
	
	public int getLife() {
		return (this.life);
	}
	
	public void die() {
		this.onDelete();
	}
	
	public boolean isOnPosition(Position pos) {
		return (this.pos.equals(pos));
	}
	
	public Position getPosition() {
		return (this.pos);
	}
	
	public boolean receiveDamage(int damage) {
		this.life -= damage;
		return (life <= 0);
	}
	
	public boolean isOut() {
		return (this.pos.isOut());
	}
	
	public boolean isInFinalRow() {
		return (this.pos.get_fil() == Game.DIM_Y - 1);
	}
	
	public void performMovement(Move dir) {
		this.pos = this.pos.move(dir);		
	}
	
	private String getSymbol() {
		return (Messages.REGULAR_ALIEN_SYMBOL);
	}
	
	public String toString() {
		StringBuilder alien = new StringBuilder();
		
		alien.append(getSymbol());
		alien.append('[');
		alien.append(life);
		alien.append(']');
		
		return (alien.toString());
	}
	
	public String getInfo() {
		StringBuilder info = new StringBuilder();
		
		info.append(this.getDescription());
		
		return (info.toString());
	}
	
	private String getDescription() {
		return (Messages.REGULAR_ALIEN_DESCRIPTION);
	}
	
	public void computerAction() {
		if (this.cyclesToMove == 0) {
			this.automaticMove();
			this.cyclesToMove = this.speed;
		}
		else {
			this.cyclesToMove--;
		}
	}
	
	public void onDelete() {
		this.alienManager.alienDead();
	}
	
	public void automaticMove() {
		if (this.readyToDescend()) {
			this.descent();
			if (this.dir == Move.LEFT) { this.dir = Move.RIGHT; }
			if (this.dir == Move.RIGHT) { this.dir = Move.LEFT; }
		}
		else {
			this.performMovement(dir);
		}
	}
	
	private boolean readyToDescend() {
		return (this.isInBorder() && this.pos.move(dir).isOut());
	}
	
	private boolean isInBorder() {
		return (this.pos.get_col() == 0 || this.pos.get_col() == Game.DIM_X - 1);
	}

	private void descent() {
		this.performMovement(Move.DOWN);
	}
	
	public boolean receiveAttack(UCMLaser laser) {
		return (this.receiveDamage(laser.getDamage()));
	}

	public int getcyclesToMove() {
		return this.cyclesToMove;
	}
}