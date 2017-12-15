package BugBuster.Screens.UIComponents;

import BugBuster.BugBuster;
import BugBuster.Player;
import BugBuster.Screens.MainMenuScreen;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * A class to represent the OptionsComponent
 * This UI component holds the "Main Menu" button and the "Start Round" button
 * @author Henry Senior
 * @version 1.0.0
 */
public class OptionsComponent extends Pane implements ComponentIF
{
	//Have a static self pointer to implement the Singleton Design Pattern
	private static OptionsComponent instance;
	private Button mainMenuBtn;
	private Button startRoundBtn;

	/**
	 * Returns a pointer to the static instance of the UI Component, if no instance exists, create
	 * one
	 * @return
	 */
	public static OptionsComponent getInstance()
	{
		if (instance == null)
		{
			instance = new OptionsComponent();
		}
		return instance;
	}

	/**
	 * Create a new OptionsComponent
	 */
	private OptionsComponent()
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
				Player.setInstanceToNull();
				BugBuster.updateScene(new MainMenuScreen());
			}
		});

		// Set the increase damage button
		startRoundBtn = new Button("Start Round");
		startRoundBtn.setFont(new Font("Cooper Black", 12));
		startRoundBtn.setTextFill(Color.GREEN);
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

	@Override
	public void killComponent()
	{
		instance = null;

		mainMenuBtn = null;
		startRoundBtn = null;
	}

	/**
	 * Return a pointer to the start round button
	 * @return startRoundBtn
	 */
	public Button getStartRoundBtn()
	{
		return startRoundBtn;
	}
}
