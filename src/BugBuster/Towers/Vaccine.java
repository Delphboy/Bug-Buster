package BugBuster.Towers;

import BugBuster.Pathogens.Bacteria;
import BugBuster.Pathogens.Flu;
import BugBuster.Pathogens.MMR;
import BugBuster.Pathogens.Pathogen;
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
		cost = 35;
		img = new Image("resources/vaccine-tower.png");
	}

	@Override
	public void findTarget(ArrayList<Pathogen> pathogens)
	{
		super.findTarget(pathogens);
		Player playerInstance = Player.getInstance();
		if(target instanceof Bacteria)
		{
			target = null;
		}
		else
		{
			if(playerInstance.getCurrentVaccine().equalsIgnoreCase("flu"))
			{
				if(target instanceof Flu == false)
					target = null;
			}
			else if(playerInstance.getCurrentVaccine().equalsIgnoreCase("MMR"))
			{
				if(target instanceof Flu == false && target instanceof MMR == false)
					target = null;
			}
			else
			{
				// DO nothing
			}
		}
	}
}
