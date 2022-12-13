package org.openjfx.client.graphics;

import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class optionPage {
	private static Pane optionPage=null;
	public static Pane getOptionPage(Stage primaryStage) {
		if(optionPage==null) {
				try {
					optionPage= createOptionPage(primaryStage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return optionPage;
	}
	private static Pane createOptionPage(Stage primaryStage) throws FileNotFoundException {
		Pane base=new Pane();
        VBox background=new VBox();
		background.getChildren().addAll(homePage.getHomeButton(primaryStage));
        base.getChildren().add(background);
        base.setBackground(new Background(new BackgroundFill(Color.YELLOW, new CornerRadii(0), Insets.EMPTY)));
		base.getStylesheets().add("home.css");
		return base;
	}


}