package model;

import controller.GameController;
import javafx.scene.Parent;

public class GameModel extends Parent {
	private static final int ROWS = 10;
	private static final int COLUMNS = 10;
	private static final int TILE_SIZE = 40;
	private int beetlesKilled = 0;
	private int numOfBeetles = 0;
	private int gamePoints = 0;
	private TileModel[][] grid = new TileModel[COLUMNS][ROWS] ;

	public BeetleModel createBeatle(int x, int y) {
		BeetleModel beatle = new BeetleModel(x, y);
		return beatle;
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

	public void generateBeetles(GameController gamecontroller) {
		while(numOfBeetles < 5) {
			int x = generateRand();
			int y = generateRand();
			gamecontroller.createBeetle(x,y);
		}
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

	public int getBeatleKilled() {
		return beetlesKilled;
	}

	public void increaseBeetleCount() {
		this.beetlesKilled += 1;
	}

	public int getNumOfBeetles() {
		return numOfBeetles;
	}

	public void decreaseNumOfBeetles() {
		this.numOfBeetles -= 1;
	}

	public void increaseNumOfBeetles() {
		this.numOfBeetles += 1;
	}

	public int getGamePoints() {
		return gamePoints;
	}

	public void increaseGamePointsBy(int increment) {
		this.gamePoints = this.gamePoints + increment;
	}
	
	// ensure objects do not go out of range
	public int generateRand() {
		int random = 2 + (int) (Math.random() * ((8 - 2) + 1));
		return random;
	}

}
