package BugBuster.Towers;

import BugBuster.Player;
import javafx.scene.image.Image;

/**
 * A class to represent the White Blood Cell Tower
 * White Blood Cell specific upgrade methods have also been added
 * @author Henry Senior
 * @version 1.0.0
 */
public class WhiteBloodCell extends Tower implements TowerIF
{
	/**
	 * Create a new WhiteBloodCell object
	 */
	public WhiteBloodCell()
	{
		super("White Blood Cell", 1, 1);
		cost = 25;
		aboutMessage = "Whiteblood cells are the bodies defence\nmechanism. They protect against " +
				"\nanything that might try to attack us.\nWhilst they're really good on their\nown, " +
				"they sometimes need help from\nother things, such as anitbiotics or\nvaccines.";
		defaultImage = new Image("resources/white-blood-cell.png");
		shootImage = new Image("resources/white-blood-cell-shoot.png");
		img = defaultImage;
	}

	/**
	 * Add specific upgrade tower range method
	 */
	@Override
	public void upgradeTowerRange()
	{
		Player playerInstance = Player.getInstance();
		if((playerInstance.getCurrency() > (radius * 5)) && (radius < 3))
		{
			radius += 1;
			playerInstance.setCurrency(playerInstance.getCurrency() - radius * 5);
		}

		if(radius == 3)
			maxRadiusAchieved = true;
	}

	/**
	 * Add specific upgrade tower damage method
	 */
	@Override
	public void upgradeTowerDamage()
	{
		Player playerInstance = Player.getInstance();
		if((playerInstance.getCurrency() > (damage * 10)) && (damage < 3))
		{
			damage += 1;
			playerInstance.setCurrency(playerInstance.getCurrency() - damage * 10);
		}

		if(damage == 3)
			maxDamageAchieved = true;
	}
}
