package BugBuster.Screens;

import BugBuster.BugBuster;
import BugBuster.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * A class to represent the MainMenuScreen
 * This class is the main screen that players will be greated with once the game loads. Provides a
 * button based menu system, allowing the player to navigate to the "how to play" screen or a "map
 * selection" screen.
 * @author Henry Senior
 * @version 1.0.0
 */
public class MainMenuScreen extends Pane implements ScreenIF
{
	Canvas canvas;
	GraphicsContext graphicsContext;

	/**
	 * Create a new MainMenuScreen
	 */
	public MainMenuScreen()
	{
		canvas = new Canvas(BugBuster.STAGE_WIDTH, BugBuster.STAGE_HEIGHT);
		graphicsContext = canvas.getGraphicsContext2D();

		Image menuImage = new Image("resources/welcome-sreen.png");
		graphicsContext.drawImage(menuImage, 0, 0, BugBuster.STAGE_WIDTH, BugBuster.STAGE_HEIGHT);

		Button buttonStartGame = new Button("Start Game");
		buttonStartGame.setFont(new Font("Cooper Black", 24));
		buttonStartGame.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				Player.setInstanceToNull();
				BugBuster.updateScene(new MapSelectionScreen());
			}
		});
		buttonStartGame.setMinSize(280,110);
		buttonStartGame.setMaxSize(280, 110);
		buttonStartGame.setLayoutX(640);
		buttonStartGame.setLayoutY(165);

		Button buttonHowToPlay = new Button("How To Play");
		buttonHowToPlay.setFont(new Font("Cooper Black", 24));
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
		buttonExitGame.setFont(new Font("Cooper Black", 24));
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

	/**
	 * Set the canvas and graphicsContext to null;
	 */
	@Override
	public void killScreen()
	{
		canvas = null;
		graphicsContext = null;
	}
}
