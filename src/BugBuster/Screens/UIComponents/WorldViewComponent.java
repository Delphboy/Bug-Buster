package BugBuster.Screens.UIComponents;

import BugBuster.Screens.BugBuster;
import BugBuster.Tile;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class WorldViewComponent extends Pane implements ComponentIF
{
	Canvas canvas;
	GraphicsContext gc;

	public WorldViewComponent(int mapNum)
	{
		canvas = new Canvas(800, 600);
		gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.BLUE);
		gc.fillRect(0,0,canvas.getWidth(), canvas.getHeight());

		getChildren().add(canvas);
		drawWorld(mapNum);
	}

	private void drawWorld(int mapNum)
	{
		Tile[][] worldMap = null;
		switch (mapNum)
		{
			case 1:
				worldMap = loadMap1();
				break;
			case 2:
				worldMap = loadMap2();
				break;
			case 3:
				worldMap = loadMap3();
				break;
			default:
				worldMap = loadMap1();
				break;
		}

		for (int i = 0; i < 16; i++)
		{
			for (int j = 0; j < 12; j++)
			{
				gc.drawImage(worldMap[i][j].getTileImg(), worldMap[i][j].getPosX(),
						worldMap[i][j].getPosY(), worldMap[i][j].TILE_WIDTH,
						worldMap[i][j].TILE_HEIGHT);
			}
		}
	}

	private Tile[][] loadMap1()
	{
		Tile[][] worldMap = new Tile[16][12];
		for (int i = 0; i < 16; i++)
		{
			for (int j = 0; j < 12; j++)
			{
				worldMap[i][j] = new Tile(i * Tile.TILE_WIDTH, j * Tile
						.TILE_HEIGHT, "resources/cell-tile.png", false);
			}
		}

		for (int i = 0; i < 16; i++)
		{
			worldMap[i][4] = new Tile(i * Tile.TILE_WIDTH, 4 * Tile
					.TILE_HEIGHT, "resources/path-tile.png", false);
		}

		return worldMap;
	}

	private Tile[][] loadMap2()
	{
		Tile[][] worldMap = new Tile[16][12];
		for (int i = 0; i < 16; i++)
		{
			for (int j = 0; j < 12; j++)
			{
				worldMap[i][j] = new Tile(i * Tile.TILE_WIDTH, j * Tile
						.TILE_HEIGHT, "resources/cell-tile.png", false);
			}
		}

		for (int i = 0; i < 16; i++)
		{
			worldMap[i][8] = new Tile(i * Tile.TILE_WIDTH, 8 * Tile
					.TILE_HEIGHT, "resources/path-tile.png", false);
		}

		return worldMap;
	}

	private Tile[][] loadMap3()
	{
		Tile[][] worldMap = new Tile[16][12];
		for (int i = 0; i < 16; i++)
		{
			for (int j = 0; j < 12; j++)
			{
				worldMap[i][j] = new Tile(i * Tile.TILE_WIDTH, j * Tile
						.TILE_HEIGHT, "resources/cell-tile.png", false);
			}
		}

		for (int i = 0; i < 16; i++)
		{
			worldMap[i][2] = new Tile(i * Tile.TILE_WIDTH, 2 * Tile
					.TILE_HEIGHT, "resources/path-tile.png", false);
		}

		return worldMap;
	}

	@Override
	public void update()
	{

	}
}
