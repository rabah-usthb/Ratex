package application;
	
import javafx.application.Application;


import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
        	
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ressource/Initialization.fxml"));
            String css = this.getClass().getResource("/ressource/Initialization.css").toExternalForm();
            Parent root = loader.load();
            
            // Set the FXML content to the scene
            Scene scene = new Scene(root);
            scene.getStylesheets().add(css);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
