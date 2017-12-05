package BugBuster.Pathogens;

import BugBuster.GameObject;
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
			move();
		else
			buildRoute(world);
	}

	/**
	 * Build a route that the tile will take. Tiles are scanned and a queue
	 * of movement operations is built.
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


	/**
	 * Use the movement operations created by the buildRoute() method to
	 * navigate the pathogen through the world
	 */
	private void move()
	{
		Direction operation = directionQueue.get(0);

		switch (operation)
		{
			case UP: y -= tileY - 1; break;
			case DOWN: y += tileY + 1; break;
			case LEFT: x -= tileX - 1; break;
			case RIGHT: x += tileX + 1; break;
		}
		update();
		directionQueue.remove(0);
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

	/**
	 * check whether the pathogen is on a specific tile.
	 * @param tileX
	 * @param tileY
	 * @return TRUE : when pathogen is on a tile
	 * @return FALSE : When pathogen isn't on the tile
	 */
	private boolean isOnTile(int tileX, int tileY)
	{
		boolean returnValue = true;

		if (x < tileX * Tile.TILE_WIDTH && x > tileX * Tile.TILE_WIDTH + Tile.TILE_WIDTH)
			returnValue = false;

		if (y < tileY * Tile.TILE_HEIGHT && y > tileY * Tile.TILE_HEIGHT+ Tile
				.TILE_HEIGHT)
			returnValue = false;

		return returnValue;
	}

}
