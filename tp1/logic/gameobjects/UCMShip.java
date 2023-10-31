package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

public class UCMShip {
	private final static int ARMOR = 3;
	private final static int DAMAGE = 1;
	private Position pos;
	private int life;
	private int points;
	private Game game;
	private boolean canShoot;
	
	public UCMShip(Game game, Position pos) {
		this.game = game;
		this.pos = pos;
		this.life = this.ARMOR;
		this.points = 0;
		this.canShoot = true;
	}
	
	public boolean isAlive() {
		return (this.life > 0);
	}
	
	public int getLife() {
		return (this.life);
	}
	
	public int getPoints() {
		return (this.points);
	}
	
	public void addPoints(int points) {
		this.points += points;
	}
	
	public void die() {
		this.life = 0;
	}
	
	public boolean isOnPosition(Position position) {
		return (this.pos.equals(position));
	}
	
	public Position getPosition() {
		return (this.pos);
	}
	
	public void receiveDamage(int damage) {
		this.life -= damage;
	}
	
	public boolean isOut() {
		return (this.pos.isOut());
	}
	
	private String getSymbol() {
		if (this.isAlive()) { return (Messages.UCMSHIP_SYMBOL); }
		else { return (Messages.UCMSHIP_DEAD_SYMBOL); }
	}
	
	public String toString() {
		return (this.getSymbol());
	}
	
	public String stateToString() {
		String state = new String(this.getInfo());
		
		return (state);
	}
	
	protected String getInfo() {
		return (String.format("%s: Harm: %d - Shield: %d\n", this.getDescription(), this.getDamage(), this.getLife()));
	}
	
	protected String getDescription() {
		return (Messages.UCMSHIP_DESCRIPTION);
	}
	
	public int getDamage() {
		return (this.DAMAGE);
	}
	
	public void onDelete() {
		this.pos = null;
	}
	
	public boolean move(Move move) {
		if (this.pos.move(move).isOut()) {
			System.out.print("No es un movimiento valido!\n");
			return (false);
		}
		this.pos = this.pos.move(move);
		
		return (true);
	}
	
	public void enableLaser() {
		this.canShoot = true;
	}
	
	public boolean shootLaser() {
		if (this.canShoot == false) {
			return (false);
		}
		this.game.addObject(new UCMLaser(Move.UP, this.game, this.pos));
		this.canShoot = false;
		return (true);
	}
	
	public void receiveAttack(Bomb bomb) {
		this.receiveDamage(bomb.getDamage());
	}

	

}
