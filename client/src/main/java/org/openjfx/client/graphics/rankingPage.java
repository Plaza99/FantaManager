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

public class rankingPage {
	private static Pane rankingPage=null;
	public static Pane getRankingPage(Stage primaryStage) {
		if(rankingPage==null) {
				try {
					rankingPage= createRankingPage(primaryStage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return rankingPage;
	}
	private static Pane createRankingPage(Stage primaryStage) throws FileNotFoundException {
		Pane base=new Pane();
        VBox background=new VBox();
		background.getChildren().addAll(homePage.getHomeButton(primaryStage));
        base.getChildren().add(background);
        base.setBackground(new Background(new BackgroundFill(Color.PURPLE, new CornerRadii(0), Insets.EMPTY)));
		return base;
	}


}