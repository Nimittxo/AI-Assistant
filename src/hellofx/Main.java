package hellofx;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Create UI components
        TextField inputField = new TextField();
        inputField.setPromptText("Send Input");
        inputField.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;"); 
        inputField.setPrefHeight(40); 

        Button runButton = new Button("Run Python Script");
        runButton.setStyle("-fx-font-size: 16px;"); 

        TextArea outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;"); 
        outputArea.setPrefHeight(200); 

        // Load the background image
        Image backgroundImage = new Image("background-1.jpg"); 
        BackgroundImage myBackground = new BackgroundImage(backgroundImage, null, null, null, new BackgroundSize(100, 100, true, true, true, false));
        VBox layout = new VBox(10, inputField, runButton, outputArea);
        layout.setPadding(new javafx.geometry.Insets(20));
        layout.setBackground(new Background(myBackground)); 

 
        Scene scene = new Scene(layout, 600, 400);

        stage.setTitle("AI-Assistant");
        Image icon = new Image("logo.png");
        stage.getIcons().add(icon);
        stage.setFullScreen(true);

        stage.setScene(scene);

        runButton.setOnAction(e -> {
            String userInput = inputField.getText();
            outputArea.setText("Loading..."); 
            
            
            Task<String> task = new Task<String>() {
                @Override
                protected String call() {
                    return runPythonScript(userInput);
                }

                @Override
                protected void succeeded() {
                    outputArea.setText(getValue()); 
                }

                @Override
                protected void failed() {
                    outputArea.setText("Error: " + getException().getMessage()); 
                }
            };
            new Thread(task).start(); 
        });

        stage.show();
    }

    private String runPythonScript(String input) {
        StringBuilder output = new StringBuilder();
        try {
      
            String[] command = {"python", "src/InputProcessor.py", input}; // Adjust path to your script
            
  
            Process process = new ProcessBuilder(command).redirectErrorStream(true).start();
            

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            process.waitFor();
            
        } catch (Exception e) {
            output.append("Error: ").append(e.getMessage());
        }
        return output.toString();
    }
}
