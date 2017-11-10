package BugBuster;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * @author Henry Senior
 * @version 1.0.0
 */
public class BugBuster extends Application
{
	protected Pane root;
	protected Scene sceneGame;
	protected Canvas world;
	protected GraphicsContext gc;

	/**
	 * Provide a static enterace to the program, allowing it to run
	 * @param args
	 */
	public static void main(String args[])
	{
		launch(args);
	}

	/**
	 * Creates a new JavaFX Window
	 * @param primaryStage
	 * @throws Exception
	 */
	@Override
	public void start(Stage primaryStage) throws Exception
	{

	}
}
