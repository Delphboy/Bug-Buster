package BugBuster.Screens.UIComponents;

import BugBuster.Screens.BugBuster;
import BugBuster.Screens.MainMenuScreen;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class TowerShopComponent extends ScrollPane
{
	Button mainMenuBtn;
	Button startRoundBtn;

	public TowerShopComponent()
	{
		setWidth(250);
		setHeight(50);

		// Set the increase damage button
		mainMenuBtn = new Button("Main Menu");
		mainMenuBtn.setFont(new Font("Cooper Black", 12));
		mainMenuBtn.setLayoutX(10);
		mainMenuBtn.setLayoutY(10);
		mainMenuBtn.setMinSize(85, 30);
		mainMenuBtn.setMaxSize(85, 30);
		mainMenuBtn.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				BugBuster.updateScene(new MainMenuScreen());
			}
		});

		// Set the increase damage button
		startRoundBtn = new Button("Start Round");
		startRoundBtn.setFont(new Font("Cooper Black", 12));
		startRoundBtn.setLayoutX(140);
		startRoundBtn.setLayoutY(10);
		startRoundBtn.setMinSize(100, 30);
		startRoundBtn.setMaxSize(100, 30);
		startRoundBtn.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				System.out.println("Start Round");
			}
		});

		getChildren().addAll(mainMenuBtn, startRoundBtn);
		setContent(getChildren().get(0));
	}
}
