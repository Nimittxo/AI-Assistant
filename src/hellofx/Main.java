package hellofx;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;  // Correct import for JavaFX Image

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        
        // Width and height are specified, and background color is set as Color.CYAN
        Scene scene = new Scene(root, 400, 300, Color.CYAN);

        stage.setTitle("AI-Assistant");

        // Loading the icon using JavaFX Image class
        Image icon = new Image("logo.png");  
        stage.getIcons().add(icon);

        stage.setScene(scene);
        stage.show();
    }
}
