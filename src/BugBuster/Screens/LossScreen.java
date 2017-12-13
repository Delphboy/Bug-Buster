package BugBuster.Screens;

import BugBuster.BugBuster;
import BugBuster.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class LossScreen extends Pane implements ScreenIF
{
	Canvas canvas;
	GraphicsContext graphicsContext;

	public LossScreen()
	{
		canvas = new Canvas(BugBuster.STAGE_WIDTH, BugBuster.STAGE_HEIGHT);
		graphicsContext = canvas.getGraphicsContext2D();

		Image backgroundImage = new Image("resources/loss-screen.png");
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

		Label applesLeftLabel = new Label("" + Player.getInstance().getCurrency());
		applesLeftLabel.setFont(new Font("Cooper Black", 48));
		applesLeftLabel.setTextFill(Color.BLACK);
		applesLeftLabel.setLayoutX(390);
		applesLeftLabel.setLayoutY(475);

		Label roundsCompletedLabel = new Label("" + Player.getInstance().getWavesComplete());
		roundsCompletedLabel.setFont(new Font("Cooper Black", 48));
		roundsCompletedLabel.setTextFill(Color.BLACK);
		roundsCompletedLabel.setLayoutX(380);
		roundsCompletedLabel.setLayoutY(315);

		getChildren().addAll(canvas, buttonReturnToMenu, applesLeftLabel, roundsCompletedLabel);
	}

	@Override
	public void killScreen()
	{
		canvas = null;
		graphicsContext = null;
	}
}
