package tp1.logic;

import java.util.Random;

import tp1.logic.gameobjects.UCMShip;

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
		player = new UCMShip(this,new Position(DIM_X / 2, DIM_Y - 1));
		
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

	
	public void isFinished() {
		
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
		
	}
	
	private void removeDead() {
		
	}
	
	private void computerActions() {
		
	}
	
	private void laserAutomaticMove() {
		
	}
	
	private void automaticMoves() {
		
	}
	
	public void  exit() {
		
	}
	
	public void infoToString() {
		
	}
	
	public void listOfShips() {
		
	}
	
	public void move() {
		
	}
	
	public void shootLaser() {
		
	}
	
	public void shockWave() {
		
	}
	
	public void receivePoints() {
		
	}
	
	public void getNumCyclesToMoveOneCell() {
		
	}
	
	private void haveLanded() {
		
	}
	
	public int getRemainingAliens() {
		//TODO fill your code
		return 0;
	}

	
	public void performAttack() {
		
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
		//TODO fill your code		
	}

}
