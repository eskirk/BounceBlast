package layout;
import game.GameEngine;
import game.GameScene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import layout.components.BackToMainMenuButton;
import layout.components.PlayOnlineButton;

public class PlayOnlineLobby extends Scene {
	// JavaFX
	private GridPane gridpane;
	
	// Components
	private BackToMainMenuButton back;
	private Button start;
	private Button temp;
	private Button settings;
	private Text gameTitle;
	private GridPane box;
	private ColumnConstraints cons1;
	private ColumnConstraints cons2;
	private RowConstraints rcons1;
	private RowConstraints rcons2;
	
	// Constants
	private static final int SCREENBUTTONCOL = 2;
	
	public PlayOnlineLobby(Main app) {
		super(new GridPane(), 800, 600);
		this.gridpane =  (GridPane) getRoot();
		
		// Gridpane
		cons1 = new ColumnConstraints();
        cons1.setHgrow(Priority.NEVER);
        gridpane.getColumnConstraints().add(cons1);
        cons2 = new ColumnConstraints();
        cons2.setHgrow(Priority.ALWAYS);
        gridpane.getColumnConstraints().addAll(cons1, cons2);
        rcons1 = new RowConstraints();
        rcons1.setVgrow(Priority.NEVER);
        rcons2 = new RowConstraints();
        rcons2.setVgrow(Priority.ALWAYS);    
        gridpane.getRowConstraints().addAll(rcons1, rcons2);
		gridpane.setAlignment(Pos.TOP_CENTER);
		gridpane.setStyle("-fx-background-image: url('file:resource/background.jpg');" +
				 "-fx-background-size: stretch;-fx-background-position:center top;");
		 
		// BounceBlast text
		gameTitle = new Text();
		gameTitle.setFont(new Font(20));
		gameTitle.setFill(Color.WHITE);
		gameTitle.setText("Play Online");
		gameTitle.setStyle("-fx-font: 75 arial;");
		gameTitle.setTextAlignment(TextAlignment.CENTER);
		GridPane.setHalignment(gameTitle, HPos.CENTER);
		gridpane.add(gameTitle, SCREENBUTTONCOL, 0);
		GridPane.setMargin(gameTitle, new Insets(5, 10, 5, 10));
		
		 //Adding GridPane
        box = new GridPane();
        box.setPadding(new Insets(20,20,20,20));
        box.setMaxWidth(650);
        box.setMaxHeight(250);
        box.setHgap(5);
        box.setVgap(5);
        box.getColumnConstraints().addAll(cons1, cons2);
        box.getRowConstraints().addAll(rcons1, rcons2);
        box.setStyle("-fx-background-color: rgba(0, 0, 128, 0.4);" +
				 "-fx-background-position:center top;" +
				 "-fx-border-color: white;-fx-border-width: 3;");
		
	    GridPane.setHalignment(box, HPos.CENTER);
		gridpane.add(box, SCREENBUTTONCOL, 1);

		// Start Button
		start = new PlayOnlineButton(app, "Start", new InGame(app));
		start.setPrefHeight(25);
		start.setPrefWidth(65);
		start.setStyle("-fx-border-width: 3;" + 
				"-fx-border-color: white;" + 
				"-fx-background-color: #24618F;" +
				"-fx-font-size: 16;" + 
				"-fx-text-fill: white;");
		gridpane.add(start, 2, 5);
		GridPane.setHalignment(start, HPos.CENTER);
		GridPane.setMargin(start, new Insets(5, 80, 30, 10));
		
		// Lobby menu button
		settings = new PlayOnlineButton(app, "Settings", new Settings(app));
		settings.setPrefHeight(25);
		settings.setPrefWidth(100);
		settings.setStyle("-fx-border-width: 3;" + 
				"-fx-border-color: white;" + 
				"-fx-background-color: #24618F;" +
				"-fx-font-size: 16;" + 
				"-fx-text-fill: white;");
		gridpane.add(settings, 2, 4);
		GridPane.setHalignment(settings, HPos.CENTER);
		GridPane.setMargin(settings, new Insets(5, 80, 30, 10));	
		
		// Back button
		back = new BackToMainMenuButton(app, "Back");
		gridpane.add(back, 0, 10);
		GridPane.setHalignment(back, HPos.CENTER);
		GridPane.setMargin(back, new Insets(5, 10, 5, 10));
		// Return to Main Menu when back is pressed
		 
		//Temporary GameScene Button
		temp = new PlayOnlineButton(app, "Game Scene", new GameScene(app, "1", "Basketball"));
		temp.setPrefHeight(25);
		temp.setPrefWidth(200);
		gridpane.add(temp, 2, 10);
		GridPane.setHalignment(back, HPos.CENTER);
		GridPane.setMargin(back, new Insets(5, 10, 5, 10));
		// Return to Main Menu when back is pressed
		temp.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				GameScene scene = new GameScene(app, "1", "Basketball");
				app.updateScene(scene);
				
				app.setThread(new Thread(new GameEngine(app, scene)));
				Thread mainThread = app.getThread();
				mainThread.start();
				
		        //app.thread = new Thread(new GameEngine(app, scene));
		        //app.thread.start();
			}
		});
	}
	public BackToMainMenuButton getBackButton() {
		return this.back;
	}
}
