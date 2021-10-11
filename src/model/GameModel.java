package model;

import java.util.ArrayList;

import controller.GameController;
import javafx.scene.Parent;

public class GameModel extends Parent {
	private static final int ROWS = 10;
	private static final int COLUMNS = 9;
	private static final int TILE_SIZE = 40;
	private static final int MAXIMUM_BEETLE = 20;
	private int gamePoints = 0;
	private TileModel[][] grid = new TileModel[COLUMNS][ROWS] ;
	private int tilesOpened = 0;
	private int numOfTiles = ROWS * COLUMNS;
	private boolean gameWon = false;
	private boolean gameOver = false;
	private boolean gameActive = false;
	private ArrayList<BeetleModel> beetleList = new ArrayList<BeetleModel>();
	private int jewelCount = 0;
	private boolean amuletActivated = false; 
	private boolean showingInstruction = true;
	
	// images
	private static final String ACTIVE_EYE_URL = "file:img/eye_active.png";
	private static final String INACTIVE_EYE_URL = "file:img/eye_inactive.png";
	private static final String INSTRUCTION_URL = "file:img/instructions.png";
	private static final String AMULET_INACTIVE_URL = "file:img/amulet_inactive.png";
	private static final String AMULET_ACTIVE_URL = "file:img/amulet_active.png";
	private static final String SCARAB_INACTIVE_URL = "file:img/scarab_inactive.png";
	
	public BeetleModel createBeatle() {
		BeetleModel beetle = new BeetleModel(generateRand(), generateRand());
		beetleList.add(beetle);
		return beetle;
	}

	public TreasureModel createTreasure(int x, int y) {
		TreasureModel treasure = new TreasureModel(x, y);
		return treasure;
	}
	
	public TileModel createTile(int x, int y) {
		TileModel tile = new TileModel(x,y,Math.random()<0.2);
		grid[x][y] = tile;
		return tile;
	}

	public static int getRows() {
		return ROWS;
	}

	public static int getColumns() {
		return COLUMNS;
	}

	public static int getTileSize() {
		return TILE_SIZE;
	}

	public int getGamePoints() {
		return gamePoints;
	}

	public void increaseGamePointsBy(int increment) {
		this.gamePoints = this.gamePoints + increment;
	}
	
	public void increaseTileOpenCount() {
		this.tilesOpened += 1;
	}
	
	public int getTileCount() {
		return tilesOpened;
	}
	
	public int getNumberOfTiles() {
		return numOfTiles;
	}
	
	public void reset() {
		beetleList.clear();
		gamePoints = 0;
		tilesOpened = 0;
		jewelCount = 0;
		gameWon = false;
		gameOver = false;
		gameActive = false;	
	}
	
	public int generateRand() {
		int random = 2 + (int) (Math.random() * ((7 - 2) + 1));
		return random;
	}

	public boolean isGameWon() {
		return gameWon;
	}

	public void setGameWon(boolean gameWon) {
		this.gameWon = gameWon;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public ArrayList getBeetleList() {
		return beetleList;
	}

	public void removeFromBeetleList(BeetleModel beetleModel) {
		beetleList.remove(beetleModel);
	}

	public boolean isGameActive() {
		return gameActive;
	}

	public void setGameActive(boolean gameActive) {
		this.gameActive = gameActive;
	}

	public boolean isAmuletActivated() {
		return amuletActivated;
	}

	public void setAmuletActivated(boolean amuletActivated) {
		this.amuletActivated = amuletActivated;
	}

	public int getJewelCount() {
		return jewelCount;
	}

	public void increaseJewelCount() {
		jewelCount += 1;
	}

	public static String getActiveEyeUrl() {
		return ACTIVE_EYE_URL;
	}

	public static String getInactiveEyeUrl() {
		return INACTIVE_EYE_URL;
	}

	public boolean isShowingInstruction() {
		return showingInstruction;
	}

	public void setShowingInstruction(boolean showingInstruction) {
		this.showingInstruction = showingInstruction;
	}

	public static String getInstructionUrl() {
		return INSTRUCTION_URL;
	}

	public static int getMaximumBeetle() {
		return MAXIMUM_BEETLE;
	}

	public static String getAmuletInactiveUrl() {
		return AMULET_INACTIVE_URL;
	}

	public static String getScarabInactiveUrl() {
		return SCARAB_INACTIVE_URL;
	}

	public static String getAmuletActiveUrl() {
		return AMULET_ACTIVE_URL;
	}

}
