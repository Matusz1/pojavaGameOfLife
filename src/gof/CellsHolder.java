package gof;

public class CellsHolder {
	
	static final int WIDTH = 512;
	static final int HEIGHT = 512;
	
	private static Cell[][] cells;
	
	/*
	 * Initializes all the cells in array
	 */
	static void initCells() {
		
	}
	
	/*
	 * Returns cell with given coordinates,
	 * while taking care of periodic boundry conditions
	 */
	static void getCell(int x, int y) {
		
	}
	
	/*
	 * Iterates over every living cell,
	 * and increments its neigbours neigbourCount
	 */
	static void updateNeigboursCount() {
		
	}
	
	
	/*
	 * Decides if cells is meant to be alive or dead,
	 * resets the neighbour count for every cell to 0
	 */
	static void updateAliveStatus() {
		
	}
	
	/*
	 * Sets every cell to dead state
	 */
	static void clearAll() {
		
	}
	
}
