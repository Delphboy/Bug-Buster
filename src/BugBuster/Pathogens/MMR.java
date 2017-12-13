package BugBuster.Pathogens;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class MMR extends Pathogen
{
	public MMR(GraphicsContext gc, double x, double y)
	{
		super(gc, x, y);
		health = 15;
		damage = 3;
		img = new Image("resources/MMR.png");
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
