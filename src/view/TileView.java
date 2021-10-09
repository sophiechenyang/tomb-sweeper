package view;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.GameModel;
import model.TileModel;

public class TileView extends ImageView{
	private int tileSize = GameModel.getTileSize();
	private Image tileImage = new Image("file:img/tile.png");
	
	public TileView(TileModel tileModel) {
		
		this.setImage(tileImage);
		this.setFitWidth(40);
		this.setFitHeight(40);
		
		setTranslateX(tileModel.getX() * tileSize);
		setTranslateY(tileModel.getY() * tileSize);
			
	}
	
	public void setPlayerHandler(EventHandler<MouseEvent> listenForClick) {
		this.setOnMouseClicked(listenForClick);
	}

}
