package BugBuster.Pathogens;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class SmallPox extends Pathogen
{
	public boolean isForRemoval = false;

	private int endTileX, endTileY, moveCount = 0;
	private Direction lastDir = Direction.RIGHT;
	private ArrayList<Direction> directionQueue = new ArrayList<>();

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
