package application;

import java.sql.Connection;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Main extends Application {

public static Scene scene;
public static Stage primaryStageMain;
    @Override
    public void start(Stage primaryStage) throws Exception {
    	primaryStageMain=primaryStage;
        primaryStage.setTitle("Registration Form JavaFX Application");
     
        // Create the registration form grid pane
        login l=new login();
        GridPane gridPane = l.createRegistrationFormPane();
        // Add UI controls to the registration form grid pane
        // Create a scene with registration form grid pane as the root node
        scene = new Scene(gridPane, global.widthFormInit+120, global.heightFormInit);
        // Set the scene in primary stage  
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
} 
            