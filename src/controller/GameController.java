package controller;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
	Timer timer;

	public GameController() {
		createTiles();
		gameView.clickResetButton(gameView.resetButton, new resetGameEvent());
		gameView.setKeyPressHandler(new activateAmulet());
		gameView.setKeyReleaseHandler(new deactivateAmulet());
	}

	public void startGame() {
		timer = new Timer();
		timer.schedule(new RemindTask(), 0, 8000);
		gameModel.setGameActive(true);

	}

	class RemindTask extends TimerTask {

		ArrayList<BeetleModel> beetleList = gameModel.getBeetleList();

		public void run() {
			Platform.runLater(() -> {
				int beetleCount = beetleList.size();

				if (beetleCount < 20) {
					createBeetle();
					createBeetle();
				} else {
					setGameOver();
				}
			});
		}
	}

	public void createTiles() {
		for (int y = 0; y < GameModel.getRows(); y++) {
			for (int x = 0; x < GameModel.getColumns(); x++) {
				TileModel tile = gameModel.createTile(x, y);
				TileView tileView = gameView.createTile(tile);
				TileController tileController = new TileController(tile, tileView, this, gameModel);
			}
		}
	};

	public void createBeetle() {
		BeetleModel beetle = gameModel.createBeatle();
		BeetleView beetleView = gameView.createBeatle(beetle, gameModel);
		BeetleController beetleController = new BeetleController(beetle, beetleView, gameModel, this);
	}

	void createTreasure(int x, int y) {
		TreasureModel treasure = gameModel.createTreasure(x, y);
		TreasureView treasureView = gameView.createTreasure(treasure);
		TreasureController treasureController = new TreasureController(treasure, treasureView, gameModel, gameView);
	}

	class resetGameEvent implements EventHandler<MouseEvent> {
		public void handle(MouseEvent e) {
			// System.out.println(gameModel.getTileCount());
			resetGame();
		}
	}
	
	class activateAmulet implements EventHandler<KeyEvent> {
		@Override
		public void handle(KeyEvent event) {
			KeyCode code = event.getCode();
			if (code == KeyCode.A) {
				gameModel.setAmuletActivated(true);
				System.out.println("A key is detected");
				System.out.println(gameModel.isAmuletActivated());
			}
		}
	}

	class deactivateAmulet implements EventHandler<KeyEvent> {
		@Override
		public void handle(KeyEvent event) {
			KeyCode code = event.getCode();
			if (code == KeyCode.A) {
				gameModel.setAmuletActivated(false);
				System.out.println("A key is released");
				System.out.println(gameModel.isAmuletActivated());
			}
		}
	}
	public GameView getGameView() {
		return gameView;
	}

	public void setGameView(GameView gameView) {
		this.gameView = gameView;
	}

	public void updateBeetleScore() {
		gameModel.increaseGamePointsBy(5);
		gameView.updateScore(gameModel);
	}

	public void setGameWon() {
		if (gameModel.isGameWon() == false && gameModel.isGameOver() == false) {
			gameModel.setGameWon(true);
			gameModel.setGameActive(false);
			gameView.setWinView();
			timer.cancel();
		}
	}

	public void setGameOver() {
		if (gameModel.isGameWon() == false && gameModel.isGameOver() == false) {
			gameModel.setGameOver(true);
			gameModel.setGameActive(false);
			gameView.setLostView();
			timer.cancel();
		}
	}

	public void resetGame() {
		timer.cancel();
		gameView.reset();
		gameModel.reset();
		createTiles();
	}

}
