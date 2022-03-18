package gof;

import java.awt.HeadlessException;

import javax.swing.JFrame;

public class FrameManager extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private GameOfLife game;
	
	public FrameManager(GameOfLife g) throws HeadlessException {
		super("Game of Life");
		game = g;
		
		this.setSize(1200, 800);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	

}
