package tp1.logic;

/**
 * 
 * Immutable class to encapsulate and manipulate positions in the game board
 * 
 */
public class Position {

	private int col;
	private int row;

	//TODO fill your code
	public Position() {
		this.col = 0;
		this.row = 0;	
	}
	
	public Position(int column, int row) {
		this.col = column;
		this.row = row;
	}
	
	public int get_col() {
		return (this.col);
	}
	
	public int get_fil() {
		return (this.row);
	}
	
	public boolean equals(Position pos) {
		return (this.col == pos.col && this.row == pos.row);
	}
	
	public Position clone() {
		return (new Position(this.col, this.row));
	}
}
