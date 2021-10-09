package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.BeetleModel;
import model.GameModel;
import model.TileModel;
import model.TreasureModel;

public class GameView extends Parent{	
	
	int rows = GameModel.getRows();
	int columns = GameModel.getColumns();
	int tileSize = GameModel.getTileSize();
	Text gameScore = new Text("0");
	Pane pane = new Pane();
	Image pharaohImg = new Image("file:img/pharaoh.png");
	ImageView pharaohView = new ImageView(pharaohImg);
	
	public GameView() {
		BorderPane root = new BorderPane();
		
		setBackground(pane);
		root.setTop(setHeader());
		root.setCenter(pane);
		root.setLeft(setLeftPanel());
		root.setBottom(setBottomPane());
		root.setRight(setRightPanel());
		
		this.getChildren().add(root);
	}
	
	public TileView createTile(TileModel tileModel) {
		TileView tileView = new TileView(tileModel);
		pane.getChildren().add(tileView);
		System.out.println();
		return tileView;	
	}
	
	public BeetleView createBeatle(BeetleModel beetlemodel) {
		BeetleView beetleView = new BeetleView(beetlemodel);
		pane.getChildren().add(beetleView);
		return beetleView;
	}
	
	public TreasureView createTreasure(TreasureModel treasureModel) {
		TreasureView treasureView = new TreasureView(treasureModel);
		pane.getChildren().add(treasureView);
		return treasureView;
	}

	public void updateScore (GameModel gameModel) {
		System.out.println(gameModel.getGamePoints());
		gameScore.setText(Integer.toString(gameModel.getGamePoints())); ;
	}
	
	// Game Title
	public StackPane setHeader() {
		StackPane header = new StackPane();
		
		Image topPaneImg = new Image("file:img/topPane.png");
		ImageView topPaneView = new ImageView(topPaneImg);
		topPaneView.setFitWidth(1160);
		topPaneView.setFitHeight(180);
		
		VBox textBox = new VBox();
		textBox.setAlignment(Pos.CENTER);
		
		Text title = new Text("Pharaoh's Treasures");
		title.setFill(Color.GOLD);
		title.setFont(Font.font(24));
	
		gameScore.setFill(Color.GOLD);
		gameScore.setFont(Font.font(30));
		
		textBox.getChildren().addAll(title, gameScore);
		
		//header.getStyleClass().add("header");
		
		header.getChildren().addAll(topPaneView, textBox);
		return header;
		
	}
	
	// Instructions panel on the left
	public VBox setLeftPanel() {
		VBox leftcontainer = new VBox();
				
		Image leftPaneImg = new Image("file:img/leftPane.png");
		ImageView leftPaneView = new ImageView(leftPaneImg);
		
		
		leftPaneView.setFitWidth(400);
		leftPaneView.setFitHeight(400);
		
		leftcontainer.getChildren().add(leftPaneView);
		return leftcontainer;
	}
	
	// Instructions panel on the right
	public VBox setRightPanel() {
		VBox rightcontainer = new VBox();
				
		Image rightPaneImg = new Image("file:img/rightPane.png");
		ImageView rightPaneView = new ImageView(rightPaneImg);
		
		
		rightPaneView.setFitWidth(400);
		rightPaneView.setFitHeight(400);
		
		rightcontainer.getChildren().add(rightPaneView);
		return rightcontainer;
	}
	
	void setBackground(Pane pane) {
		pharaohView.setFitWidth(360);
		pharaohView.setFitHeight(400);
		pane.getChildren().add(pharaohView);

	}

	
	public Pane setBottomPane() {
		HBox bottomcontainer = new HBox();
		Image bottomPaneImg = new Image("file:img/bottomPane.png");
		ImageView bottomPaneView = new ImageView(bottomPaneImg);
		bottomPaneView.setFitWidth(1160);
		bottomPaneView.setFitHeight(140);
		
		bottomcontainer.getChildren().add(bottomPaneView);
		return bottomcontainer;
	}

}
