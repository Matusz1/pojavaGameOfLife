package gof;

public class GameOfLife {
	
	private GuiManager guiMgr;
	private FrameManager frameMgr;
	private GameManager gameMgr;
	
	GameOfLife() {
		frameMgr = new FrameManager(this);
		guiMgr = new GuiManager(this); // After FrameManager
		gameMgr = new GameManager(this);
	}

	
	
	// === MAIN === //
	// ============ //
	
	public static void main(String[] args) {
		GameOfLife game = new GameOfLife();	
		game.getFrameMgr().setVisible(true);
	}
	
	
	
	// === Getters === //
	// =============== //
	
	GuiManager getGuiMgr() {
		return guiMgr;
	}
	
	FrameManager getFrameMgr() {
		return frameMgr;
	}
	
	GameManager getGameMgr() {
		return gameMgr;
	}

}
