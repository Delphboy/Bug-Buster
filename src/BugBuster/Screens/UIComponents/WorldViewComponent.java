package BugBuster.Screens.UIComponents;

import BugBuster.Screens.BugBuster;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class WorldViewComponent extends Pane implements ComponentIF
{
	public WorldViewComponent()
	{
		Canvas canvas = new Canvas(800, 600);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.BLUE);
		gc.fillRect(0,0,canvas.getWidth(), canvas.getHeight());

		getChildren().add(canvas);
	}

	@Override
	public void update()
	{

	}
}
