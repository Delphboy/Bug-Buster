package BugBuster.Towers;

import BugBuster.*;
import BugBuster.Pathogens.Pathogen;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Tower extends GameObject implements TowerIF
{
	String type;
	String aboutMessage;
	int tileLocX;
	int tileLocY;
	int radius;
	int damage;
	int cost = 10;
	Pathogen target;

	MediaPlayer soundPlayer = null;

	public Tower(String type, int radius, int damage)
	{
		super(null, 0 ,0);
		this.type = type;
		this.radius = radius;
		this.damage = damage;
		aboutMessage = "This will be a short string explaining the \nscience " +
				"behind the tower";
		img = new Image("resources/testTower.png");
	}

	public String getType()
	{
		return type;
	}

	public int getTileLocX()
	{
		return tileLocX;
	}

	public int getTileLocY()
	{
		return tileLocY;
	}

	public int getRadius()
	{
		return radius;
	}

	public int getDamage()
	{
		return damage;
	}

	public Image getImage()
	{
		return img;
	}

	public String getAboutMessage()
	{
		return aboutMessage;
	}

	public int getCost()
	{
		return cost;
	}

	public void setTileLocX(int tileLocX)
	{
		this.tileLocX = tileLocX;
	}

	public void setTileLocY(int tileLocY)
	{
		this.tileLocY = tileLocY;
	}

	public void setGraphicsContext(GraphicsContext graphicsContext)
	{
		gc = graphicsContext;
	}

	public Pathogen getTarget()
	{
		return target;
	}

	@Override
	public String toString()
	{
		return "Tower{" +
				"tileLocX=" + tileLocX +
				", tileLocY=" + tileLocY +
				", radius=" + radius +
				", damage=" + damage +
				'}';
	}

	public void shootPathogen(ArrayList<Pathogen> pathogens)
	{
		findTarget(pathogens);

		if(target != null)
		{
			target.setHealth(target.getHealth() - damage);

			try
			{
				Media soundFile = new Media(this.getClass().getResource("../../resources/default-laser.mp3").toExternalForm());
				soundPlayer = new MediaPlayer(soundFile);
			}catch (MediaException me)
			{
				System.err.println("If you are experiencing this issue on Linux, " +
						"please follow this link for a fix:\n " +
						"https://stackoverflow.com/questions/19549902/cant-play-mp3-file-via-javafx-2-2-media-player");
			}

			if(soundPlayer != null)
				soundPlayer.play();

			if(target.getHealth() <= 0)
				target = null;
		}
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
}
