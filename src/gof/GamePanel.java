package gof;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 4802230154979709652L;
	
	// Defines the height and width of single cell
	private int boxSize = 20;
	
	// If true cells are colored according to their lifetime
	private boolean colorCells = false;
	
	// Offset for drawing the cells
	private int majorX = 0;
	private int majorY = 0;
	private int minorX = 0;
	private int minorY = 0;
	
	// For moving purpose
	private int lastMousePosX = 0;
	private int lastMousePosY = 0;
	
	// Current mouse position
	private int xMousePos = 0;
	private int yMousePos = 0;
	
	GamePanel() {
		super();
		
		this.addMouseWheelListener(new MouseWheelListener() {
			
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				
				// Befor zooming we move top-left corner to mouse position
				minorX += xMousePos;
				minorY += yMousePos;
				fixOffsets();
				
				// Now we can safely zoom
				int notches = e.getWheelRotation();
				// if cases for quicker zoom for bigger box sizes
				int newBoxSize = boxSize - notches;
				if (boxSize > 20) newBoxSize -= notches*4;
				if (boxSize > 45) newBoxSize -= notches*5;
				if (boxSize > 75) newBoxSize -= notches*5;
				boxSize = newBoxSize;
				if (boxSize < 1) boxSize = 1;
				if (boxSize > 100) boxSize = 100;
				
				
				
				// Finally restore the top-left corner to its positions
				minorX -= xMousePos;
				minorY -= yMousePos;
				fixOffsets();
				
				GamePanel.this.repaint();
			}
		});
		
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				lastMousePosX = e.getX();
				lastMousePosY = e.getY();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (StructuresDialog.getStatus()) {
					drawStructure(StructuresDialog.getValue());
				}
				else {
					Cell cell = getCellAtMousePosition();
					if (cell.isAlive())
						cell.kill();
					else
						cell.revive();
				}
				
				repaint();
			}
		});
		
		this.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				xMousePos = e.getX();
				yMousePos = e.getY();
				repaint();
			}
			
			/* TODO - an idea: to draw new cells 
			 * by a quickfire when holding a particular keyboard key?? */
			@Override
			public void mouseDragged(MouseEvent e) {
				xMousePos = e.getX();
				yMousePos = e.getY();
				
				// Determine offset from mouse drag
				minorX -= xMousePos - lastMousePosX;
				minorY -= yMousePos - lastMousePosY;
				lastMousePosX = xMousePos;
				lastMousePosY = yMousePos;
				
				fixOffsets();				
				repaint();
			}
		});
	}
	
	
	/*
	 * Recalculates major offsets, used after moving or zooming
	 */
	private void fixOffsets() {
		while (minorX >= boxSize) {
			minorX -= boxSize;
			majorX += 1;
		}
		while (minorX < 0) {
			minorX += boxSize;
			majorX -= 1;
		}
		while (minorY >= boxSize) {
			minorY -= boxSize;
			majorY += 1;
		}
		while (minorY < 0) {
			minorY += boxSize;
			majorY -= 1;
		}
	}
	
	
	/*
	 * Based on the minor and major offsets returns cell that
	 * lies on the screen at screen coords x, y
	 */
	public Cell getCellAtPosition(int x, int y) {
		final int _x = (x + minorX) / boxSize + majorX;
		final int _y = (y + minorY) / boxSize + majorY;
		return CellsHolder.getCell(_x, _y);
	}
	
	
	
	/*
	 * Returns cell under mouse position
	 */
	public Cell getCellAtMousePosition() {
		return getCellAtPosition(xMousePos, yMousePos);
	}
	
	
	
	public void drawGrid(Graphics g, int n) {
		final int width = this.getWidth();
		final int height = this.getHeight();
		
		// Drawing Horizontal lines
		for (int i = (boxSize - minorY) - (majorY % n)*boxSize; i < height; i += n*boxSize) {
			g.drawLine(0, i+1, width, i+1);
		}
		
		// Drawing Vertical lines
		for (int i = (boxSize - minorX) - (majorX % n)*boxSize; i < width; i += n*boxSize) {
			g.drawLine(i+1, 0, i+1, height);
		}
	}
	
	
	public void drawMinorGrid(Graphics g) {
		final int width = this.getWidth();
		final int height = this.getHeight();
		
		// Drawing Horizontal lines
		for (int i = (boxSize - minorY); i < height; i += boxSize) {
			g.drawLine(0, i+1, width, i+1);
		}
		
		// Drawing Vertical lines
		for (int i = (boxSize - minorX); i < width; i += boxSize) {
			g.drawLine(i+1, 0, i+1, height);
		}
	}
	
	
	
	public void drawMajorGrid(Graphics g) {
		final int width = this.getWidth();
		final int height = this.getHeight();
		
		// Drawing Horizontal lines
		for (int i = (boxSize - minorY) - (majorY % 10)*boxSize; i < height; i += 10*boxSize) {
			g.drawLine(0, i+1, width, i+1);
		}
		
		// Drawing Vertical lines
		for (int i = (boxSize - minorX) - (majorX % 10)*boxSize; i < width; i += 10*boxSize) {
			g.drawLine(i+1, 0, i+1, height);
		}
	}
	
	
	
	
	// For highlighting the structure to be placed
	public void highlightStructure(Graphics g) {
		g.setColor(Color.gray);
		/* Possibly-helpful-in-future formula for upper left pixel 
		 * to start drawing a box that is below the mouse */
		final int xRect = (int)Math.floor((xMousePos - (int)minorX%boxSize - boxSize)/boxSize)*boxSize + (int)minorX%boxSize + boxSize;
		final int yRect = (int)Math.floor((yMousePos - (int)minorY%boxSize - boxSize)/boxSize)*boxSize + (int)minorY%boxSize + boxSize;
		switch (StructuresDialog.getValue()) {
		case "Pond":
			for (int i = 0; i < Structure.pond.getVY().length; i++)
				g.fillRect(xRect + (Structure.pond.getVX()[i] - 1)*boxSize + 2, yRect + (Structure.pond.getVY()[i] - 1)*boxSize + 2, boxSize - 4, boxSize - 4);
			break;
		case "Glider":
			for (int i = 0; i < Structure.glider.getVY().length; i++)
				g.fillRect(xRect + (Structure.glider.getVX()[i] - 2)*boxSize + 2, yRect + (Structure.glider.getVY()[i] - 2)*boxSize + 2, boxSize - 4, boxSize - 4);
			break;
		case "Glider Spawner":
			//-14 like -2 shifts the mouse in relation to the displayed structure by vector [14,2]. Elsewhere likewise.
			for (int i = 0; i < Structure.gliderSpawner.getVY().length; i++)
				g.fillRect(xRect + (Structure.gliderSpawner.getVX()[i] - 14)*boxSize + 2, yRect + (Structure.gliderSpawner.getVY()[i] - 2)*boxSize + 2, boxSize - 4, boxSize - 4);
			break;
		}
	}
	
	
	
	
	// For making the highlighted cells alive 
	public void drawStructure (String value) {
		// Possibly-helpful-in-future formula for the box that is below the mouse
		final int xBase = (int)Math.floor((xMousePos - minorX)/boxSize);
		final int yBase = (int)Math.floor((yMousePos - minorY)/boxSize);
		switch (value) {
		case "Pond":
			for (int i = 0; i < Structure.pond.getVY().length; i++)
				CellsHolder.getCell(xBase + Structure.pond.getVX()[i] - 1, yBase + Structure.pond.getVY()[i] - 1).revive();
			break;
		case "Glider":
			for (int i = 0; i < Structure.glider.getVY().length; i++)
				CellsHolder.getCell(xBase + Structure.glider.getVX()[i] - 2, yBase + Structure.glider.getVY()[i] - 2).revive();
			break;	
		case "Glider Spawner":
			for (int i = 0; i < Structure.gliderSpawner.getVY().length; i++)
				CellsHolder.getCell(xBase + Structure.gliderSpawner.getVX()[i] - 14, yBase + Structure.gliderSpawner.getVY()[i] - 2).revive();
			break;
		}
		StructuresDialog.setSelectionStatus(false);
	}
	
	
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Here we decide on minor and major grids, there are 3 setups base on boxSize
		
		if (boxSize > 6) {
			g.setColor(new Color(0xeeeeee));
			drawGrid(g, 1);
			g.setColor(new Color(0x888888));
			drawGrid(g, 10);
			g.setColor(new Color(0x000000));
			drawGrid(g, 100);
		}
		else {
			g.setColor(new Color(0x888888));
			drawGrid(g, 10);
			g.setColor(new Color(0x000000));
			drawGrid(g, 100);
		}

		
		final int width = this.getWidth();
		final int height = this.getHeight();
		
		for (int i = 0; i < width + boxSize; i += boxSize) {
			for (int j = 0; j < height + boxSize; j += boxSize) {
				Cell cell = getCellAtPosition(i, j);
				if (cell.isAlive()) {
					if (colorCells) {
						g.setColor(cell.getColor());
					}
					else {
						g.setColor(Color.black);
					}
					drawCell(i - minorX, j - minorY, g);
				}
			}
		}

		if (StructuresDialog.getStatus())
			highlightStructure(g);
	}


	
	/*
	 * Draws cell at positions x, y on the given object.
	 * Decides if the cell needs to be wider and higher by 4 pixels based on boxSize.
	 */
	private void drawCell(int x, int y, Graphics g) {
		if (boxSize < 7) {
			g.fillRect(x, y, boxSize, boxSize);
		}
		else {
			g.fillRect(x+3, y+2, boxSize-3, boxSize-3);
		}
	}
		
	

	public void setColorCells(boolean b) {
		colorCells = b;
	}

}