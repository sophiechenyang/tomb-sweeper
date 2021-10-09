package controller;

import controller.TreasureController.detectDrag;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.TileModel;
import view.TileView;

public class TileController {
	private TileModel tileModel;
	private TileView tileView;
	private GameController gameController;
	
	public TileController(TileModel tileModel, TileView tileView, GameController gameController) {
		this.tileModel = tileModel;
		this.tileView = tileView;
		this.gameController = gameController;
		this.tileView.setPlayerHandler(new detectClick());
	}
	
	class detectClick implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent e) {
			if (tileModel.isHasTreasure() == true) {
				gameController.createTreasure(tileModel.getX(), tileModel.getY());
			}
			tileView.setImage(null);
		}
	}

}
