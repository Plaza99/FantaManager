package org.openjfx.client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class rankingPage {
	private static Pane rankingPage=null;
	public static Pane getRankingPage(Stage primaryStage) {
		if(rankingPage==null) {
				try {
					rankingPage=createRanking(primaryStage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return rankingPage;
	}
	private static Pane createRanking(Stage primaryStage) throws FileNotFoundException {
		Pane base=new Pane();
        VBox background=new VBox();
        background.setBackground(new Background(new BackgroundFill(Color.YELLOW, new CornerRadii(0), Insets.EMPTY)));
        base.getChildren().add(background);
        base.setBackground(new Background(new BackgroundFill(Color.PURPLE, new CornerRadii(0), Insets.EMPTY)));
		return base;
	}


}