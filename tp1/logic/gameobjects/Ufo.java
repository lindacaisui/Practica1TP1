package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;


public class Ufo {

	private boolean enabled;
	private static int ARMOR = 1;
	private int life;
	private int points;
	private Position pos;
	private Game game;
	
	public Ufo(Game game) {
		this.game = game;
		this.pos = null;
		this.enabled = false;
		this.life = this.ARMOR;
		this.points = 25;
	}
	
	public boolean isAlive() {
		return (this.enabled && this.life > 0);
	}
	
	public void automaticMove() {
		if (enabled) {
			this.pos = pos.move(Move.LEFT);
			if (this.pos.isOut()) {
				this.onDelete();
			}
		}
	}

	public void computerAction() {
		if(!enabled && canGenerateRandomUfo()) {
			enable();
		}
	}
	
	private void enable() {
		this.enabled = true;
		this.life = this.ARMOR;
		this.pos = new Position(8, 0);
	}

	public void onDelete() {
		this.enabled = false;
		this.pos = null;
	}
	
	private String getSymbol() {
		return (Messages.UFO_SYMBOL);
	}
	
	@Override
	public String toString() {
		StringBuilder alien = new StringBuilder();

		alien.append(getSymbol());
		alien.append('[');
		alien.append(life);
		alien.append(']');

		return (alien.toString());
	}
	
	public boolean receiveAttack(UCMLaser laser) {
		return (this.receiveDamage(laser.getDamage()));
	}
	
	private boolean receiveDamage(int damage) {
		this.life -= damage;
		this.game.ufoKilled();
		this.game.addPoints(this.points);
		return (life <= 0);
	}
	
	public void checkAttack(UCMLaser laser) {
		if (laser == null || this.pos == null) { return; }
		if (this.pos.equals(laser.getPosition())) {
			laser.performAttack(this);
			laser.receiveDamage(1);
		}
	}
	
	public void shockWave() {
		this.receiveDamage(1);
	}
	
	public Position getPosition() {
		return (this.pos);
	}

	/**
	 * Checks if the game should generate an ufo.
	 * 
	 * @return <code>true</code> if an ufo should be generated.
	 */
	private boolean canGenerateRandomUfo(){
		return (game.getRandom().nextDouble() < game.getLevel().getUfoFrequency());
	}
	
}
