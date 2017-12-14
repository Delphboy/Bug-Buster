package BugBuster.Towers;

import BugBuster.Pathogens.Bacteria;
import BugBuster.Pathogens.Pathogen;
import BugBuster.Player;
import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * A class to represent the Antibiotics Tower
 * This class builds on the behaviour implemented in the abstract Tower class
 * An Antibiotic specific findTarget() method has been added to ensure an Antibiotics tower only shoots
 * Bacteria objects
 * Antibiotics specific upgrade methods have also been added
 * @author Henry Senior
 * @version 1.0.0
 */
public class Antibiotics extends Tower implements TowerIF
{
	/**
	 * Create an antibiotics tower
	 */
	public Antibiotics()
	{
		super("Antibiotics", 1, 5);
		cost = 30;
		aboutMessage = "Antibiotics are special medicines that\nkill bacteria. There's no point " +
				"using\nthem against anything other than\nbacteria though, as it won't work!";
		defaultImage = new Image("resources/antibiotics-tower.png");
		shootImage = new Image("resources/antibiotics-tower-shoot.png");
		img = defaultImage;
	}

	/**
	 * Run the findTarget() method in Tower, then check the target is a bacteria. If the target is
	 * not an instance of Bacteria, set the target to 'null'
	 * @param pathogens
	 */
	@Override
	protected void findTarget(ArrayList<Pathogen> pathogens)
	{
		super.findTarget(pathogens);
		if(! (target instanceof Bacteria))
		{
			target = null;
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

		if(damage > 9)
			maxDamageAchieved = true;
	}

}
