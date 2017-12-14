package BugBuster.Screens.UIComponents;

import BugBuster.Player;
import BugBuster.BugBuster;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

/**
 * A class to represent the HeaderBarComponent
 * This UI module displays information about the player, such as remaining health and how many apples they have
 * This UI component utilises the Singleton Design Pattern
 * @author Henry Senior
 * @version 1.0.0
 */
public class HeaderBarComponent extends Pane implements ComponentIF
{
	Canvas canvas;
	GraphicsContext graphicsContext;
	Image heart;
	Image emptyHeart;
	Image apple;
	Label waveInfoLabel;
	Label currencyLabel;

	private Player player;

	// Make a static self pointer to utilise the Singleton Design Patter
	private static HeaderBarComponent instance;

	/**
	 * Return a pointer to the instance of the UI component that exists. If no instance exists,
	 * create one.
	 * @return
	 */
	public static HeaderBarComponent getInstance()
	{
		if (instance == null)
		{
			instance = new HeaderBarComponent();
		}
		return instance;
	}

	/**
	 * Create a new HeaderBarComponent
	 * The constructor has been made private to in order to utilse the Singleton Design Pattern
	 * The instance of the component should be accessed using getInstance()
	 */
	private HeaderBarComponent()
	{
		player = Player.getInstance();
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

	/**
	 * Update the UI component so it displays the most up to date information
	 */
	@Override
	public void update()
	{
		if(instance != null && player != null)
		{
			waveInfoLabel.setText(String.valueOf(player.getWavesComplete()) + "/10");
			currencyLabel.setText(String.valueOf(player.getCurrency()));

			// display hearts
			for (int i = 1; i <= 10; i++)
			{
				if(i <= player.getHealth())
					graphicsContext.drawImage(heart, 330 + (i * 40), 5, 40, 40);
				else
					graphicsContext.drawImage(emptyHeart, 330 + (i * 40), 5, 40, 40);
			}
		}
	}

	/**
	 * Used to destroy the component when it is no longer needed
	 */
	@Override
	public void killComponent()
	{
		instance = null;
		player = null;
		canvas = null;
		graphicsContext = null;
	}
}
