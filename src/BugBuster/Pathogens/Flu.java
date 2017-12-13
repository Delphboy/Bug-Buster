package BugBuster.Pathogens;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Flu extends Pathogen
{
	public Flu(GraphicsContext gc, double x, double y)
	{
		super(gc, x, y);
		health = 5;
		img = new Image("resources/flu.png");
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
