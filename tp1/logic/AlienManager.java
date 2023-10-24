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
	}
		
	// INITIALIZER METHODS
	
	/**
	 * Initializes the list of regular aliens
	 * @return the initial list of regular aliens according to the current level
	 */
	protected RegularAlienList initializeRegularAliens() {
		//TODO fill your code
		return null;
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
