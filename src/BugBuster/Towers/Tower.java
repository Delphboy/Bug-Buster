package BugBuster.Towers;

import BugBuster.Controller;
import BugBuster.GameObject;
import BugBuster.Pathogens.NullPathogen;
import BugBuster.Pathogens.Pathogen;
import BugBuster.Player;
import BugBuster.Screens.UIComponents.HeaderBarComponent;
import javafx.animation.*;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
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
	int fireRate;
	Pathogen target;

	MediaPlayer soundPlayer = null;

	Timer shootTimer = new Timer();
	TimerTask task = new TimerTask()
	{
		@Override
		public void run()
		{
			shootPathogen();
		}
	};

	public Tower(String type, int radius, int damage)
	{
		super(null, 0 ,0);
		this.type = type;
		this.radius = radius;
		this.damage = damage;
		this.fireRate = 3;
		aboutMessage = "This will be a short string explaining the \nscience " +
				"behind the tower";
		img = new Image("resources/testTower.png");
		shootTimer.scheduleAtFixedRate(task, 0, (long)((1.0 / this.fireRate) * 1000));
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

	public void increaseDamage()
	{
		Player playerInstance = Player.getInstance();
		if(playerInstance.getCurrency() >= damage * 10)
		{
			damage += 1;
			playerInstance.setCurrency(playerInstance.getCurrency() - damage * 10);
		}
	}

	public void increaseRadius()
	{
		Player playerInstance = Player.getInstance();
		if(playerInstance.getCurrency() >= radius * 5)
		{
			radius += 1;
			playerInstance.setCurrency(playerInstance.getCurrency() - damage * 10);
		}
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

	private void shootPathogen()
	{
		if(target != null)
		{
			target.setHealth(target.getHealth() - damage);
			animate();
			try
			{
				Media soundFile = new Media
						(new File("src/resources/sounds/default-laser.mp3")
								.toURI()
								.toString());
				System.out.println("Sound file @" + soundFile.getSource());
				soundPlayer = new MediaPlayer(soundFile);
			}catch (MediaException me)
			{
				System.err.println("If you are experiencing this issue on Linux, " +
						"please follow this link for a fix:\n " +
						"https://stackoverflow.com/questions/19549902/cant-play-mp3-file-via-javafx-2-2-media-player");
			}

			if(soundPlayer != null)
			{
				soundPlayer.setOnEndOfMedia(new Runnable()
				{
					@Override
					public void run()
					{
						soundPlayer.seek(Duration.ZERO);
					}
				});
				soundPlayer.play();
			}
		}
	}

	private void animate()
	{

	}

	@Override
	public void upgradeTowerRange()
	{

	}

	@Override
	public void upgradeTowerDamage()
	{

	}

	/**
	 * Find the pathogen closest to the target
	 */
	public void findTarget(ArrayList<Pathogen> pathogens)
	{
		for (Pathogen p : pathogens)
		{
			target = null;
			for (int y = tileLocY - radius; y <= tileLocY + radius; y++)
			{
				for (int x = tileLocX - radius; x <= tileLocX + radius; x++)
				{
					if(x == p.getTileX() && y == p.getTileY())
						target = p;
					if(target !=  null && target.getHealth() <= 0)
						target = null;
					if(target instanceof NullPathogen)
						target = null;
				}
			}
		}
	}
}
