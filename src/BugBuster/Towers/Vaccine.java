package BugBuster.Towers;

import BugBuster.Pathogens.*;
import BugBuster.Player;
import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * A class to represent the Vaccine Tower
 * This class builds on the behaviour implemented in the abstract Tower class
 * A Vaccine specific findTarget() method has been added to ensure a vaccine only shoots Virues the
 * player has the immunisation against
 * Vaccine specific upgrade methods have also been added
 * @author Henry Senior
 * @version 1.0.0
 */
public class Vaccine extends Tower implements TowerIF
{
	/**
	 * Create a new vaccine object
	 */
	public Vaccine()
	{
		super("Vaccine", 1, 5);
		aboutMessage = "A vaccine protects people from specific\n pathogens. To protect yourself\n" +
				"against more pathogens, you will need\nmore immunisations. Make sure you\nimmunise" +
				" yourself against: \n - The Flu\n - MMR Viruses\n - Smallpox";
		cost = 50;
		defaultImage = new Image("resources/vaccine-tower.png");
		shootImage = new Image("resources/vaccine-tower-shoot.png");
		img = defaultImage;
	}

	/**
	 * Run the findTarget() method in Tower, then check the target is a virus, and that the player
	 * has immunisation against the virus. Set the target to 'null' if the play doesn't have
	 * immunisation or if the target is a bacteria
	 * @param pathogens
	 */
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
			if(playerInstance.getCurrentImmunisationLevel().equalsIgnoreCase("flu"))
			{
				if(target instanceof Flu == false)
					target = null;
			}
			else if(playerInstance.getCurrentImmunisationLevel().equalsIgnoreCase("MMR"))
			{
				if(target instanceof SmallPox)
					target = null;
			}
			else
			{
				// Do nothing as a target has been detected
			}
		}
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
		if((playerInstance.getCurrency() > (damage * 10)) && (damage < 10))
		{
			damage += 1;
			playerInstance.setCurrency(playerInstance.getCurrency() - damage * 10);
		}

		if(damage == 10)
			maxDamageAchieved = true;
	}

}
