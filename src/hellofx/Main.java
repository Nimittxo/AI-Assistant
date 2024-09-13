package hellofx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create root pane
        AnchorPane root = new AnchorPane();

        // Create label with text in the center
        Label label = new Label("This is a JFX WINDOW");
        label.setStyle("-fx-font-size: 24px;"); // Set font size

        // Create buttons
        Button exitButton = new Button("Exit");
        Button openButton = new Button("Open");

        // Set button actions
        exitButton.setOnAction(e -> System.exit(0)); // Exits the program
        openButton.setOnAction(e -> System.out.println("Open button clicked!")); // Placeholder action

        // Position the label in the center
        AnchorPane.setTopAnchor(label, 200.0); // Set Y position
        AnchorPane.setLeftAnchor(label, 450.0); // Set X position

        // Position buttons at the bottom
        AnchorPane.setBottomAnchor(exitButton, 50.0); // Set Y position
        AnchorPane.setLeftAnchor(exitButton, 200.0); // Set X position

        AnchorPane.setBottomAnchor(openButton, 50.0); // Set Y position
        AnchorPane.setRightAnchor(openButton, 200.0); // Set X position

        // Add components to the root pane
        root.getChildren().addAll(label, exitButton, openButton);

        // Set the scene and make it fullscreen
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true); // Make the window fullscreen

        primaryStage.setTitle("JavaFX Fullscreen Window");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
