package BugBuster;

import BugBuster.Screens.GameScreen;
import BugBuster.Screens.MainMenuScreen;
import BugBuster.Screens.ScreenIF;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

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
	public static final int STAGE_WIDTH = 1050;
	public static final int STAGE_HEIGHT = 650;

	private static Stage window;
	private static Scene scene;

	public static void main(String[] args)
	{
		launch(args);
	}

	/**
	 * Set the
	 * @param primaryStage
	 * @throws Exception
	 */
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		window = primaryStage;

		// Force shut the program to kill background processes
		window.setOnCloseRequest(new EventHandler<WindowEvent>()
		{
			@Override
			public void handle(WindowEvent event)
			{
				System.exit(1);
			}
		});

		// Set up the style of the window
		window.setTitle("Bug Buster | Software Architecture Assignment - Henry Senior");
		window.setResizable(false);
		window.initStyle(StageStyle.DECORATED);

		//by default, start the MainMenuScreen
		updateScene(new MainMenuScreen());
//		updateScene(new GameScreen(1));

		// Display the window to the user
		window.show();
	}

	/**
	 * Create a new scene using the given Parent object. This scene is then used by the stage
	 * @param newParent
	 */
	public static void updateScene(Parent newParent)
	{
		if (scene != null)
		{
			Parent oldParent = scene.getRoot();
			Pane oldPane = (Pane)oldParent;
			((ScreenIF) oldPane).killScreen();
		}

		scene = new Scene(newParent, STAGE_WIDTH, STAGE_HEIGHT);
		window.setScene(scene);
	}
}
