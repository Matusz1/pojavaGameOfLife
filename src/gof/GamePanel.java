package gof;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	//private static final long serialVersionUID = 4802230154979709652L;
	
	private int boxSize = 16;
	
	// Offset for drawing the cells
	private int majorX = 0;
	private int majorY = 0;
	private int minorX = 0;
	private int minorY = 0;
	
	// For moving purpose
	private int lastMousePosX = 0;
	private int lastMousePosY = 0;
	
	GamePanel() {
		super();
		
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
				// TODO Auto-generated method stub
				
			}
		});
		
		this.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				 minorX += e.getX() - lastMousePosX;
				 minorY += e.getY() - lastMousePosY;
				 lastMousePosX = e.getX();
				 lastMousePosY = e.getY();
				 repaint();
			}
		});
	}
	
	public void drawGrid(Graphics g) {
		final int width = this.getWidth();
		final int height = this.getHeight();
		
		// Drawing Horizontal lines
		for (int i = minorY; i < width; i += boxSize) {
			g.fillRect(0, i-1, width, 2);
		}
		
		// Drawing Vertical lines
		for (int i = minorX; i < width; i += boxSize) {
			g.fillRect(i-1, 0, 2, height);
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.lightGray);
		drawGrid(g);
		
		g.setColor(Color.black);
		
		final int width = this.getWidth() / boxSize + 1;
		final int height = this.getHeight() / boxSize + 1;
		
		for (int i = majorY; i < height; ++i) {
			for (int j = majorX; j < width; ++j) {
				if (CellsHolder.getCell(j, i).isAlive()) {
					g.fillRect((j - majorX)*boxSize + 2 + minorX, (i - majorY)*boxSize + 2 + minorY, boxSize-4, boxSize-4);			
				}
			}
		}
	}

}