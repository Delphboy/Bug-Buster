package BugBuster.Pathogens;

import BugBuster.GameObject;
import BugBuster.Player;
import BugBuster.Screens.BugBuster;
import BugBuster.Screens.TutorialScreen;
import BugBuster.Screens.UIComponents.HeaderBarComponent;
import BugBuster.Screens.UIComponents.WorldViewComponent;
import BugBuster.Tile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Pathogen extends GameObject
{
	public boolean isForRemoval = false;
	int health,damange = 1, tileX, tileY;

	private int endTileX, endTileY, moveCount = 0;
	private Direction lastDir = Direction.RIGHT;
	private ArrayList<Direction> directionQueue = new ArrayList<>();

	public Pathogen(GraphicsContext gc, double x, double y)
	{
		super(gc, x, y);
		health = 10;
		img = new Image("resources/test-tile.png");
		setTileLocation(lastDir);

		update();
	}

	/**
	 * return the x-coordinate of the tile the pathogen is currently on
	 * @return tileX
	 */
	public int getTileX()
	{
		return tileX;
	}

	/**
	 * return the y-coordinate of the tile the pathogen is currently on
	 * @return tileY
	 */
	public int getTileY()
	{
		return tileY;
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
			endTileX = scannedX;
			endTileY = scannedY;
			System.out.println("last tile found! " + endTileX + ":" +
					endTileY);
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

		// Iterate movement count
		if(moveCount == Tile.TILE_WIDTH || moveCount == Tile.TILE_HEIGHT)
			moveCount = 0;

		// Move pathogen across a tile
		switch (operation)
		{
			case UP:
				y -= 1;
				update();
				break;
			case DOWN:
				y += 1;
				update();
				break;
			case LEFT:
				x -= 1;
				update();
				break;
			case RIGHT:
				x += 1;
				update();
				break;
		}

		moveCount += 1;

		// Handle removing pathogen and doing damage, if it makes it through
		// the map
		if(isOnTile(endTileX, endTileY))
		{
			isForRemoval = true;
			Player player = Player.getInstance();
			if(player.getHealth() > 1)
			{
				player.setHealth(player.getHealth() - damange);
				HeaderBarComponent.getInstance().update();
			}
			else
				BugBuster.updateScene(new TutorialScreen());
		}

		setTileLocation(operation);

		if(moveCount == Tile.TILE_WIDTH || moveCount == Tile.TILE_HEIGHT)
			directionQueue.remove(0);
	}

	/**
	 * Update the tile location based on the actual X,Y coordinates of the
	 * pathogen
	 * IF pathogen is going LEFT or UP, add 30 to the x and y to account for
	 * width and height of the pathogen.
	 */
	private void setTileLocation(Direction lastDir)
	{
		if(lastDir == Direction.LEFT || lastDir == Direction.UP)
		{
			tileX = (int)((x + 30) / Tile.TILE_WIDTH);
			tileY = (int)((y + 30) / Tile.TILE_HEIGHT);
		}
		else
		{
			tileX = (int)(x / Tile.TILE_WIDTH);
			tileY = (int)(y / Tile.TILE_HEIGHT);
		}

		System.out.println("Pathogen Location: X: " + tileX + "\tY:" + tileY +
				"\t\tx:" + x + " " + "\ty: " + y);
	}

	/**
	 * check whether the pathogen is on a specific tile.
	 * @param tileX
	 * @param tileY
	 * @return TRUE : when pathogen is on a tile
	 * @return FALSE : When pathogen isn't on the tile
	 */
	public boolean isOnTile(int tileX, int tileY)
	{
		return ((x >= tileX * Tile.TILE_WIDTH) &&
				( x <= (tileX + 1) * Tile.TILE_WIDTH) &&
				(y >= tileY * Tile.TILE_HEIGHT) &&
				( y <= (tileY+ 1) * Tile.TILE_HEIGHT));
	}
}
