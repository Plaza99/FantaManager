package org.openjfx.client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

	public static Scene scene;
	public static Stage primaryStageMain;
	    @Override
	    public void start(Stage primaryStage) throws Exception {
	    	primaryStageMain=primaryStage;
	        scene = new Scene(homePage.getHome(primaryStageMain), global.widthMenu, global.heightMenu);
	        // Set the scene in primary stage  
	        primaryStage.setScene(scene);
	        
	        primaryStage.show();
	    }


	    public static void main(String[] args) {
	        launch(args);
	    }

}