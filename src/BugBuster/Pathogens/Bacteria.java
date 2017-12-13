package BugBuster.Pathogens;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bacteria extends Pathogen
{
	public Bacteria(GraphicsContext gc, double x, double y)
	{
		super(gc, x, y);
		health = 1;
		damage = 1;
		img = new Image("resources/Bacteria.png");
		setTileLocation(lastDir);

		update();
	}

	@Override
	public void attack()
	{
		super.attack();
	}

	@Override
	public void move()
	{
		super.move();
	}
}
