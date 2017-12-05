package BugBuster.Pathogens;

import BugBuster.GameObject;
import BugBuster.Screens.BugBuster;
import BugBuster.Screens.GameScreen;
import BugBuster.Screens.UIComponents.WorldViewComponent;
import BugBuster.Tile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Pathogen extends GameObject
{
	int health,damange = 1, tileX, tileY;

	private int previousX, previousY;
	private Direction lastDir = Direction.RIGHT;
	private ArrayList<Direction> directionQueue = new ArrayList<>();

	public Pathogen(GraphicsContext gc, double x, double y)
	{
		super(gc, x, y);
		health = 10;
		img = new Image("resources/test-tile.png");
		setTileLocation();

		update();
	}

	/**
	 * search the world the the next tile that the pathogen should move to, then
	 * move pathogen accordingly
	 * @param world
	 */
	public void navigate(Tile[][] world)
	{
		if(directionQueue.size() != 0)
			moveTowardsTile(1,1);
		else
			buildRoute(world);
	}

	/**
	 *
	 * @param world
	 */
	private void buildRoute(Tile[][] world)
	{
		int scannedX = tileX;
		int scannedY = tileY;
		if((tileX < 16 && tileX >= 0) && (tileY < 12 && tileY >= 0))
		{
			Tile scannedTile = world[scannedX][scannedY];
			while(!scannedTile.isEndTile() && scannedX < 15 && scannedY < 11)
			{
				scannedTile = world[scannedX][scannedY];
				System.out.println(scannedTile.getPosX() + " : " +
						scannedTile.getPosY());

				if((lastDir != Direction.LEFT)
						&& (world[scannedX + 1][scannedY].isWalkable()))
				{
					directionQueue.add(Direction.RIGHT);
					lastDir = Direction.RIGHT;
					scannedX += 1;
				}
				else if((lastDir != Direction.RIGHT) &&
						(world[scannedX -1][scannedY].isWalkable()))
				{
					directionQueue.add(Direction.LEFT);
					lastDir = Direction.LEFT;
					scannedX -= 1;
				}
				else if((lastDir != Direction.UP) &&
						(world[scannedX][scannedY + 1].isWalkable()))
				{
					directionQueue.add(Direction.DOWN);
					lastDir = Direction.DOWN;
					scannedY += 1;
				}
				else if((lastDir != Direction.DOWN) &&
						(world[scannedX][scannedY - 1].isWalkable()))
				{
					directionQueue.add(Direction.UP);
					lastDir = Direction.UP;
					scannedY -= 1;
				}
				else
				{
					System.out.println("I don't know where to go");
				}
			}
			System.out.println("last tile found! " + tileX + ":" + tileY);
		}
		else
			System.out.println("INDEX ERROR");
	}

	private void moveTowardsTile(int tileX, int tileY)
	{
		for (Direction d:directionQueue)
		{
			System.out.println(d.toString());
		}
	}

	/**
	 * update the tile location based on the actual X,Y coordinates of the pathogen
	 */
	private void setTileLocation()
	{
		previousX = tileX;
		previousY = tileY;
		tileX = (int)(x) / Tile.TILE_WIDTH;
		tileY = (int)(y) / Tile.TILE_HEIGHT;
		System.out.println("X: " + tileX + "\tY:" + tileY);
	}

}
