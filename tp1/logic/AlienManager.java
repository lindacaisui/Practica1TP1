package tp1.logic;

//import tp1.logic.gameobjects.DestroyerAlien;
import tp1.logic.gameobjects.RegularAlien;
//import tp1.logic.lists.DestroyerAlienList;
import tp1.logic.lists.RegularAlienList;

/**
 * 
 * Manages alien initialization and
 * used by aliens to coordinate movement
 *
 */
public class AlienManager {
	
	private Level level;
	private Game game;
	private int remainingAliens;
	
	private boolean squadInFinalRow;
	private int shipsOnBorder;
	private boolean onBorder;
	

	public AlienManager(Game game, Level level) {
		this.level = level;
		this.game = game;
		this.remainingAliens = 0;
		
		this.squadInFinalRow = false;
		this.shipsOnBorder = 0;
		this.onBorder = false;
	}
		
	// INITIALIZER METHODS
	
	/**
	 * Initializes the list of regular aliens
	 * @return the initial list of regular aliens according to the current level
	 */
	protected RegularAlienList initializeRegularAliens() {
		RegularAlienList list = new RegularAlienList(Game.DIM_X * Game.DIM_Y);
		Position start_pos = new Position(3, 1);
		
		if (this.level == Level.EASY) {
			for (int i = 0; i < 4; i++) {
				list.add(new RegularAlien(new Position(start_pos), this.game, this, 3));
				start_pos = start_pos.move(Move.RIGHT);
			}
			
			
		}
		
		if (this.level == Level.HARD) {
			for (int i = 0; i < 4; i++) {
				list.add(new RegularAlien(new Position(start_pos), this.game, this, 2));
				start_pos = start_pos.move(Move.RIGHT);
			}
			start_pos = start_pos.move(Move.DOWN);
			for (int i = 0; i < 4; i++) {
				start_pos = start_pos.move(Move.LEFT);
				list.add(new RegularAlien(new Position(start_pos), this.game, this, 2));
			}
		}
		
		if (this.level == Level.INSANE) {
			for (int i = 0; i < 4; i++) {
				list.add(new RegularAlien(new Position(start_pos), this.game, this, 1));
				start_pos = start_pos.move(Move.RIGHT);
			}
			start_pos = start_pos.move(Move.DOWN);
			for (int i = 0; i < 4; i++) {
				start_pos = start_pos.move(Move.LEFT);
				list.add(new RegularAlien(new Position(start_pos), this.game, this, 1));
			}
		}
		
		return list;
	}

	/**
	 * Initializes the list of destroyer aliens
	 * @return the initial list of destroyer aliens according to the current level
	 */
//	protected  DestroyerAlienList initializeDestroyerAliens() {
//		//TODO fill your code
//	}

	
	// CONTROL METHODS
	
	public int getRemainingAliens() {
		
		return this.remainingAliens;
	}
	
	public boolean allAlienDead() {
		if (remainingAliens == 0)
			return true;
		else 
			return false;
		
	}
	
	public void alienDead() {
		
	}
	
	public void haveLanded() {
		
	}
	
	public boolean finalRowReached() {
		
		return this.squadInFinalRow;
	}
	
	public void readyToDescend() {
		
	}
	
	public int shipOnBorder() {
		if(!onBorder) {
			onBorder = true;
			shipsOnBorder = remainingAliens;
		}
		return this.shipsOnBorder;
	}
	
	
	public boolean onBorder() {
		
		return this.onBorder;
	}
	

}
