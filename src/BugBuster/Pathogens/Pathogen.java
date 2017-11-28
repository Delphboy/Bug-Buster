package BugBuster.Pathogens;

import BugBuster.GameObject;
import javafx.scene.canvas.GraphicsContext;

public class Pathogen extends GameObject
{
	private int health;

	public Pathogen(GraphicsContext gc, double x, double y)
	{
		super(gc, x, y);
		health = 10;
	}
}
