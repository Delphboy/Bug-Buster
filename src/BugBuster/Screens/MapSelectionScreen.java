package BugBuster.Screens;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class MapSelectionScreen extends Pane
{
	Canvas canvas;
	GraphicsContext graphicsContext;

	public MapSelectionScreen()
	{
		canvas = new Canvas(BugBuster.STAGE_WIDTH, BugBuster.STAGE_HEIGHT);
		graphicsContext = canvas.getGraphicsContext2D();

		Image backGroundImage = new Image("resources/map-selection-screen.png");
		graphicsContext.drawImage(backGroundImage, 0, 0);

		// Create button to return to main menu
		Button buttonReturnToMenu = new Button("Return to Main Menu");
		buttonReturnToMenu.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				BugBuster.updateScene(new MainMenuScreen());
			}
		});
		buttonReturnToMenu.setMinSize(140,30);
		buttonReturnToMenu.setMaxSize(140, 30);
		buttonReturnToMenu.setLayoutX(10);
		buttonReturnToMenu.setLayoutY(10);

		// Create a button to select the 1st map
		Button buttonLeftMap = new Button("Left Map");
		buttonLeftMap.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				System.out.println("Left Map Selected");
			}
		});
		buttonLeftMap.setMinSize(280, 60);
		buttonLeftMap.setMaxSize(280, 60);
		buttonLeftMap.setLayoutX(65);
		buttonLeftMap.setLayoutY(485);

		// Create a button to select the 2nd map
		Button buttonMiddleMap = new Button("Middle Map");
		buttonMiddleMap.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				System.out.println("Middle Map Selected");
			}
		});
		buttonMiddleMap.setMinSize(280, 60);
		buttonMiddleMap.setMaxSize(280, 60);
		buttonMiddleMap.setLayoutX(372);
		buttonMiddleMap.setLayoutY(485);

		// Create a button to select the 3rd Map
		Button buttonRightMap = new Button("Right Map");
		buttonRightMap.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				System.out.println("Right Map Selected");
			}
		});
		buttonRightMap.setMinSize(280, 60);
		buttonRightMap.setMaxSize(280, 60);
		buttonRightMap.setLayoutX(679);
		buttonRightMap.setLayoutY(485);

		getChildren().addAll(canvas, buttonReturnToMenu, buttonLeftMap, buttonMiddleMap,
				buttonRightMap);
	}
}
