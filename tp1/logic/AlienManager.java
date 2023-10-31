package tp1.logic;

import tp1.logic.gameobjects.DestroyerAlien;
//import tp1.logic.gameobjects.DestroyerAlien;
import tp1.logic.gameobjects.RegularAlien;
import tp1.logic.lists.BombList;
import tp1.logic.lists.DestroyerAlienList;
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
		RegularAlienList list = new RegularAlienList(this.level.getNumRegularAliens());
		int x = 2;
		int y = 1;
		
		this.remainingAliens += this.level.getNumRegularAliens();
		
		for (int i = 0; i < this.level.getNumRowsRegularAliens(); i++) {
			for (int j = 0; j < this.level.getNumRegularAliens() / this.level.getNumRowsRegularAliens(); j++) {
				list.add(new RegularAlien(new Position(x + j, y + i), this.game, this, this.level.getNumCyclesToMoveOneCell()));
			}
		}
		
		return list;
	}

	/**
	 * Initializes the list of destroyer aliens
	 * @return the initial list of destroyer aliens according to the current level
	 */
	protected  DestroyerAlienList initializeDestroyerAliens(BombList bombList) {
		DestroyerAlienList list = new DestroyerAlienList(this.level.getNumDestroyerAliens());
		int x = 3 - this.level.getNumDestroyerAliens() / 4;
		int y = 2 + this.level.getNumRowsRegularAliens() / 2;
		
		this.remainingAliens += this.level.getNumDestroyerAliens();
		
		for (int i = 0; i < this.level.getNumDestroyerAliens(); i++) {
			list.add(new DestroyerAlien(new Position(x + i, y), this.game, this, this.level.getNumCyclesToMoveOneCell(), bombList));
		}
		
		return list;
	}
	// CONTROL METHODS
	
	public int getRemainingAliens() {
		return this.remainingAliens;
	}
	
	public boolean allAlienDead() {
		return (this.remainingAliens == 0);
		
	}
	
	public void alienDead() {
		this.remainingAliens--;
		this.shipNotOnBorder();
	}
	
	public boolean haveLanded() {
		return (this.squadInFinalRow);
	}
	
	public void finalRowReached() {
		this.squadInFinalRow = true;
	}
	
	public void readyToDescend() {
		this.onBorder = true;
	}
	
	public void shipOnBorder() {
		if (!this.onBorder) {
			this.onBorder = true;
			this.shipsOnBorder = this.remainingAliens;
		}
	}
	
	public void shipNotOnBorder() {
		this.shipsOnBorder--;
		if (this.shipsOnBorder < 0) {
			this.shipsOnBorder = 0;
		}
	}
	
	public boolean onBorder() {
		if (this.shipsOnBorder == 0) {
			this.onBorder = false;
		}
		else {
			this.onBorder = true;
		}
		return this.onBorder;
	}
	

}
