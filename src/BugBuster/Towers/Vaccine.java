package BugBuster.Towers;

import javafx.scene.image.Image;

public class Vaccine extends Tower implements TowerIF
{
	public Vaccine()
	{
		super("Vaccine", 5, 5);
		cost = 35;
		img = new Image("resources/test-tile.png");
	}
}
