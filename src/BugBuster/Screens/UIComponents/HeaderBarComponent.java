package BugBuster.Screens.UIComponents;

import BugBuster.Player;
import BugBuster.Screens.BugBuster;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class HeaderBarComponent extends Pane implements ComponentIF
{
	Canvas canvas;
	GraphicsContext graphicsContext;
	Image heart;
	Image emptyHeart;
	Image apple;
	Label waveInfoLabel;
	Label currencyLabel;

	Player player;

	public HeaderBarComponent(Player givenPlayer)
	{
		player = givenPlayer;
		canvas = new Canvas(BugBuster.STAGE_WIDTH, BugBuster.STAGE_HEIGHT);
		graphicsContext = canvas.getGraphicsContext2D();

		// load images
		heart = new Image("resources/full-heart.png");
		emptyHeart = new Image("resources/empty-heart.png");
		apple = new Image("resources/apple.png");

		// Display information about current wave
		waveInfoLabel = new Label("Wave 99/100");
		waveInfoLabel.setFont(new Font("Cooper Black", 24));
		waveInfoLabel.setLayoutX(10);
		waveInfoLabel.setLayoutY(10);

		// Display the amount of currency the player has
		graphicsContext.drawImage(apple, 200, 8, 30, 30);
		currencyLabel = new Label(": " + player.getCurrency());
		currencyLabel.setFont(new Font("Cooper Black", 24));
		currencyLabel.setLayoutX(240);
		currencyLabel.setLayoutY(10);

		update();

		// Add modules to header bar component
		getChildren().add(canvas);
		getChildren().add(waveInfoLabel);
		getChildren().add(currencyLabel);
	}

	@Override
	public void update()
	{


		// display empty hearts
		for (int i = 1; i <= 10; i++)
		{
			if(i <= player.getHealth())
				graphicsContext.drawImage(heart, 330 + (i * 40), 5, 40, 40);
			else
				graphicsContext.drawImage(emptyHeart, 330 + (i * 40), 5, 40, 40);
		}
	}
}
