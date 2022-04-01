package gof;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	//private static final long serialVersionUID = 4802230154979709652L;
	
	private int boxSize = 20;
	
	// Offset for drawing the cells
	private int majorX = 0;
	private int majorY = 0;
	private int minorX = 0;
	private int minorY = 0;
	
	GamePanel() {
		super();
		
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				repaint();
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
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);
		
		final int width = this.getWidth() / (boxSize+4);
		final int height = this.getHeight() / (boxSize+4);
		
		for (int i = majorY; i < width; ++i) {
			for (int j = majorX; j < height; ++j) {
				if (CellsHolder.getCell(i, j).isAlive()) {
					g.fillRect((j - majorX)*boxSize, (i - majorY)*boxSize, boxSize, boxSize);			
				}
			}
		}
	}

}
