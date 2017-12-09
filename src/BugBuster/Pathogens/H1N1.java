package BugBuster.Pathogens;

import BugBuster.GameObject;
import BugBuster.Player;
import BugBuster.Screens.BugBuster;
import BugBuster.Screens.TutorialScreen;
import BugBuster.Screens.UIComponents.HeaderBarComponent;
import BugBuster.Tile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class H1N1 extends Pathogen
{
	public boolean isForRemoval = false;
	int health,damange = 1, tileX, tileY;

	private int endTileX, endTileY, moveCount = 0;
	private Direction lastDir = Direction.RIGHT;
	private ArrayList<Direction> directionQueue = new ArrayList<>();

	public H1N1(GraphicsContext gc, double x, double y)
	{
		super(gc, x, y);
		health = 5;
		img = new Image("resources/test-tile.png");
		setTileLocation(lastDir);

		update();
	}
}
