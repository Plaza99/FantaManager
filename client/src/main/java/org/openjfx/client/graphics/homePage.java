package org.openjfx.client.graphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

public class homePage {
	private static Pane homePage=null;
	public static Pane getHome(Stage primaryStage) {
		if(homePage==null) {
			try {
				homePage=createHome(primaryStage);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return homePage;
	}
	private static Pane createHome(Stage primaryStage) throws FileNotFoundException {
	
		Pane base=new Pane();
        VBox background=new VBox();
        // titolo e spazio sopra
        HBox upperSpacePane=new HBox();
        HBox titleSpacePane=new HBox();
        Text titoloText=new Text("FantaManager");
        titoloText.getStyleClass().add("titoloApp");
        Image ballIcon=new Image(new FileInputStream("client/src/main/java/imm/ballicon_white.png"));
        ImageView ballView = new ImageView(ballIcon);
        ballView.getStyleClass().add("immagineTitoloApp");
        ballView.setFitHeight(80);
        ballView.setFitWidth(80);
        upperSpacePane.setAlignment(Pos.CENTER);
        titleSpacePane.setAlignment(Pos.CENTER);
        titleSpacePane.setSpacing(20);
        titleSpacePane.getChildren().addAll(ballView,titoloText);
        upperSpacePane.getChildren().addAll(titleSpacePane);
        upperSpacePane.setPadding(new Insets(30,0,270,0));
       
        //spazio per le scelte dell'utente
        VBox opzioniBox=createChoiseBox("opzioni","option.png");
        opzioniBox.setOnMouseClicked(event->{
        	primaryStage.getScene().setRoot(optionPage.getOptionPage(primaryStage));
        });
        VBox formazioneBox=createChoiseBox("formazione","formation_white.png");
        formazioneBox.setOnMouseClicked(event->{
        	primaryStage.getScene().setRoot(formationPage.getFormationPage(primaryStage));
        });
        VBox classificaBox=createChoiseBox("classifica","podio.png");
        classificaBox.setOnMouseClicked(event->{
        	primaryStage.getScene().setRoot(rankingPage.getRankingPage(primaryStage));
        });
        VBox tradeBox=createChoiseBox("scambi","trade.png");
        tradeBox.setOnMouseClicked(event->{
        	primaryStage.getScene().setRoot(tradePage.getTradePage(primaryStage));
        });
        HBox choisesSpacePane=new HBox();
        choisesSpacePane.getChildren().addAll(opzioniBox,formazioneBox,classificaBox,tradeBox);
        choisesSpacePane.setPrefHeight(140);
        choisesSpacePane.setSpacing(15);
        choisesSpacePane.setAlignment(Pos.CENTER);
        
        
        //opzioni generali per il background
        background.setAlignment(Pos.TOP_CENTER);
        background.setPrefWidth(global.widthMenu);
        background.setPrefHeight(global.heightMenu);
        background.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(0), Insets.EMPTY)));
        background.getChildren().addAll(upperSpacePane,choisesSpacePane); 
        base.getChildren().add(background);   
        base.getStylesheets().add("home.css");
		return base;
	}
	private static VBox createChoiseBox(String titolo, String imm) throws FileNotFoundException {
		VBox box=new VBox();
		Text text=new Text(titolo);
		text.getStyleClass().add("choiseText");
		Image icon=new Image(new FileInputStream("client/src/main/java/imm/"+imm));
		ImageView iconView=new ImageView(icon);
		iconView.setFitHeight(70);
        iconView.setFitWidth(70);
		iconView.getStyleClass().add("choiseImage");
		box.setAlignment(Pos.CENTER);
		box.getStyleClass().add("choise");
		box.setPadding(new Insets(0,0,0,0));
		box.setPrefSize(150, 160);
		box.getChildren().addAll(iconView,text);
		box.setOnMouseClicked(event ->  {
			System.out.println("cliccato il tasto: "+titolo);
		});
		return box;
	}
    public static VBox getHomeButton(Stage primaryStage) throws FileNotFoundException{
        VBox box=new VBox();
        Text text=new Text("home");
        text.getStyleClass().add("homeButtonText");
        Image icon=new Image(new FileInputStream("client/src/main/java/imm/homeButtonIcon.png"));
        ImageView iconView=new ImageView(icon);
        iconView.setFitHeight(70);
        iconView.setFitWidth(70);
        iconView.getStyleClass().add("homeButtonImage");
        box.setAlignment(Pos.CENTER);
        box.getStyleClass().add("homeButtonBox");
        box.setPadding(new Insets(0,0,0,0));
        box.setPrefSize(150, 160);
        box.getChildren().addAll(iconView,text);
        box.setOnMouseClicked(event ->  {
            System.out.println("cliccato il tasto: home");
            primaryStage.getScene().setRoot(getHome(primaryStage));
        });
        return box;
    }
}
