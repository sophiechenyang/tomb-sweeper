package controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.GameModel;
import model.TileModel;
import view.TileView;

public class TileController {
	private TileModel tileModel;
	private TileView tileView;
	private GameModel gameModel;
	private GameController gameController;
	
	public TileController(TileModel tileModel, TileView tileView, GameController gameController, GameModel gameModel) {
		this.tileModel = tileModel;
		this.tileView = tileView;
		this.gameModel = gameModel;
		this.gameController = gameController;
		this.tileView.setPlayerHandler(new detectClick());
	}
	
	class detectClick implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent e) {	
			
			if (!gameModel.isGameActive() && !gameModel.isGameOver())
				gameController.startGame();
			
			if (tileModel.isHasTreasure() == true && !gameModel.isGameOver()) {
				gameController.createTreasure(tileModel.getX(), tileModel.getY());
			}
			
			if (gameModel.isGameActive()) {
				tileView.setImage(null);
				gameModel.increaseTileOpenCount();
			}
		
			
			System.out.println("Tiles opened: " + gameModel.getTileCount());
			System.out.println("Total tiles: " + gameModel.getNumberOfTiles());
			
			if (gameModel.getTileCount() == gameModel.getNumberOfTiles()) {
				gameController.setGameWon();
			}
			
			//System.out.println("is Game Active: " + gameModel.isGameActive());
			//System.out.println("is Game Over: " + gameModel.isGameOver());


		}
	}

}
