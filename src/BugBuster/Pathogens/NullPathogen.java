package BugBuster.Pathogens;

import BugBuster.BugBuster;
import BugBuster.Player;
import BugBuster.Screens.TutorialScreen;
import BugBuster.Screens.UIComponents.HeaderBarComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class NullPathogen extends Pathogen
{
	public NullPathogen(GraphicsContext gc, double x, double y)
	{
		super(gc, x, y);
		health = 0;
		damange = 0;
		img = new Image("resources/path-tile.png");
		setTileLocation(lastDir);

		update();
	}

	@Override
	public void attack()
	{
		if(isOnTile(endTileX, endTileY))
		{
			isForRemoval = true;
			Player player = Player.getInstance();
			if(player.getHealth() < 1)
				BugBuster.updateScene(new TutorialScreen());
		}
	}

	@Override
	protected void move()
	{
		super.move();
	}
}
