package controller;

import model.BeetleModel;
import model.GameModel;
import model.TileModel;
import model.TreasureModel;
import view.BeetleView;
import view.GameView;
import view.TileView;
import view.TreasureView;

public class GameController {
	private GameView gameView = new GameView();
	private GameModel gameModel = new GameModel();

	public GameController() {
		createTiles();
		gameModel.generateBeetles(this);
	}

	public void createTiles() {
		for (int y = 0; y < gameModel.getRows(); y++) {
			for (int x = 0; x < gameModel.getColumns(); x++) {
				TileModel tile = gameModel.createTile(x, y);
				TileView tileView = gameView.createTile(tile);
				TileController tileController = new TileController(tile, tileView,this);
				
			}
	}};

	public void createBeetle(int x, int y) {
		BeetleModel beetle = gameModel.createBeatle(x, y);
		BeetleView beetleView = gameView.createBeatle(beetle);
		BeetleController beetleController = new BeetleController(beetle, beetleView, gameModel, this);
		gameModel.increaseNumOfBeetles();
		// System.out.println("Number of beetles created is: " + gameModel.getNumOfBeetles());
		// System.out.println("its co-ordinate is X: "+ beetle.getX() + "and Y: " +
	}

	void createTreasure(int x, int y) {
		TreasureModel treasure = gameModel.createTreasure(x, y);
		TreasureView treasureView = gameView.createTreasure(treasure);
		TreasureController treasureController = new TreasureController(treasure, treasureView);
	}

	public GameView getGameView() {
		return gameView;
	}

	public void setGameView(GameView gameView) {
		this.gameView = gameView;
	}
	
	// Killing a beetle gets 5 points
	public void updateBeetleScore() {
		gameModel.increaseGamePointsBy(5);
		gameView.updateScore(gameModel);
	}

}
