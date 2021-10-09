package controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.TreasureModel;
import view.TreasureView;

public class TreasureController {
	private TreasureModel treasureModel;
	private TreasureView treasureView;
	
	public TreasureController(TreasureModel treasureModel, TreasureView treasureView) {
		this.treasureModel = treasureModel;
		this.treasureView = treasureView;
		this.treasureView.setPlayerHandler(new detectDrag());
	}
	class detectDrag implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent e) {
			treasureView.collectTreasure();
		}
	}
}
