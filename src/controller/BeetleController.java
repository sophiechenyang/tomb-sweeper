package controller;

import view.BeetleView;
import view.GameView;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.BeetleModel;
import model.GameModel;

public class BeetleController {
	private BeetleView beetleView;
	private BeetleModel beetleModel;
	private GameModel gameModel;
	private GameView gameView;
	private GameController gameController;

	public BeetleController(BeetleModel beetleModel, BeetleView beetleView, GameModel gameModel, GameController gameController, GameView gameView) {
		this.beetleView = beetleView;
		this.beetleModel = beetleModel;
		this.gameModel = gameModel;
		this.gameView = gameView;
		this.gameController = gameController;
		this.beetleView.setPlayerHandler(new clickBeetle());
		this.beetleView.setHoverHandler(new hoverBeetle());

	}

	class clickBeetle implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent e) {
			if (e.getClickCount() > 2) {
				
				if (beetleModel.isDead())
					return;
				
				killBeetle();
				
			} else if (e.getButton() == MouseButton.SECONDARY){
				beetleView.stopBeetle();
			}
		}
	}
	
	class hoverBeetle implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent e) {
			
			if (!gameModel.isAmuletActivated() && !beetleModel.isDead()) {
				beetleView.stopBeetle();
				gameController.setGameOver();
			} else {
				beetleView.setCursor(Cursor.CROSSHAIR);
			}
			
		}
	}
	
	private void killBeetle() {

		
		if (gameModel.isGameActive()) {
			beetleView.collectBeetle();
			beetleModel.setDead(true);
			gameModel.removeFromBeetleList(beetleModel);
			gameController.updateBeetleScore();
			gameView.updateBeetleCount(gameModel);
		}
	}
	

}
