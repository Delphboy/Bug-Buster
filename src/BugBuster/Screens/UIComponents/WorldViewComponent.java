package BugBuster.Screens.UIComponents;

import BugBuster.Screens.BugBuster;
import BugBuster.Tile;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class WorldViewComponent extends Pane implements ComponentIF
{
	Canvas canvas;
	GraphicsContext gc;

	public WorldViewComponent()
	{
		canvas = new Canvas(800, 600);
		gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.BLUE);
		gc.fillRect(0,0,canvas.getWidth(), canvas.getHeight());

		getChildren().add(canvas);
		drawWorld(1);
	}

	private void drawWorld(int mapNum)
	{
		ArrayList<Tile> worldMap = null;
		switch (mapNum)
		{
			case 1:
				worldMap = loadMap1();
				break;
		}

		for(Tile t : worldMap)
		{
			gc.drawImage(t.getTileImg(), t.getPosX(), t.getPosY(), t
					.TILE_WIDTH, t.TILE_HEIGHT);
		}
	}

	private ArrayList<Tile> loadMap1()
	{
		ArrayList<Tile> worldMap = new ArrayList<>();
		for (int i = 0; i < 16; i++)
		{
			for (int j = 0; j < 12; j++)
			{
				worldMap.add(new Tile(i * Tile.TILE_WIDTH, j * Tile
						.TILE_HEIGHT, "resources/test-tile.png"));
			}
		}
		return worldMap;
	}

	@Override
	public void update()
	{

	}
}
