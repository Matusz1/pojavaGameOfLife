package gof;

// Testig GitHub

public class GameOfLife {
	
	private GuiManager guiMgr;
	private GameManager gameMgr;
	
	GameOfLife() {
		gameMgr = new GameManager(this);
		guiMgr = new GuiManager(this);
	}

	public static void main(String[] args) {
		GameOfLife game = new GameOfLife();	
		game.getGuiMgr().setVisible(true);
	}
	
	GuiManager getGuiMgr() {
		return guiMgr;
	}
	
	GameManager getGameMgr() {
		return gameMgr;
	}

}
