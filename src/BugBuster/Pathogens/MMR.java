package BugBuster.Pathogens;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * A class to represent a bacteria pathogen
 * @author Henry Senior
 * @version 1.0.0
 */
public class MMR extends Pathogen
{
	/**
	 * Create a new MMR object (Measles virus)
	 * @param gc
	 * @param x
	 * @param y
	 */
	public MMR(GraphicsContext gc, double x, double y)
	{
		super(gc, x, y);
		health = 15;
		damage = 3;
		img = new Image("resources/MMR.png");
		setTileLocation(lastDir);

		update();
	}
}
