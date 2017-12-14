package BugBuster.Pathogens;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * A class to represent a Flu pathogen
 * @author Henry Senior
 * @version 1.0.0
 */
public class Flu extends Pathogen
{
	/**
	 * Create a new Flu object
	 * @param gc
	 * @param x
	 * @param y
	 */
	public Flu(GraphicsContext gc, double x, double y)
	{
		super(gc, x, y);
		health = 5;
		img = new Image("resources/flu.png");
		setTileLocation(lastDir);

		update();
	}
}
