package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class login {

    public GridPane createRegistrationFormPane() {
        // Instantiate a new Grid Pane
        GridPane gridPane = new GridPane();

        // Position the pane at the center of the screen, both vertically and horizontally
        gridPane.setAlignment(Pos.CENTER);

        // Set a padding of 20px on each side
        gridPane.setPadding(new Insets(40, 40, 40, 40));

        // Set the horizontal gap between columns
        gridPane.setHgap(60);

        // Set the vertical gap between rows
        gridPane.setVgap(5);

        // Add Column Constraints
        gridPane.getColumnConstraints().add(new ColumnConstraints(global.widthFormInit/2)); 
        gridPane.getColumnConstraints().add(new ColumnConstraints(global.widthFormInit/2));
        gridPane.setMinSize(global.widthFormInit, global.heightFormInit);
        addUIControls(gridPane);
        return gridPane;
    }

    private  void addUIControls(GridPane gridPane) {
        // Add Header
        Label headerLabel = new Label("Registrati");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0,0,1,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));
        
        Label headerLabelLog = new Label("Accedi");
        headerLabelLog.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabelLog, 1,0,1,1);
        GridPane.setHalignment(headerLabelLog, HPos.CENTER);
        GridPane.setMargin(headerLabelLog, new Insets(20, 0,20,0));

        // Add Name Label
        Label nameLabel = new Label("Full Name : ");
        gridPane.add(nameLabel, 0,1);

        // Add Name Text Field
        TextField nameField = new TextField();
        nameField.setPrefHeight(40);
        gridPane.add(nameField, 0,2);

        // Add Password Label
        Label passwordLabel = new Label("Password : ");
        gridPane.add(passwordLabel, 0, 3);

        // Add Password Field
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefHeight(40);
        gridPane.add(passwordField, 0, 4);
        
        // Add Name Label
        Label nameLabelLog = new Label("Full Name : ");
        gridPane.add(nameLabelLog, 1,1);

        // Add Name Text Field
        TextField nameFieldLog = new TextField();
        nameFieldLog.setPrefHeight(40);
        gridPane.add(nameFieldLog, 1,2);


        // Add Password Label
        Label passwordLabelLog = new Label("Password : ");
        gridPane.add(passwordLabelLog, 1, 3);

        // Add Password Field
        PasswordField passwordFieldLog = new PasswordField();
        passwordFieldLog.setPrefHeight(40);
        gridPane.add(passwordFieldLog, 1, 4);

        // Add Submit Button
        Button submitButtonReg = new Button("Registrati");
        submitButtonReg.setPrefHeight(40);
        submitButtonReg.setDefaultButton(true);
        submitButtonReg.setPrefWidth(100);
        gridPane.add(submitButtonReg, 0, 5, 1, 1);
        GridPane.setHalignment(submitButtonReg, HPos.CENTER);
        GridPane.setMargin(submitButtonReg, new Insets(20, 0,20,0));

        submitButtonReg.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	System.out.println("Registrazione");
            	if(utente.exists(connection_mySQL.get_connection(),nameField.getText())) {
            		System.out.println("Utente già registrato");
    			}
            	else {
            		utente.insert_utent(connection_mySQL.get_connection(), nameField.getText(),passwordField.getText());
            	}
            	}
        });
	    Button submitButtonLog = new Button("Accedi");
	    submitButtonLog.setPrefHeight(40);
	    submitButtonLog.setDefaultButton(true);
	    submitButtonLog.setPrefWidth(100);
	    gridPane.add(submitButtonLog, 1, 5, 1, 1);
	    GridPane.setHalignment(submitButtonLog, HPos.CENTER);
	    GridPane.setMargin(submitButtonLog, new Insets(20, 0,20,0));
	
	    submitButtonLog.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent event) {
	        	System.out.println("Login");
	        	if(utente.exists(connection_mySQL.get_connection(),nameFieldLog.getText())) {
	        		global.livPriv=utente.logIn(connection_mySQL.get_connection(),nameFieldLog.getText(),passwordFieldLog.getText()); 
	        		if(global.livPriv==0) {
	        			System.out.println("passwordErrata");
	        			global.livPriv=-1;
	        		}
	        		else if(global.livPriv==1) {
	        			System.out.println("Login avvenuto con successo per un utente");
	        			home h=new home();
	        			h.changeSceneHome();
	        		}
	        		else if(global.livPriv>1) {
	        			System.out.println("Login avvenuto con successo per un admin");
	        			home h=new home();
	        			h.changeSceneHome();
	        		}
	        		
	        		
	        	}else {
	        		System.out.println("nick errato");
	        	}
	        }
	    });
}

  
    public  void changeSceneLogin() {
    	GridPane gridPane=createRegistrationFormPane();
    	Main.scene=new Scene(gridPane, global.widthFormInit+120, global.heightFormInit);
    	Main.primaryStageMain.setScene(Main.scene);
    }
}
