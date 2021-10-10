package model;

import java.util.ArrayList;

import controller.GameController;
import javafx.scene.Parent;

public class GameModel extends Parent {
	private static final int ROWS = 10;
	private static final int COLUMNS = 9;
	private static final int TILE_SIZE = 40;
	private int gamePoints = 0;
	private TileModel[][] grid = new TileModel[COLUMNS][ROWS] ;
	private int tilesOpened = 0;
	private int numOfTiles = ROWS * COLUMNS;
	private boolean gameWon = false;
	private boolean gameOver = false;
	private boolean gameActive = false;
	private ArrayList<BeetleModel> beetleList = new ArrayList<BeetleModel>();
	private boolean amuletActivated = false; 
	
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

}
