package BugBuster.Screens;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class MainMenuScreen extends Pane
{
	Canvas canvas;
	GraphicsContext graphicsContext;

	public MainMenuScreen()
	{
		canvas = new Canvas(BugBuster.STAGE_WIDTH, BugBuster.STAGE_HEIGHT);
		graphicsContext = canvas.getGraphicsContext2D();

		Image menuImage = new Image("resources/welcome-sreen.png");
		graphicsContext.drawImage(menuImage, 0, 0);

		Button buttonStartGame = new Button("Start Game");
		buttonStartGame.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				BugBuster.updateScene(new MapSelectionScreen());
			}
		});
		buttonStartGame.setMinSize(280,110);
		buttonStartGame.setMaxSize(280, 110);
		buttonStartGame.setLayoutX(640);
		buttonStartGame.setLayoutY(165);

		Button buttonHowToPlay = new Button("How To Play");
		buttonHowToPlay.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				BugBuster.updateScene(new TutorialScreen());
			}
		});
		buttonHowToPlay.setMinSize(280,110);
		buttonHowToPlay.setMaxSize(280, 110);
		buttonHowToPlay.setLayoutX(640);
		buttonHowToPlay.setLayoutY(300);

		Button buttonExitGame = new Button("Exit Game");
		buttonExitGame.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				System.exit(1);
			}
		});
		buttonExitGame.setMinSize(280,110);
		buttonExitGame.setMaxSize(280, 110);
		buttonExitGame.setLayoutX(640);
		buttonExitGame.setLayoutY(435);


		getChildren().addAll(canvas, buttonStartGame, buttonHowToPlay, buttonExitGame);
	}
}
