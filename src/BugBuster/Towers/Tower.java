package BugBuster.Towers;

import BugBuster.*;
import BugBuster.Pathogens.Pathogen;
import BugBuster.Screens.UIComponents.Tile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.util.ArrayList;

/**
 * A class to represent the Towers used in the game
 * This class provides a blue print of all the Towers that the game can implement.
 * @author Henry Senior
 * @version 1.0.0
 */
public abstract class Tower extends GameObject implements TowerIF
{
	String type;
	String aboutMessage;

	protected int damage;
	int tileLocX;
	int tileLocY;
	int radius;
	int cost = 10;
	int imageCounter = 1;

	boolean maxRadiusAchieved;
	boolean maxDamageAchieved;
	Image defaultImage;
	Image shootImage;
	Pathogen target;

	/**
	 * Create a new tower
	 * @param type
	 * @param radius
	 * @param damage
	 */
	public Tower(String type, int radius, int damage)
	{
		super(null, 0 ,0);
		this.type = type;
		this.radius = radius;
		this.damage = damage;

		maxRadiusAchieved = false;
		maxDamageAchieved = false;
		aboutMessage = "This will be a short string explaining the \nscience " +
				"behind the tower";
		defaultImage = new Image("resources/testTower.png");
		shootImage = new Image("resources/testTower.png");
		img = defaultImage;
	}

	/**
	 * Return the type of the tower
	 * @return typer
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * Get the radius/range of the the tower
	 * @return radius
	 */
	public int getRadius()
	{
		return radius;
	}

	/**
	 * Get the damage the tower will do on each attack
	 * @return damage
	 */
	public int getDamage()
	{
		return damage;
	}

	/**
	 * Get the tower's display image
	 * @return imgCounter
	 */
	public Image getImage()
	{
		return img;
	}

	/**
	 * Get the aboutMessage which details the science of the tower
	 * @return aboutMessage
	 */
	public String getAboutMessage()
	{
		return aboutMessage;
	}

	/**
	 * get the price of the tower
	 * @return cost
	 */
	public int getCost()
	{
		return cost;
	}

	/**
	 * Set the tower's tile X location and update the GameObject x
	 * @param tileLocX
	 */
	public void setTileLocX(int tileLocX)
	{
		this.tileLocX = tileLocX;
		x = tileLocX * Tile.TILE_WIDTH;
	}

	/**
	 * Set the tower's tile Y location and update the GameObject y
	 * @param tileLocY
	 */
	public void setTileLocY(int tileLocY)
	{
		this.tileLocY = tileLocY;
		y = tileLocY * Tile.TILE_HEIGHT;
	}

	/**
	 * Set the graphicsContext used to draw the tower
	 * @param graphicsContext
	 */
	public void setGraphicsContext(GraphicsContext graphicsContext)
	{
		gc = graphicsContext;
	}

	/**
	 * Get whether or not the maximum tower radius/range has been reached
	 * @return true/false
	 */
	public boolean isMaxRadiusAchieved()
	{
		return maxRadiusAchieved;
	}

	/**
	 * Get whether or not the maximum tower damage has been reached
	 * @return true/false
	 */
	public boolean isMaxDamageAchieved()
	{
		return maxDamageAchieved;
	}

	/**
	 * if the tower has a target;
	 * 		- animate the tower
	 * 		- play a shooting sound
	 * 		- damage the pathogen
	 * 		- if the target dies, reset the target
	 * if there isn't a target, set the tower to use the default image (reset the animation)
	 * @param pathogens - a list of all that pathogens that are on the screen
	 */
	@Override
	public void shootPathogen(ArrayList<Pathogen> pathogens)
	{
		findTarget(pathogens);

		if(target != null)
		{
			// Animate the tower
			if(imageCounter == 1)
			{
				img = defaultImage;
				imageCounter = 2;
			}
			else
			{
				img = shootImage;
				imageCounter = 1;
			}

			//Play Music
			ClassLoader classLoader = getClass().getClassLoader();
			Media soundFile = new Media(classLoader.getResource
					("resources/default-laser.mp3").toExternalForm());
			MediaPlayer player = new MediaPlayer(soundFile);
			player.play();

			//Damage the target
			target.setHealth(target.getHealth() - damage);

			// reset the target if it dies
			if(target.getHealth() <= 0)
				target = null;
		}
		// Reset the tower image if it isn't shooting
		else
		{
			imageCounter = 1;
		}
		update();
	}

	/**
	 * Upgrade the range of the tower.
	 * Whilst a general upgrade is implemented here, it should be overrided by a tower's concrete
	 * implementation
	 */
	@Override
	public void upgradeTowerRange()
	{
		Player playerInstance = Player.getInstance();
		if((playerInstance.getCurrency() > radius * 2))
		{
			radius += 1;
			playerInstance.setCurrency(playerInstance.getCurrency() - radius * 2);
		}
	}

	/**
	 * Upgrade the damage of the tower.
	 * Whilst a general upgrade is implemented here, it should be overrided by a tower's concrete
	 * implementation
	 */
	@Override
	public void upgradeTowerDamage()
	{
		Player playerInstance = Player.getInstance();
		if((playerInstance.getCurrency() > damage * 2))
		{
			damage += 1;
			playerInstance.setCurrency(playerInstance.getCurrency() - damage * 2);
		}
	}

	/**
	 * Find the pathogen closest to the target
	 */
	protected void findTarget(ArrayList<Pathogen> pathogens)
	{
		target = null;
		for (Pathogen p : pathogens)
		{
			for (int y = tileLocY - radius; y <= tileLocY + radius; y++)
			{
				for (int x = tileLocX - radius; x <= tileLocX + radius; x++)
				{
					if(x == p.getTileX() && y == p.getTileY())
						target = p;
					if(target !=  null && target.getHealth() <= 0)
						target = null;
				}
			}
		}
	}

	/**
	 * Draw the tower to the width and height of a tile, rather than 30x30
	 */
	@Override
	public void update()
	{
		if(img != null)
			gc.drawImage(img, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
	}
}
