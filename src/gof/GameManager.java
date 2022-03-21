package gof;

import java.io.File;

public class GameManager {
	
	private GameOfLife game;
	
	private boolean running;
	private int gameSpeed;
	private boolean colorfulCells;
	
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
	
	public void setColorfulCells(boolean b) {
		colorfulCells = b;
	}

	public void saveToFile(File file) {
		System.out.println("Saving to file: " + file.getName());
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

	public void addStructure(String string) {
		System.out.println("Adding structure: " + string);
	}
}
