package BugBuster.Towers;

import BugBuster.Pathogens.Bacteria;
import BugBuster.Pathogens.Pathogen;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Vaccine extends Tower implements TowerIF
{
	public Vaccine()
	{
		super("Vaccine", 1, 5);
		aboutMessage = "I AM A VACCINE";
		cost = 35;
		img = new Image("resources/vaccine-tower.png");
	}

	@Override
	public void findTarget(ArrayList<Pathogen> pathogens)
	{
		super.findTarget(pathogens);
		if(target instanceof Bacteria)
		{
			target = null;
		}
	}
}
