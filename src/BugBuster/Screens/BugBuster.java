package BugBuster.Screens;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author Henry Senior
 * @version 1.0.0
 */
public class BugBuster extends Application
{
	/*
	Width and Height are public so they can be used elsewhere in the program. Change them here to
	change them everywhere else.
	 */
	public static final int STAGE_WIDTH = 1024;
	public static final int STAGE_HEIGHT = 768;

	private static Stage window;
	private static Scene scene;


	/**
	 * Set the
	 * @param primaryStage
	 * @throws Exception
	 */
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		window = primaryStage;

		// Set up the style of the window
		window.setTitle("Bug Buster | Software Architecture Assignment - Henry Senior");
		window.setResizable(false);
		window.initStyle(StageStyle.UNDECORATED);

		//by default, start the MainMenuScreen
		updateScene(new MainMenuScreen());

		// Display the window to the user
		window.show();
	}

	/**
	 * Create a new scene using the given Parent object. This scene is then used by the stage
	 * @param newParent
	 */
	public static void updateScene(Parent newParent)
	{
		scene = new Scene(newParent, STAGE_WIDTH, STAGE_HEIGHT);
		window.setScene(scene);
	}
}
