package gof;

import java.io.File;

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
		System.out.println("The game has startet/stopped");
	}
	
	void setGameSpeedOption(int speed) {
		gameSpeed = speed;
		System.out.println("Setting the game speed to: " + speed);
	}

	public void saveToFile(File file) {
		System.out.println("Saving to file: file.getName()");
	}
	
	public void loadFromFile(File file) {
		System.out.println("Loading from file: " + file.getName());
	}

	public void makeQuickBackup() {
		System.out.println("Making quick backup");
		
	}

	public void loadQuickBackup() {
		System.out.println("Loading quick backup");
	}
	
}
