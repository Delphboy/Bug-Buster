package BugBuster.Towers;

import BugBuster.Pathogens.*;
import BugBuster.Player;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Vaccine extends Tower implements TowerIF
{
	public Vaccine()
	{
		super("Vaccine", 1, 5);
		aboutMessage = "A vaccine protects people from specific\n pathogens. To protect yourself\n" +
				"against more pathogens, you will need\nmore immunisations. Make sure you\nimmunise" +
				" yourself against: \n - The Flu\n - MMR Viruses\n - Smallpox";
		cost = 50;
		img = new Image("resources/vaccine-tower.png");
	}

	@Override
	protected void findTarget(ArrayList<Pathogen> pathogens)
	{
		super.findTarget(pathogens);
		Player playerInstance = Player.getInstance();

		if(target instanceof Bacteria)
		{
			target = null;
		}
		else
		{
			System.out.println("VACCINE DEBUG: " + target );
			if(playerInstance.getCurrentVaccine().equalsIgnoreCase("flu"))
			{
				if(target instanceof Flu == false)
					target = null;
			}
			else if(playerInstance.getCurrentVaccine().equalsIgnoreCase("MMR"))
			{
				if(target instanceof SmallPox)
					target = null;
			}
			else
			{
				System.out.println("WE HAVE A TARGET!" + target);
			}
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
