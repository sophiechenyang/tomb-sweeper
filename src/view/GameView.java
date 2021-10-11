package view;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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

public class GameView extends Parent {

	private int rows = GameModel.getRows();
	private int columns = GameModel.getColumns();
	private int tileSize = GameModel.getTileSize();
	private Text gameScore = new Text("0");
	private Text jewelCount = new Text("0");
	private Text beetleCount = new Text("0");
	private Pane pane = new Pane();
	
	// Images initialized here are updated later in the game by the controller
	private Image pharaohImg = new Image("file:img/pharaoh.png");
	private ImageView pharaohView = new ImageView(pharaohImg);
	private ArrayList<BeetleView> beetleViewList = new ArrayList<BeetleView>();	
	private Image amuletImg = new Image(GameModel.getAmuletInactiveUrl());
	private ImageView amuletView = new ImageView(amuletImg);
	private Image resetImg = new Image(GameModel.getScarabInactiveUrl());
	private ImageView resetView = new ImageView(resetImg);
	private Image instructbttnImg = new Image(GameModel.getInactiveEyeUrl());
	ImageView instructbttnView = new ImageView(instructbttnImg);
	private Image instructImg = new Image(GameModel.getInstructionUrl());
	private ImageView instructView = new ImageView(instructImg);
	private Image eyeActiveImg = new Image(GameModel.getActiveEyeUrl());
	private Image amuletActiveImg = new Image(GameModel.getAmuletActiveUrl());
	

	public GameView() {
		BorderPane root = new BorderPane();

		setBackground(pane);
		root.setTop(createHeader());
		root.setCenter(createGamePane());
		root.setLeft(createLeftPanel());
		root.setBottom(createBottomPane());
		root.setRight(createRightPanel());

		this.getChildren().add(root);
	}

	public TileView createTile(TileModel tileModel) {
		TileView tileView = new TileView(tileModel);
		pane.getChildren().add(tileView);
		return tileView;
	}

	public BeetleView createBeatle(BeetleModel beetlemodel, GameModel gameModel) {
		BeetleView beetleView = new BeetleView(beetlemodel);
		pane.getChildren().add(beetleView);
		beetleViewList.add(beetleView);
		return beetleView;
	}
	
	public void setInstructions() {
		pane.getChildren().add(instructView);
	}

	public TreasureView createTreasure(TreasureModel treasureModel) {
		TreasureView treasureView = new TreasureView(treasureModel);
		pane.getChildren().add(treasureView);
		return treasureView;
	}

	public void updateScore(GameModel gameModel) {
		gameScore.setText(Integer.toString(gameModel.getGamePoints()));
	}

	public void updateBeetleCount(GameModel gameModel) {
		beetleCount.setText(Integer.toString(gameModel.getBeetleList().size()));
	}

	public void updateJewelCount(GameModel gameModel) {
		jewelCount.setText((Integer.toString(gameModel.getJewelCount())));
	}

	// Game Title
	public Pane createHeader() {
		Pane header = new Pane();

		Image topPaneImg = new Image("file:img/topPane.png");
		ImageView topPaneView = new ImageView(topPaneImg);
		topPaneView.setFitWidth(1160);
		topPaneView.setFitHeight(180);

		instructbttnView.setFitWidth(72);
		instructbttnView.setPreserveRatio(true);
		instructbttnView.setX(345);
		instructbttnView.setY(96);

		getResetView().setFitWidth(88);
		getResetView().setPreserveRatio(true);
		getResetView().setX(530);
		getResetView().setY(94);

		VBox textBox = new VBox();
		textBox.setAlignment(Pos.CENTER);

		amuletView.setFitHeight(72);
		amuletView.setPreserveRatio(true);
		amuletView.setX(762);
		amuletView.setY(85);

		header.getChildren().addAll(topPaneView, amuletView, instructbttnView, getResetView());
		return header;

	}

	public VBox createGamePane() {
		VBox middleContainer = new VBox();
		middleContainer.setStyle("-fx-background-color: #673A19");

		BorderPane gameDisplay = new BorderPane();
		gameDisplay.setMinSize(200, 60);

		// Game score
		gameScore.setFont(Font.font(24));
		gameScore.setFill(Color.GOLDENROD);

		// Gem Count
		HBox gemGroup = new HBox();
		gemGroup.setPadding(new Insets(15, 12, 15, 12));
		gemGroup.setSpacing(5);

		Image gemImg = new Image("file:img/gem.png");
		ImageView gemView = new ImageView(gemImg);
		gemView.setFitHeight(25);
		gemView.setFitWidth(25);

		jewelCount.setFont(Font.font(18));
		jewelCount.setFill(Color.GOLDENROD);
		
		gemGroup.getChildren().addAll(gemView, jewelCount);
		gameDisplay.setLeft(gemGroup);

		// Beetle Count

		HBox beetleGroup = new HBox();
		beetleGroup.setPadding(new Insets(15, 12, 15, 12));
		beetleGroup.setSpacing(5);

		Image beetleImg = new Image("file:img/beetle.png");
		ImageView beetleView = new ImageView(beetleImg);
		beetleView.setFitWidth(25);
		beetleView.setFitHeight(25);
		gameDisplay.setRight(beetleGroup);

		beetleCount.setFont(Font.font(18));
		beetleCount.setFill(Color.GOLDENROD);

		beetleGroup.getChildren().addAll(beetleView, beetleCount);

		gameDisplay.setCenter(gameScore);

		middleContainer.getChildren().addAll(gameDisplay, pane);
		return middleContainer;
	}

	public Pane createLeftPanel() {
		Pane leftcontainer = new Pane();

		Image leftPaneImg = new Image("file:img/leftPane.png");
		ImageView leftPaneView = new ImageView(leftPaneImg);

		Image fire = new Image("file:img/fire.gif");
		ImageView fireView = new ImageView(fire);
		fireView.setFitWidth(70);
		fireView.setPreserveRatio(true);
		fireView.setX(115);
		fireView.setY(130);

		leftPaneView.setFitWidth(400);
		leftPaneView.setFitHeight(460);

		leftcontainer.getChildren().addAll(leftPaneView, fireView);
		return leftcontainer;
	}

	public Pane createRightPanel() {
		Pane rightcontainer = new Pane();

		Image rightPaneImg = new Image("file:img/rightPane.png");
		ImageView rightPaneView = new ImageView(rightPaneImg);

		Image fire = new Image("file:img/fire.gif");
		ImageView fireView = new ImageView(fire);
		fireView.setFitWidth(70);
		fireView.setPreserveRatio(true);
		fireView.setX(205);
		fireView.setY(130);

		rightPaneView.setFitWidth(400);
		rightPaneView.setFitHeight(460);

		rightcontainer.getChildren().addAll(rightPaneView, fireView);
		return rightcontainer;
	}

	void setBackground(Pane pane) {
		pharaohView.setFitWidth(360);
		pharaohView.setFitHeight(400);
		pane.getChildren().add(pharaohView);

	}

	public Pane createBottomPane() {
		HBox bottomcontainer = new HBox();
		Image bottomPaneImg = new Image("file:img/bottomPane.png");
		ImageView bottomPaneView = new ImageView(bottomPaneImg);
		bottomPaneView.setFitWidth(1160);
		bottomPaneView.setFitHeight(100);

		bottomcontainer.getChildren().add(bottomPaneView);
		return bottomcontainer;
	}

	public void setWinView() {
		
		pane.getChildren().clear();
		
		Text congrats = new Text("Congratulations, you have found pharaoh's treasures !");
		
		Image winImg = new Image("file:img/win.png");
		ImageView winView = new ImageView(winImg);
		Image flower = new Image("file:img/flower.png");
		resetView.setImage(flower);
		pane.getChildren().addAll(winView);
	}

	public void setLostView() {
		Image lostImg = new Image("file:img/lost.png");
		pharaohView.setImage(lostImg);
		gameScore.setText("Game Over");
		Image activeScarab = new Image("file:img/scarab_active.png");
		resetView.setImage(activeScarab);

		beetleViewList.forEach(beetle -> beetle.stopBeetle());
	}

	public void reset() {
		pane.getChildren().clear();
		pharaohView.setImage(pharaohImg);
		pane.getChildren().add(pharaohView);
		resetView.setImage(resetImg);
		gameScore.setText("0");
		jewelCount.setText("0");
		beetleCount.setText("0");
	}
	
	public void showInstructions() {
		pane.getChildren().add(instructView);
	}
	
	public void removeInstructions() {
		pane.getChildren().remove(instructView);
	}
	
	
	// Events handlers

	
	public void clickResetButton(ImageView imageView, EventHandler<MouseEvent> startGame) {
		imageView.setOnMouseClicked(startGame);
	}
	
	public void clickInstructionBttn(ImageView imageView, EventHandler<MouseEvent> toggleInstruction) {
		imageView.setOnMouseClicked(toggleInstruction);
	}
	
	public void clickInstruction(ImageView imageView, EventHandler<MouseEvent> hideInstruction) {
		imageView.setOnMouseClicked(hideInstruction);
	}

	public void setKeyPressHandler(EventHandler<KeyEvent> activateAmulet) {
		this.setOnKeyPressed(activateAmulet);
	}

	public void setKeyReleaseHandler(EventHandler<KeyEvent> deactivateAmulet) {
		this.setOnKeyReleased(deactivateAmulet);
	}
	
	// setters and getters 

	public ImageView getAmuletView() {
		return amuletView;
	}

	public void setAmuletView(ImageView amuletView) {
		this.amuletView = amuletView;
	}

	public ImageView getResetView() {
		return resetView;
	}
	
	public ImageView getInstructView() {
		return instructView;
	}
	
	public ImageView getInstructBttnView() {
		return instructbttnView;
	}

	public Image getEyeActiveImg() {
		return eyeActiveImg;
	}
	
	public Image getEyeInactiveImg() {
		return instructbttnImg;
	}
	
	public Image getAmuletInactiveImg() {
		return amuletImg;
	}
	public Image getInstructionImg() {
		return instructImg;
	}

	public Image getAmuletActiveImg() {
		return amuletActiveImg;
	}
		

}
