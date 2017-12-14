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
 * A class to represent the MapSelectionScreen
 * This screen displays the three maps that the use can select. A game screen object is created
 * based off the user's seleciton of map.
 * @author Henry Senior
 * @version 1.0.0
 */
public class MapSelectionScreen extends Pane implements ScreenIF
{
	Canvas canvas;
	GraphicsContext graphicsContext;

	/**
	 * Create a new MapSelectionScreen
	 */
	public MapSelectionScreen()
	{
		canvas = new Canvas(BugBuster.STAGE_WIDTH, BugBuster.STAGE_HEIGHT);
		graphicsContext = canvas.getGraphicsContext2D();

		Image backGroundImage = new Image("resources/map-selection-screen.png");
		graphicsContext.drawImage(backGroundImage, 0, 0);

		// Create button to return to main menu
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

		// Create a button to select the 1st map
		Button buttonLeftMap = new Button("Left Map");
		buttonLeftMap.setFont(new Font("Cooper Black", 24));
		buttonLeftMap.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				System.out.println("Left Map Selected");
				BugBuster.updateScene(new GameScreen(1));
			}
		});
		buttonLeftMap.setMinSize(200, 45);
		buttonLeftMap.setMaxSize(200, 45);
		buttonLeftMap.setLayoutX(115);
		buttonLeftMap.setLayoutY(420);

		// Create a button to select the 2nd map
		Button buttonMiddleMap = new Button("Middle Map");
		buttonMiddleMap.setFont(new Font("Cooper Black", 24));
		buttonMiddleMap.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				System.out.println("Middle Map Selected");
				BugBuster.updateScene(new GameScreen(2));
			}
		});
		buttonMiddleMap.setMinSize(200, 45);
		buttonMiddleMap.setMaxSize(200, 45);
		buttonMiddleMap.setLayoutX(424);
		buttonMiddleMap.setLayoutY(420);

		// Create a button to select the 3rd Map
		Button buttonRightMap = new Button("Right Map");
		buttonRightMap.setFont(new Font("Cooper Black", 24));
		buttonRightMap.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				System.out.println("Right Map Selected");
				BugBuster.updateScene(new GameScreen(3));
			}
		});
		buttonRightMap.setMinSize(200, 45);
		buttonRightMap.setMaxSize(200, 45);
		buttonRightMap.setLayoutX(733);
		buttonRightMap.setLayoutY(420);

		getChildren().addAll(canvas, buttonReturnToMenu, buttonLeftMap, buttonMiddleMap,
				buttonRightMap);
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
