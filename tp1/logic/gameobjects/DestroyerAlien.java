package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.Game;
import tp1.logic.Level;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.logic.lists.BombList;
import tp1.view.Messages;

public class DestroyerAlien {
	private static int ARMOR = 1;
	private Position pos;
	private int life;
	private Game game;
	private Move dir;
	private int cyclesToMove;
	private int speed;
	private int points;
	private AlienManager alienManager;
	private boolean canShoot;
	private BombList bombList;
	private double shotFreq;

	public DestroyerAlien(Position pos, Game game, AlienManager manager, int speed, BombList bombList) {
		this.pos = pos;
		this.game = game;
		this.alienManager = manager;
		this.life = this.ARMOR;
		this.dir = Move.LEFT;
		this.speed = speed;
		this.points = 10;
		this.cyclesToMove = speed;
		this.canShoot = true;
		this.bombList = bombList;
		this.shotFreq = this.game.getLevel().getShootFrequency();
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
		return (Messages.DESTROYER_ALIEN_SYMBOL);
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
		return (this.getDescription());
	}

	private String getDescription() {
		return (Messages.DESTROYER_ALIEN_DESCRIPTION);
	}

	public void onDelete() {
		this.alienManager.alienDead();
		this.game.addPoints(this.points);
	}

	public void computerActions() {
		double rand = this.game.getRandom().nextDouble();
		if (rand < this.shotFreq) {
			this.shootBomb();
		}
	}

	public void automaticMove() {
		if (this.cyclesToMove != 0) {
			this.cyclesToMove--;
			return;
		}
		if (this.alienManager.onBorder()) {
			this.descent();
			if (this.dir == Move.LEFT) {
				this.dir = Move.RIGHT;
			} else {
				this.dir = Move.LEFT;
			}
		} else {
			this.performMovement(dir);
		}
		this.cyclesToMove = this.speed - 1;
		if (this.pos.get_fil() == this.game.DIM_Y - 1) {
			this.alienManager.finalRowReached();
		}
	}

	public void readyToDescend() {
		if (this.isInBorder() && this.pos.move(this.dir).isOut()) {
			this.alienManager.shipOnBorder();
		}
	}

	private boolean isInBorder() {
		return (this.pos.get_col() == 0 || this.pos.get_col() == Game.DIM_X - 1);
	}

	private void descent() {
		this.performMovement(Move.DOWN);
		this.alienManager.shipNotOnBorder();
	}

	public boolean receiveAttack(UCMLaser laser) {
		return (this.receiveDamage(laser.getDamage()));
	}

	public void enableBomb() {
		this.canShoot = true;
	}

	public boolean shootBomb() {
		if (this.canShoot == false) {
			return (false);
		}
		System.out.println("Shooting bomb");
		this.bombList.add(new Bomb(Move.DOWN, this.pos, this));
		this.canShoot = false;
		return (true);
	}

	public int getcyclesToMove() {
		return this.cyclesToMove;
	}
}
