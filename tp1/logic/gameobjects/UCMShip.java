package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

public class UCMShip {
	private static int ARMOR = 3;
	private static int DAMAGE = 1;
	private Position pos;
	private int life;
	private Game game;
	private boolean canShoot;
	
	public UCMShip(Game game, Position pos, int life) {
		this.game = game;
		this.pos = pos;
		this.life = life;
		this.canShoot = true;
	}
	
	public boolean isAlive() {
		return (this.life > 0);
	}
	
	public int getLife() {
		return (this.life);
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
		if (this.pos.get_col() < 0 || this.pos.get_col() >= this.game.DIM_X) {
			return (true);
		}
		if (this.pos.get_fil() < 0 || this.pos.get_fil() >= this.game.DIM_Y) {
			return (true);
		}
		return (false);
	}
	
	private String getSymbol() {
		return (Messages.UCMSHIP_SYMBOL);
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
	
	protected int getDamage() {
		return (this.DAMAGE);
	}
	
	public void onDelete() {
		
	}
	
	public void automaticMove() {
		
	}
	
	public void computerAction() {
		
	}
	
	public boolean move(Move move) {
		this.pos = new Position(pos.get_col() + move.getX(), pos.get_fil() + move.getY());
		if (this.isOut()) {
			System.out.print("No es un movimiento valido!\n");
			this.pos = new Position(pos.get_col() - move.getX(), pos.get_fil() - move.getY());
			
			return (false);
		}
		
		return (true);
	}
	
	public void enableLaser() {
		this.canShoot = true;
	}
	
	public boolean shootLaser() {
		if (this.canShoot == false) {
			return (false);
		}
		return (true);
	}
	
	public void receiveAttack() {
		
	}

	

}
