package BugBuster.Towers;

import BugBuster.Player;
import javafx.scene.image.Image;

public class WhiteBloodCell extends Tower implements TowerIF
{
	public WhiteBloodCell()
	{
		super("White Blood Cell", 1, 1);
		img = new Image("resources/white-blood-cell.png");
		cost = 25;
		aboutMessage = "Whiteblood cells are the bodies defence\nmechanism. They protect against " +
				"\nanything that might try to attack us.\nWhilst they're really good on their\nown, " +
				"they sometimes need help from\nother things, such as anitbiotics or\nvaccines.";
	}

	@Override
	public void upgradeTowerRange()
	{
		Player playerInstance = Player.getInstance();
		if((playerInstance.getCurrency() > (radius * 5)) && (radius < 5))
		{
			radius += 1;
			playerInstance.setCurrency(playerInstance.getCurrency() - radius * 5);
		}
	}

	@Override
	public void upgradeTowerDamage()
	{
		Player playerInstance = Player.getInstance();
		if((playerInstance.getCurrency() > (damage * 10)) && (damage < 5))
		{
			damage += 1;
			playerInstance.setCurrency(playerInstance.getCurrency() - damage * 10);
		}
	}
}
