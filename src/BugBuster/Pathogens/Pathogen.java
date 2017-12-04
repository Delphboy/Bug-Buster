package BugBuster.Pathogens;

import BugBuster.GameObject;
import BugBuster.Screens.BugBuster;
import BugBuster.Screens.GameScreen;
import BugBuster.Screens.UIComponents.WorldViewComponent;
import BugBuster.Tile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Pathogen extends GameObject
{
	int health,damange = 1, tileX, tileY;

	private int previousX, previousY;

	public Pathogen(GraphicsContext gc, double x, double y)
	{
		super(gc, x, y);
		health = 10;
		img = new Image("resources/test-tile.png");
		setTileLocation();

		update();
	}

	/**
	 * search the world the the next tile that the pathogen should move to, then move pathogen
	 * accordingly
	 * @param world
	 */
	public void navigate(Tile[][] world)
	{
		if((tileX < 16 && tileX >= 0) && (tileY < 12 && tileY >= 0))
		{
			// while pathogen can go right
			if (world[tileX + 1][tileY].isWalkable() && (previousX != tileX + 1))
			{
				System.out.println("Right");
				x += 1;
				setTileLocation();
				update();
			}

			// while pathogen can go down
			if (world[tileX][tileY + 1].isWalkable() && (previousY != tileY + 1))
			{
				System.out.println("Down");
				y += 1;
				setTileLocation();
				update();
			}

			// while pathogen can go up
			if (world[tileX][tileY - 1].isWalkable() && (previousY != tileY - 1))
			{
				System.out.println("Up");
				y -= 1;
				setTileLocation();
				update();
			}

			// while pathogen can go left
			if(tileX > 0)
			{
				if(world[tileX - 1][tileY].isWalkable() && (previousX != tileX - 1))
				{
					System.out.println("Left");
					x -= 1;
					setTileLocation();
					update();
				}
			}
		}
		else
			System.out.println("INDEX ERROR");
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
