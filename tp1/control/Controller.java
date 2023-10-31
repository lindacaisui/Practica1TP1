package tp1.control;

import static tp1.view.Messages.debug;

import java.util.Scanner;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.view.GamePrinter;
import tp1.view.Messages;

/**
 *  Accepts user input and coordinates the game execution logic
 */
public class Controller {

	private Game game;
	private Scanner scanner;
	private GamePrinter printer;

	public Controller(Game game, Scanner scanner) {
		this.game = game;
		this.scanner = scanner;
		printer = new GamePrinter(game);
	}

	/**
	 * Show prompt and request command.
	 *
	 * @return the player command as words
	 */
	private String[] prompt() {
		System.out.print(Messages.PROMPT);
		String line = scanner.nextLine();
		String[] words = line.toLowerCase().trim().split("\\s+");

		System.out.println(debug(line));

		return words;
	}

	/**
	 * Runs the game logic
	 */
	public void run() {
		//TODO fill your code
		this.printGame();
		while(true) {
			String[] args = prompt();
			
			if (args.length > 0) {
			switch(args[0]) {
			case "m":
			case "move":
				Move move = Move.valueOf(args[1].toUpperCase());
				if(this.game.move(move)) {
					this.game.update();
					this.printGame();
				}
				break;
			case "s":
			case "shoot":
				if (this.game.shootLaser()) {
					this.game.update();
					this.printGame();
				}
				break;
			case "w":
			case "shockwave":
				this.game.shockWave();
				this.game.update();
				this.printGame();
				break;
			case "l":
			case "list":
				this.listShips();
				break;
			case "r":
			case "reset":
				this.resetGame();
				this.printGame();
				break;
			case "e":
			case "exit":
				this.printEndMessage();
				return;
			case "h":
			case "help":
				this.printHelp();
				break;
			case "":
			case "n":
			case "none":
				this.game.update();
				this.printGame();
				break;
			default:
				System.out.println("Comando no válido");
				break;
			}
			
		}
			if (this.game.isFinished()) {
				this.printEndMessage();
				return;
			}
		}
		
	}
	
	private void listShips() {
		System.out.println(this.game.listOfShips());
	}
	
	private void resetGame() {
		game.reset();
		this.printGame();
	}
	
	private void printHelp() {
		System.out.println(Messages.HELP);
	}

	/**
	 * Draw / paint the game
	 */
	private void printGame() {
		System.out.println(printer.toString());
	}
	
	/**
	 * Prints the final message once the game is finished
	 */
	public void printEndMessage() {
		System.out.println(printer.endMessage());
	}
	
}
