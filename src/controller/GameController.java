package controller;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
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
	File f;
	Media m;
	MediaPlayer mp;

	public GameController() throws MalformedURLException {
		createTiles();
		gameView.setInstructions();
		gameView.clickResetButton(gameView.getResetView(), new resetGameEvent());
		gameView.clickInstructionBttn(gameView.getInstructBttnView(), new toggleInstruction());
		gameView.setKeyPressHandler(new activateAmulet());
		gameView.setKeyReleaseHandler(new deactivateAmulet());
		gameView.clickInstruction(gameView.getInstructView(), new hideInstruction());
        f = new File("src/Osiris.mp3");
        m = new Media(f.toURI().toString());
        mp = new MediaPlayer(m);
    	mp.play();
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

				if (beetleCount < gameModel.getMaximumBeetle()) {
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
		BeetleController beetleController = new BeetleController(beetle, beetleView, gameModel, this, gameView);
		gameView.updateBeetleCount(gameModel);
	}

	void createTreasure(int x, int y) {
		TreasureModel treasure = gameModel.createTreasure(x, y);
		TreasureView treasureView = gameView.createTreasure(treasure);
		TreasureController treasureController = new TreasureController(treasure, treasureView, gameModel, gameView);
	}

	class resetGameEvent implements EventHandler<MouseEvent> {
		public void handle(MouseEvent e) {

			if(gameModel.isGameActive() || gameModel.isGameOver() || gameModel.isGameWon() ) {
				resetGame();
			}	
		}
	}
	
	class hideInstruction implements EventHandler<MouseEvent>{
		public void handle(MouseEvent e) {
			gameView.removeInstructions();
			gameView.getInstructBttnView().setImage(gameView.getEyeInactiveImg());
			gameModel.setShowingInstruction(false);
		}
	}
	
	class toggleInstruction implements EventHandler<MouseEvent>{
		public void handle(MouseEvent e) {
			if (gameModel.isShowingInstruction()) {
				gameView.removeInstructions();
				gameView.getInstructBttnView().setImage(gameView.getEyeInactiveImg());
				gameModel.setShowingInstruction(false);
			} else {
				gameView.showInstructions();
				gameView.getInstructBttnView().setImage(gameView.getEyeActiveImg());
				gameModel.setShowingInstruction(true);
			}
			

		}
	}
	
	class activateAmulet implements EventHandler<KeyEvent> {
		@Override
		public void handle(KeyEvent event) {
			KeyCode code = event.getCode();
			if (code == KeyCode.A) {
				gameModel.setAmuletActivated(true);
				gameView.getAmuletView().setImage(gameView.getAmuletActiveImg());
			}
		}
	}

	class deactivateAmulet implements EventHandler<KeyEvent> {
		@Override
		public void handle(KeyEvent event) {
			KeyCode code = event.getCode();
			if (code == KeyCode.A) {
				gameModel.setAmuletActivated(false);			
				gameView.getAmuletView().setImage(gameView.getAmuletInactiveImg());
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
