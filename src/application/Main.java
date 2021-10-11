package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Paths;

import controller.GameController;

/**
 * @Auther: Sophie Chen Yang
 * @Date: Oct 2021
 * @Description: An Egyptian themed minesweeper game
 */

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		Pane root = new Pane();
		GameController controller = new GameController();
		root.getChildren().add(controller.getGameView());

		primaryStage.setTitle("Pharaoh's Treasures");
		primaryStage.setScene(new Scene(root, 1160, 720));
		primaryStage.show();

		// stop application on window close
		primaryStage.setOnCloseRequest(e -> {
			Platform.exit();
			System.exit(0);
		});

	}

	public static void main(String[] args) {
		launch(args);

	}

}
