package BugBuster.Pathogens;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class SmallPox extends Pathogen
{
	public SmallPox(GraphicsContext gc, double x, double y)
	{
		super(gc, x, y);
		health = 25;
		damage = 10;
		img = new Image("resources/Smallpox.png");
		setTileLocation(lastDir);

		update();
	}
}
