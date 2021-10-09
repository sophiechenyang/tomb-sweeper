package controller;

import view.BeetleView;
import view.GameView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.BeetleModel;
import model.GameModel;

public class BeetleController {
	private BeetleView beetleView;
	private BeetleModel beetleModel;
	private GameModel gameModel;
	private GameController gameController;

	public BeetleController(BeetleModel beetleModel, BeetleView beetleView, GameModel gameModel, GameController gameController) {
		this.beetleView = beetleView;
		this.beetleModel = beetleModel;
		this.gameModel = gameModel;
		this.gameController = gameController;
		this.beetleView.setPlayerHandler(new SquashBeetle());

	}

	class SquashBeetle implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent e) {
			if (e.getClickCount() > 2) {
				//System.out.println(e);
				
				if (beetleModel.isDead())
					return;
				killBeetle();
				
				int randomX = gameModel.generateRand();
				int randomY = gameModel.generateRand();
				
				gameController.createBeetle(randomX, randomY);
			} else if (e.getButton() == MouseButton.SECONDARY){
				beetleView.stopBeetle();
			}
		}
	}
	
	private void killBeetle() {
		//beetleView.setImage(null);
		beetleView.fadeBeetle(this.beetleView);
		beetleModel.setDead(true);
		gameModel.decreaseNumOfBeetles(); 
		System.out.println("number of beetles count is: "+gameModel.getNumOfBeetles());
		gameController.updateBeetleScore();
	}
	

}
