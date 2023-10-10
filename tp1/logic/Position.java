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
	public Position(Position pos) {
		this.col = pos.col;
		this.row = pos.row;
	}
	public int get_col() {
		return (this.col);
	}
	
	public int get_fil() {
		return (this.row);
	}
	
	public Position add(int i, int j) {
		return new Position(col + i, row + j);	
	}
	
	public Position move(Move mov) {
		return new Position(col + mov.getX(), row + mov.getY()); 
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null) return false;
		if(getClass() !=obj.getClass()) return false;
		
		// obj es Position
		Position other = (Position) obj;
		return (col == other.col && row == other.row);
	}
	
}
