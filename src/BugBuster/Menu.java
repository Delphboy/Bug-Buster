package BugBuster;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Menu extends Application
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
		root = new Pane();

		sceneGame = new Scene(root, 1024, 768);
		root.setPadding(new Insets(0,0,0,0));
		primaryStage.setScene(sceneGame);
		primaryStage.setTitle("Bug Buster");
		primaryStage.setResizable(false);

		world = new Canvas(1024, 768);
		gc = world.getGraphicsContext2D();
		gc.setFill(Color.color(0.3569, 0.498, 0));
		gc.fillRect(0, 0, world.getWidth(), world.getWidth());
		Image welcomeScreenImg = new Image("resources/welcome-sreen.png");
		gc.drawImage(welcomeScreenImg, 0, 0);

		root.getChildren().add(world);

		primaryStage.show();
	}
}
