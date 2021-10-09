package application;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import controller.GameController;

public class Main extends Application{
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
        Pane root = new Pane();
        GameController controller = new GameController();

        root.getChildren().add(controller.getGameView());

        primaryStage.setTitle("Tomb Sweeper");
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
