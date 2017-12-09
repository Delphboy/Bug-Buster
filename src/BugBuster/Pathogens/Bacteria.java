package BugBuster.Pathogens;

import BugBuster.GameObject;
import BugBuster.Player;
import BugBuster.Screens.BugBuster;
import BugBuster.Screens.TutorialScreen;
import BugBuster.Screens.UIComponents.HeaderBarComponent;
import BugBuster.Tile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Path;

import java.util.ArrayList;

public class Bacteria extends Pathogen
{
	public Bacteria(GraphicsContext gc, double x, double y)
	{
		super(gc, x, y);
		health = 5;
		damange = 1;
		img = new Image("resources/test-tile.png");
		setTileLocation(lastDir);

		update();
	}
}
