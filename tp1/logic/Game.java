package tp1.logic;

import java.util.Random;

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

	public Game(Level level, long seed) {
		//TODO fill your code
		Position pos = new Position();
		System.out.println(pos.get_col());
		System.out.println(pos.get_fil());
	}
	
	private void initGame() {
		
	}
	
	public void reset() {
		currentCycle=0;
		// TODO implementar el resto
	}
	
	

	public String stateToString() {
		//TODO fill your code
		return null;
	}

	public int getCycle() {
		//TODO fill your code
		return this.currentCycle;
	}

	public int getRemainingAliens() {
		//TODO fill your code
		return 0;
	}

	public String positionToString(int col, int row) {
		//TODO fill your code
		return "      ";
	}

	public boolean playerWin() {
		//TODO fill your code
		return false;
	}

	public boolean aliensWin() {
		//TODO fill your code
		return false;
	}

	public void enableLaser() {
		//TODO fill your code		
	}

	public Random getRandom() {
		//TODO fill your code
		return this.rand;
	}

	public Level getLevel() {
		//TODO fill your code
		return this.level;
	}

}
