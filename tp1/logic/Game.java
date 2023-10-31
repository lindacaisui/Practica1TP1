package tp1.logic;

import java.util.Random;

import tp1.logic.gameobjects.UCMShip;
import tp1.logic.gameobjects.Ufo;
import tp1.logic.gameobjects.ShockWave;
import tp1.logic.gameobjects.UCMLaser;
import tp1.logic.lists.BombList;
import tp1.logic.lists.DestroyerAlienList;
import tp1.logic.lists.RegularAlienList;
import tp1.view.Messages;

public class Game {

	public static final int DIM_X = 9;
	public static final int DIM_Y = 8;
	private int currentCycle;
	private Random rand;
	private Level level;
	private long seed;
	private RegularAlienList regularAliens;
	private DestroyerAlienList destroyerAliens;
	private Ufo ufo;
	private BombList bombList;
	private UCMShip player;
	private UCMLaser laser;
	private ShockWave shockWave;
	private boolean doExit;
	private AlienManager alienManager;

	private void initGame() {
		this.currentCycle = 0;
		this.rand = new Random(this.seed);
		this.alienManager = new AlienManager(this, this.level);
		this.regularAliens = this.alienManager.initializeRegularAliens();
		this.bombList = new BombList(this.level.getNumDestroyerAliens());
		this.destroyerAliens = this.alienManager.initializeDestroyerAliens(this.bombList);
		this.ufo = new Ufo(this);
		this.player = new UCMShip(this, new Position(Game.DIM_X / 2, Game.DIM_Y - 1));
		this.shockWave = new ShockWave();
		this.doExit = false;
	}
	
	public Random getRandom() {
		return (this.rand);
	}
	
	public Level getLevel() {
		return (this.level);
	}

	
	public void reset() {
		this.initGame();
	}

	public int getCycle() {
		return this.currentCycle;
	}

	
	public void addObject(Object obj) {
		if (obj != null) {
			if (obj.getClass() == UCMLaser.class) {
				this.laser = (UCMLaser)obj;
			}
			if (obj.getClass() == Ufo.class) {
				this.ufo = (Ufo)obj;
			}
		}
	}
	
	public String positionToString(int col, int row) {
		Position pos = new Position(col, row);
		
		if(pos.equals(this.player.getPosition())) {
			return this.player.toString();
		}
		if (this.regularAliens.getObjectInPosition(pos) != null) {
			return (this.regularAliens.getObjectInPosition(pos).toString());
		}
		if (this.destroyerAliens.getObjectInPosition(pos) != null) {
			return (this.destroyerAliens.getObjectInPosition(pos).toString());
		}
		if (this.laser != null && pos.equals(this.laser.getPosition())) {
			return (this.laser.toString());
		}
		if (this.bombList.getObjectInPosition(pos) != null) {
			return (this.bombList.getObjectInPosition(pos).toString());
		}
		if (pos.equals(this.ufo.getPosition())) {
			return (this.ufo.toString());
		}
		return "";
	}

	
	public boolean isFinished() {
		return (this.doExit);
	}
	
	public boolean aliensWin() {
		return (!this.player.isAlive() || this.haveLanded());
	}
	
	public boolean playerWin() {
		return (this.alienManager.allAlienDead());
	}

	
	public void update() {
		this.currentCycle++;
		this.removeDead();
		this.automaticMoves();
		this.computerActions();
		this.checkAttacksTo();
		
		if (this.aliensWin() || this.playerWin()) {
			this.exit();
		}
	}
	
	private void removeDead() {
		this.regularAliens.removeDead();
		this.destroyerAliens.removeDead();
		this.bombList.removeDead();
	}
	
	private void computerActions() {	
		this.regularAliens.computerActions(this.laser);
		this.destroyerAliens.computerActions(this.laser);
		if (this.ufo != null) {
			this.ufo.computerAction();
		}
	}
	
	private void laserAutomaticMove() {
		if (this.laser != null) {
			this.laser.automaticMove();
		}
	}
	
	private void automaticMoves() {
		this.regularAliens.automaticMoves();
		this.destroyerAliens.automaticMoves();
		this.bombList.automaticMoves();
		this.ufo.automaticMove();
		
		this.regularAliens.checkAttacks(this.laser);
		this.destroyerAliens.checkAttacks(this.laser);
		this.bombList.checkAttacks(this.laser);
		this.ufo.checkAttack(this.laser);
		
		this.laserAutomaticMove();
	}
	
	public void exit() {
		this.doExit = true;
	}
	
	public String infoToString() {
		StringBuilder string_builder = new StringBuilder();
		
		string_builder.append("Life: ");
		string_builder.append(this.player.getLife());
		string_builder.append("\n");
		
		string_builder.append("Points: ");
		string_builder.append(this.player.getPoints());
		string_builder.append("\n");
	
		string_builder.append("ShockWave: ");
		string_builder.append(this.shockWave.toString());
		string_builder.append("\n");
		
		return (string_builder.toString());
	}
	
	public String listOfShips() {
		StringBuilder bob = new StringBuilder();
		
		bob.append(Messages.ucmShipDescription(Messages.UCMSHIP_DESCRIPTION, 1, 3));
		bob.append('\n');
		bob.append(Messages.alienDescription(Messages.REGULAR_ALIEN_DESCRIPTION, 5, 0, 2));
		bob.append('\n');
		bob.append(Messages.alienDescription(Messages.DESTROYER_ALIEN_DESCRIPTION, 10, 1, 1));
		bob.append('\n');
		bob.append(Messages.alienDescription(Messages.UFO_DESCRIPTION, 25, 0, 1));
		bob.append('\n');
		
		return (bob.toString());
	}
	
	public boolean move(Move move) {
		return this.player.move(move);
	}
	
	public void addPoints(int points) {
		this.player.addPoints(points);
	}
	
	public boolean shootLaser() {
		return this.player.shootLaser();
	}
	
	public void shockWave() {
		if (this.shockWave.isAvailable()) {
			this.regularAliens.shockWave();
			this.destroyerAliens.shockWave();
		}
	}
	
	public void ufoKilled() {
		this.shockWave.ufoKilled();
	}
	
	public int getNumCyclesToMoveOneCell() { 
		
		int velocidad = 1;
		
		if (this.level == Level.EASY)			{ velocidad = 3; }
		else if (this.level == Level.HARD)		{ velocidad = 2; }
		else if (this.level == Level.INSANE)	{ velocidad = 1; }
		
		return (velocidad - (this.currentCycle % velocidad));
		
	}
	
	private boolean haveLanded() {
		return (this.alienManager.haveLanded());
	}
	
	public int getRemainingAliens() {
		return this.alienManager.getRemainingAliens();
	}

	
	public boolean performAttack() {
		
		return this.player.shootLaser();
	}
	
	public void checkAttacksTo() {
		if (this.bombList.getObjectInPosition(this.player.getPosition()) != null) {
			this.player.receiveAttack(this.bombList.getObjectInPosition(this.player.getPosition()));
		}
	}
	
	public String stateToString() {
		return (this.infoToString());
	}

	
	
	public Game(Level level, long seed) {
		this.level = level;
		this.seed = seed;
		initGame();
	}

	public void enableLaser() {
		this.laser = null;
		player.enableLaser();	
	}

}
