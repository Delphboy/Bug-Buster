package BugBuster.Pathogens;

import BugBuster.GameObject;
import BugBuster.Player;
import BugBuster.BugBuster;
import BugBuster.Screens.LossScreen;
import BugBuster.Screens.UIComponents.HeaderBarComponent;
import BugBuster.Tile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Pathogen extends GameObject
{
	public boolean isForRemoval = false;
	protected int health, damage, tileX, tileY;

	protected int endTileX, endTileY, moveCount = 0;
	protected Direction lastDir = Direction.RIGHT;
	protected MoveCommand directionQueue = new MoveCommand();

	public Pathogen(GraphicsContext gc, double x, double y)
	{
		super(gc, x, y);
		health = 5;
		img = new Image("resources/test-tile.png");
		setTileLocation(lastDir);
		damage = 1;
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
	 * Return the amount of health the pathogen currently has
	 * @return (int) health
	 */
	public int getHealth()
	{
		return health;
	}

	/**
	 * Sets the health of the pathogen. Used when towers deal damage
	 * @param health
	 */
	public void setHealth(int health)
	{
		this.health = health;
	}

	/**
	 * Deal damage to the player if the pathogen is on the end tile
	 */
	public void attack()
	{
		if(isOnTile(endTileX, endTileY))
		{
			isForRemoval = true;
			Player player = Player.getInstance();
			player.setHealth(player.getHealth() - damage);
			health = 0;
			// End the game if the player is dead
			if (player.getHealth() <= 0)
				BugBuster.updateScene(new LossScreen());
		}
	}

	/**
	 * If the pathogen still has tiles it can move to, move the pathogen. If there aren't any tiles
	 * to which the pathogen can move, build a route of possible moves
	 * @param world
	 */
	public void navigate(Tile[][] world)
	{
		if(directionQueue.getLength() != 0)
			move();
		else
			buildRoute(world);
	}

	/**
	 * Build a route that the tile will take. Tiles are scanned and a queue
	 * of movement operations is built. This is achieved by implementing the Command Pattern
	 * @param world
	 */
	protected void buildRoute(Tile[][] world)
	{
		int scannedX = tileX;
		int scannedY = tileY;
		if((tileX < 16 && tileX >= 0) && (tileY < 12 && tileY >= 0))
		{
			Tile scannedTile = world[scannedX][scannedY];
			while(!scannedTile.isEndTile() && scannedX < 15 && scannedY < 11)
			{
				scannedTile = world[scannedX][scannedY];

				if((lastDir != Direction.LEFT)
						&& (world[scannedX + 1][scannedY].isWalkable()))
				{
					directionQueue.addCommand(Direction.RIGHT);
					lastDir = Direction.RIGHT;
					scannedX += 1;
				}
				else if((lastDir != Direction.RIGHT) &&
						(world[scannedX -1][scannedY].isWalkable()))
				{
					directionQueue.addCommand(Direction.LEFT);
					lastDir = Direction.LEFT;
					scannedX -= 1;
				}
				else if((lastDir != Direction.UP) &&
						(world[scannedX][scannedY + 1].isWalkable()))
				{
					directionQueue.addCommand(Direction.DOWN);
					lastDir = Direction.DOWN;
					scannedY += 1;
				}
				else if((lastDir != Direction.DOWN) &&
						(world[scannedX][scannedY - 1].isWalkable()))
				{
					directionQueue.addCommand(Direction.UP);
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
		}
		else
			System.out.println("INDEX ERROR");
	}


	/**
	 * Use the movement operations created by the buildRoute() method to
	 * navigate the pathogen through the world
	 */
	public void move()
	{
		Direction operation = (Direction)directionQueue.peek();

		// Iterate movement count
		if(moveCount == Tile.TILE_WIDTH  || moveCount == Tile.TILE_HEIGHT)
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

		setTileLocation(operation);

		// Once the pathogen has moved over a tile, remove the operation from the movement queue
		if(moveCount == Tile.TILE_WIDTH || moveCount == Tile.TILE_HEIGHT)
			directionQueue.remove();
	}

	/**
	 * Update the tile location based on the actual X,Y coordinates of the
	 * pathogen
	 * IF pathogen is going LEFT or UP, add 30 to the x and y to account for
	 * width and height of the pathogen.
	 */
	protected void setTileLocation(Direction lastDir)
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
