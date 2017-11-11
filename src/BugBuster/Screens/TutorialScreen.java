package BugBuster.Screens;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class TutorialScreen extends Pane
{
	Canvas canvas;
	GraphicsContext graphicsContext;

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
}
