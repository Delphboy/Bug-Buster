package BugBuster.Towers;

import javafx.scene.image.Image;

public class WhiteBloodCell extends Tower implements TowerIF
{
	public WhiteBloodCell()
	{
		super("White Blood Cell", 1, 1);
		img = new Image("resources/white-blood-cell.png");
		cost = 40;
	}
}
