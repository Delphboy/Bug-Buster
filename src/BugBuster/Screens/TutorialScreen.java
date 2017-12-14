package BugBuster.Screens;

import BugBuster.BugBuster;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

/**
 * A class to represent the TutorialScreen
 * This screen displays the tutorial image, and a button allowing the user to return to the "Main
 * Menu" screen
 * @author Henry Senior
 * @version 1.0.0
 */
public class TutorialScreen extends Pane implements ScreenIF
{
	Canvas canvas;
	GraphicsContext graphicsContext;

	/**
	 * Create a new TutorialScreen
	 */
	public TutorialScreen()
	{
		canvas = new Canvas(BugBuster.STAGE_WIDTH, BugBuster.STAGE_HEIGHT);
		graphicsContext = canvas.getGraphicsContext2D();

		Image backgroundImage = new Image("resources/tutorial-screen.png");
		graphicsContext.drawImage(backgroundImage, 0, 0);

		Button buttonReturnToMenu = new Button("Return to Menu");
		buttonReturnToMenu.setFont(new Font("Cooper Black", 16));
		buttonReturnToMenu.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				BugBuster.updateScene(new MainMenuScreen());
			}
		});
		buttonReturnToMenu.setMinSize(160,40);
		buttonReturnToMenu.setMaxSize(160, 40);
		buttonReturnToMenu.setLayoutX(10);
		buttonReturnToMenu.setLayoutY(10);

		getChildren().addAll(canvas, buttonReturnToMenu);
	}

	/**
	 * Set the canvas and graphicsContext to null
	 */
	@Override
	public void killScreen()
	{
		canvas = null;
		graphicsContext = null;
	}
}
