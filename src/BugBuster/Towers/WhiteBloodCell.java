package BugBuster.Towers;

		import javafx.scene.image.Image;

public class WhiteBloodCell extends Tower implements TowerIF
{
	public WhiteBloodCell()
	{
		super("White Blood Cell", 1, 1);
		img = new Image("resources/white-blood-cell.png");
		cost = 40;
		aboutMessage = "Whiteblood cells are the bodies defence\nmechanism. They protect against " +
				"\nanything that might try to attack us.\nWhilst they're really good on their\nown, " +
				"they sometimes need help from\nother things, such as anitbiotics or\nvaccines.";
	}
}
