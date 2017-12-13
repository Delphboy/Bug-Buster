package BugBuster.Pathogens;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class MMR extends Pathogen
{
	public boolean isForRemoval = false;
	int health,damange = 1, tileX, tileY;

	private int endTileX, endTileY, moveCount = 0;
	private Direction lastDir = Direction.RIGHT;
	private ArrayList<Direction> directionQueue = new ArrayList<>();

	public MMR(GraphicsContext gc, double x, double y)
	{
		super(gc, x, y);
		health = 10;
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
