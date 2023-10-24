package tp1.logic;

import java.util.Random;
import java.util.Scanner;

import tp1.logic.gameobjects.UCMShip;
import tp1.logic.gameobjects.UCMLaser;

// TODO implementarlo
public class Game {

	public static final int DIM_X = 9;
	public static final int DIM_Y = 8;
	private int currentCycle;
	private Random rand;
	private Level level;
	private long seed;
	private RegularAlienList regularAliens;
	private UCMShip player;
	private UCMLaser laser;
	private boolean doExit;
	private AlienManager alienManager;

	//TODO fill your code

	private void initGame() {
		
	}
	
	public Random getRandom() {
		//TODO fill your code
		return this.rand;
	}
	
	public Level getLevel() {
		//TODO fill your code
		return this.level;
	}

	
	public void reset() {
		currentCycle=0;
		// TODO implementar el resto
	}
		
	
	
	public int getCycle() {
		//TODO fill your code
		return this.currentCycle;
	}

	
	public void addObject() {
		
	}
	
	public String positionToString(int col, int row) {
		//TODO fill your code
		Position pos = new Position(col, row);
		
		if(pos.equals(player.getPosition())) {
		return player.toString();
		}
		return "";
	}

	
	public boolean isFinished() {
		if (this.getRemainingAliens()==0) {
			return true;
		}
		if (this.player.isAlive() == false) {
			return true;
		}
		return false;
	}
	
	public boolean aliensWin() {
		//TODO fill your code
		return false;
	}
	
	public boolean playerWin() {
		//TODO fill your code
		return false;
	}

	
	public void update() {
		removeDead();
		computerActions();
		laserAutomaticMove();
		automaticMoves();
		
	}
	
	private void removeDead() {
		this.regularAliens.removeDead();
		
	}
	
	private void computerActions() {
		this.regularAliens.computerActions();
	}
	
	private void laserAutomaticMove() {
		
	}
	
	private void automaticMoves() {
		this.regularAliens.automaticMoves();
		
	}
	
	public void  exit() {
		
	}
	
	public String infoToString() {
		StringBuilder string_builder = new StringBuilder();
		
		string_builder.append("Number of cycles: ");
		string_builder.append(currentCycle);
		string_builder.append("\n");
		
		string_builder.append("Life: ");
		string_builder.append(this.player.getLife());
		string_builder.append("\n");
		
		string_builder.append("Points: ");
		string_builder.append(0);
		string_builder.append("\n");
	
		string_builder.append("ShockWave");
		string_builder.append("OFF");
		string_builder.append("\n");
		
		string_builder.append("Remaining aliens: ");
		string_builder.append(this.alienManager.getRemainingAliens());
		
		return string_builder.toString();
	}
	
	public String listOfShips() {
		return this.regularAliens.toString();
	}
	
	public boolean move(Move move) {
		return this.player.move(move);
		
	}
	
	public boolean shootLaser() {
		return this.player.shootLaser();
	}
	
	public void shockWave() {
	}
	
	public void receivePoints() {
		
	}
	
	public int getNumCyclesToMoveOneCell() { 
		
		int velocidad=1;
		
		if (this.level==Level.EASY) {
			velocidad=3;
		}
		
		else if (this.level==Level.HARD) {
			velocidad=2;
		}
		
		else if (this.level==Level.INSANE) {
			velocidad=1;
		}
		
		return (velocidad - (this.currentCycle % velocidad));
		
	}
	
	private void haveLanded() {
		this.alienManager.haveLanded();
	}
	
	public int getRemainingAliens() {
		//TODO fill your code
		return this.alienManager.getRemainingAliens();
	}

	
	public boolean performAttack() {
		
		return this.player.shootLaser();
	}
	
	public void checkAttacksTo() {
		
	}
	
	public String stateToString() {
		//TODO fill your code
		return null;
	}

	
	
	public Game(Level level, long seed) {
		//TODO fill your code
		this.level = level;
		this.seed = seed;
		initGame();
	}

	public void enableLaser() {
		this.laser = null;
		player.enableLaser();	
	}

}
