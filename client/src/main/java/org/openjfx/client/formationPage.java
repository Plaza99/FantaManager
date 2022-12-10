package org.openjfx.client;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class formationPage {
	private static Pane formationPage=null;
	public static Pane getFormationPage(Stage primaryStage) {
		if(formationPage==null) {
				try {
					formationPage=createFormationPage(primaryStage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return formationPage;
	}
	private static Pane createFormationPage(Stage primaryStage) throws FileNotFoundException {
		Pane base=new Pane();
        VBox background=new VBox();
        background.setBackground(new Background(new BackgroundFill(Color.YELLOW, new CornerRadii(0), Insets.EMPTY)));
        base.getChildren().add(background);
        base.setBackground(new Background(new BackgroundFill(Color.GREEN, new CornerRadii(0), Insets.EMPTY)));
		return base;
	}
}
