package gof;

public class CellsHolder {
	
	static final int WIDTH = 512;
	static final int HEIGHT = 512;
	
	private static Cell[][] cells;
	
	/*
	 * Initializes all the cells in array
	 */
	static void initCells() {
		cells = new Cell[HEIGHT][WIDTH];
		for (int i = 0; i != HEIGHT; ++i) {
			for (int j = 0; j != WIDTH; ++j) {
				cells[i][j] = new Cell(i, j);
			}
		}
		cells[12][11].setAlive(true);
		cells[12][12].setAlive(true);
		cells[13][12].setAlive(true);
		cells[11][17].setAlive(true);
		cells[13][16].setAlive(true);
		cells[13][17].setAlive(true);
		cells[13][18].setAlive(true);
		
		cells[21][23].setAlive(true);
		cells[22][21].setAlive(true);
		cells[22][23].setAlive(true);
		cells[23][22].setAlive(true);
		cells[23][23].setAlive(true);
	}
	
	/*
	 * Returns cell with given coordinates,
	 * while taking care of periodic boundary conditions
	 */
	static Cell getCell(int x, int y) {
		int _x = x % WIDTH;
		int _y = y % HEIGHT;
		return cells[_x][_y];
	}
	
	/*
	 * Iterates over every living cell,
	 * and increments its neighbors neigbourCount
	 */
	static void updateNeigboursCount() {
		for (Cell[] row : cells) {
			for (Cell cell : row) {
				if (cell.isAlive()) {
					cell.updateNeigbours();
				}
			}
		}
	}
	
	
	/*
	 * Decides if cells is meant to be alive or dead,
	 * resets the neighbor count for every cell to 0
	 */
	static void updateAliveStatus() {
		for (Cell[] row : cells) {
			for (Cell cell : row) {
				final int n = cell.getNeigbourCount();
				if (cell.isAlive() && (n != 2 && n != 3)) {
					cell.setAlive(false);
				}
				else if (n == 3) {
					cell.setAlive(true);
				}
				cell.setNeighbourCount(0);
			}
		}
	}
	
	/*
	 * Sets every cell to dead state
	 */
	static void clearAll() {
		
	}
	
	static Cell[][] getCells() {
		return cells;
	}
}