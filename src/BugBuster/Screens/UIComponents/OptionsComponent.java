package BugBuster.Screens.UIComponents;

import BugBuster.Screens.BugBuster;
import BugBuster.Screens.MainMenuScreen;
import BugBuster.Towers.Tower;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class OptionsComponent extends Pane implements ComponentIF
{
	Button mainMenuBtn;
	Button startRoundBtn;

	public OptionsComponent()
	{
		setWidth(250);
		setHeight(50);

		// Set the increase damage button
		mainMenuBtn = new Button("Main Menu");
		mainMenuBtn.setFont(new Font("Cooper Black", 12));
		mainMenuBtn.setLayoutX(10);
		mainMenuBtn.setLayoutY(10);
		mainMenuBtn.setMinSize(100, 30);
		mainMenuBtn.setMaxSize(100, 30);
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
		startRoundBtn.setLayoutX(130);
		startRoundBtn.setLayoutY(10);
		startRoundBtn.setMinSize(100, 30);
		startRoundBtn.setMaxSize(100, 30);

		getChildren().addAll(startRoundBtn, mainMenuBtn);
	}

	@Override
	public void update()
	{

	}

	public Button getMainMenuBtn()
	{
		return mainMenuBtn;
	}

	public Button getStartRoundBtn()
	{
		return startRoundBtn;
	}
}
