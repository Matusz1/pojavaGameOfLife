package gameOfLife;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

public class GameManager {
	
	private GameOfLife game;
	
	private boolean running;
	private int gameSpeed;
	
	GameManager(GameOfLife g) {
		game = g;
		running = false;
		gameSpeed = 2;
	}
	
	void setRunning(boolean b) {
		running = b;
	}
	
	void setGameSpeed(int speed) {
		gameSpeed = speed;
	}
	
	public void saveToFile(File file) {
		System.out.println("Saving to file: " + file.getName());
		
		Charset charset = Charset.forName("US-ASCII");
		Path myPath = file.toPath();
		try (BufferedWriter writer = Files.newBufferedWriter(myPath, charset)) {
		    int byteWrite = 0; 	//meaningless number
		    for (int y = 0; y < CellsHolder.HEIGHT; y++)
		    	for (int x = 0; x < CellsHolder.WIDTH; x++) {
		    		byteWrite = CellsHolder.getCells()[x][y].getLifetime();
		    		writer.write(byteWrite + 48);
		    	}
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
	}
	
	public void loadFromFile(File file) {
		System.out.println("Loading from file: " + file.getName());
		
		Charset charset = Charset.forName("US-ASCII");
		Path myPath = file.toPath();
		try (BufferedReader reader = Files.newBufferedReader(myPath, charset)) {
		    int byteRead=0; 	//meaningless number
		    CellsHolder.clearAll();
		    
		    for (int y = 0; y < CellsHolder.HEIGHT; y++)
		    	for (int x = 0; x < CellsHolder.WIDTH; x++) {
		    		if (byteRead != -1) 
		    			CellsHolder.getCells()[x][y].setLifetime(reader.read() - 48);  
		    		else
		    			break;
		    		} 		    
		   game.getGuiMgr().getGamePanel().repaint();
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
	}

	/*
	 * Makes quick backup/quicksave of current cells state
	 */
	public void makeQuickBackup() {
		System.out.println("Making quick backup");		
		CellsHolder.makeQuickBackup();
	}

	public void loadQuickBackup() {
		System.out.println("Loading quick backup");
		CellsHolder.loadQuickBackup();
		game.getGuiMgr().getGamePanel().repaint();
	}

	public void addStructure(String string) {
		System.out.println("Adding structure: " + string);
	}
}