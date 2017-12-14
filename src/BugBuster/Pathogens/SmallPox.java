package BugBuster.Pathogens;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * A class to represent a Smallpox pathogen
 * @author Henry Senior
 * @version 1.0.0
 */
public class SmallPox extends Pathogen
{
	public SmallPox(GraphicsContext gc, double x, double y)
	{
		super(gc, x, y);
		health = 25;
		damage = 7;
		img = new Image("resources/Smallpox.png");
		setTileLocation(lastDir);

		update();
	}
}
