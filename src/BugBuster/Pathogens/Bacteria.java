package BugBuster.Pathogens;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bacteria extends Pathogen
{
	public Bacteria(GraphicsContext gc, double x, double y)
	{
		super(gc, x, y);
		health = 5;
		damange = 1;
		img = new Image("resources/test-tile.png");
		setTileLocation(lastDir);

		update();
	}
}
