package gof;

public class Cell {
	private int xPos;
	private int yPos;
	
	
	private boolean alive = false;
	private int lifetime = 0;
	private int neigbourCount = 0;
	
	
	// The cell by default is dead
	Cell(int x, int y) {
		xPos = x;
		yPos = y;
	}
	
	/*
	 * Returns neighbouring cell that is in position
	 * (dx, dy) with respect to this cell
	 */
	Cell getNeigbour(int dx, int dy) {
		
	}
	
	
	/*
	 * Takes every neigbour of this cell
	 * and increments its neighbour count by 1
	 */
	void updateNeigbours() {
		
	}
	
	
	/*
	 * Tells if the cell is alive
	 */
	boolean isAlive() {
		return alive;
	}
	
	
	
}
