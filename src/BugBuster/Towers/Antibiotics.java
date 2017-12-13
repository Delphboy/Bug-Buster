package BugBuster.Towers;

import BugBuster.Pathogens.Bacteria;
import BugBuster.Pathogens.Pathogen;
import BugBuster.Player;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Antibiotics extends Tower implements TowerIF
{
	public Antibiotics()
	{
		super("Antibiotics", 1, 5);
		img = new Image("resources/antibiotics-tower.png");
		cost = 30;
		aboutMessage = "Antibiotics are special medicines that\nkill bacteria. There's no point " +
				"using\nthem against anything other than\nbacteria though, as it won't work!";
	}

	@Override
	public void findTarget(ArrayList<Pathogen> pathogens)
	{
		super.findTarget(pathogens);
		if(! (target instanceof Bacteria))
		{
			target = null;
		}
	}

	@Override
	public void upgradeTowerRange()
	{
		Player playerInstance = Player.getInstance();
		if((playerInstance.getCurrency() > (radius * 5)) && (radius < 3))
		{
			radius += 1;
			playerInstance.setCurrency(playerInstance.getCurrency() - radius * 5);
		}
	}

	@Override
	public void upgradeTowerDamage()
	{
		Player playerInstance = Player.getInstance();
		if((playerInstance.getCurrency() > (damage * 10)) && (damage < 10))
		{
			damage += 1;
			playerInstance.setCurrency(playerInstance.getCurrency() - damage * 10);
		}
	}

}
