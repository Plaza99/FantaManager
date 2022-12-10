package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class home {
	private GridPane gridPane;
    private GridPane createHomePane() {
        // Instantiate a new Grid Pane
        gridPane = new GridPane();

        // Position the pane at the center of the screen, both vertically and horizontally
        gridPane.setAlignment(Pos.CENTER);

        // Set a padding of 20px on each side
        gridPane.setPadding(new Insets(40, 40, 40, 40));

        // Set the horizontal gap between columns
        gridPane.setHgap(60);

        // Set the vertical gap between rows
        gridPane.setVgap(5);

        // Add Column Constraints
        gridPane.getColumnConstraints().add(new ColumnConstraints(global.widthMenu/2)); 
        gridPane.getColumnConstraints().add(new ColumnConstraints(global.widthMenu/2));
        gridPane.setMinSize(global.widthMenu, global.heightMenu);
    
        return gridPane;
    }
    public home() {
    	createHomePane();
    	
    	//addUiGeneral();
    	if(global.livPriv<0) {
    		addUiUnregistered();
    	}
    	else if(global.livPriv>=1) {
    		addUIRegistered();
    	}
    	if(global.livPriv>1) {
    		addUIAdmin();
    	}
    }
	private void addUiGeneral() {
	
		
	}
	private void addUIAdmin() {
		 Button settingsAdminButton = new Button("Admin");
		    settingsAdminButton.setPrefHeight(40);
		    settingsAdminButton.setDefaultButton(true);
		    settingsAdminButton.setPrefWidth(100);
		    gridPane.add(settingsAdminButton, 0, 4, 1, 1);
		    GridPane.setHalignment(settingsAdminButton, HPos.CENTER);
		    GridPane.setMargin(settingsAdminButton, new Insets(20, 0,20,0));
		    settingsAdminButton.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            		login l=new login();
	            		l.changeSceneLogin();
	            	}
	        });
		
	}
	private void addUIRegistered() {
		Label headerLabel = new Label("Benvenuto "+global.nick);
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(10, 0,20,0));
    	Button submitButtonLog = new Button("LogOut");
	    submitButtonLog.setPrefHeight(40);
	    submitButtonLog.setDefaultButton(true);
	    submitButtonLog.setPrefWidth(100);
	    gridPane.add(submitButtonLog, 1, 5, 1, 1);
	    GridPane.setHalignment(submitButtonLog, HPos.CENTER);
	    GridPane.setMargin(submitButtonLog, new Insets(20, 0,20,0));
	    submitButtonLog.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            		login l=new login();
            		l.changeSceneLogin();
            	}
        });
	    Button settingsButton = new Button("impostazioni");
	    settingsButton.setPrefHeight(40);
	    settingsButton.setDefaultButton(true);
	    settingsButton.setPrefWidth(100);
	    gridPane.add(settingsButton, 0, 5, 1, 1);
	    GridPane.setHalignment(settingsButton, HPos.CENTER);
	    GridPane.setMargin(settingsButton, new Insets(20, 0,20,0));
	    settingsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            		login l=new login();
            		l.changeSceneLogin();
            	}
        });
		
	}
	private void addUiUnregistered() {
		Label headerLabel = new Label("Unregistered");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0,0,1,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));
		
	}
	public void changeSceneHome() {
		
		Main.scene=new Scene(gridPane, global.widthFormInit+120, global.heightFormInit);
		Main.primaryStageMain.setScene(Main.scene);
	}
}
