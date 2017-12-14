package BugBuster.Pathogens;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * A class to represent a bacteria pathogen
 * @author Henry Senior
 * @version 1.0.0
 */
public class Bacteria extends Pathogen
{
	/**
	 * Create a new Bacteria object
	 * @param gc
	 * @param x
	 * @param y
	 */
	public Bacteria(GraphicsContext gc, double x, double y)
	{
		super(gc, x, y);
		health = 5;
		damage = 2;
		img = new Image("resources/Bacteria.png");
		setTileLocation(lastDir);

		update();
	}
}
