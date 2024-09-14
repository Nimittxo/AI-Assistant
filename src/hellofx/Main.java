package hellofx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
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
        inputField.setPromptText("Enter input for Python script");
        Button runButton = new Button("Run Python Script");
        TextArea outputArea = new TextArea();
        outputArea.setEditable(false);
        
        // Set up the layout
        VBox layout = new VBox(10, inputField, runButton, outputArea);
        layout.setPadding(new javafx.geometry.Insets(20));
        layout.setStyle("-fx-background-color: black;"); // Set background color using CSS

        // Create the scene
        Scene scene = new Scene(layout, 600, 400);
        
        stage.setTitle("AI-Assistant");
        Image icon = new Image("logo.png");  
        stage.getIcons().add(icon);
        stage.setFullScreen(true);
        
        stage.setScene(scene);
        
        runButton.setOnAction(e -> {
            String userInput = inputField.getText();
            String output = runPythonScript(userInput);
            outputArea.setText(output);
        });

        stage.show();
    }

    private String runPythonScript(String input) {
        StringBuilder output = new StringBuilder();
        try {
            // Build the command to run the Python script
            String[] command = {"python", "src/InputProcessor.py", input}; // Adjust path to your script
            
            // Start the process
            Process process = new ProcessBuilder(command).redirectErrorStream(true).start();
            
            // Capture the output
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            
            // Wait for the process to complete
            process.waitFor();
            
        } catch (Exception e) {
            output.append("Error: ").append(e.getMessage());
        }
        return output.toString();
    }
}
