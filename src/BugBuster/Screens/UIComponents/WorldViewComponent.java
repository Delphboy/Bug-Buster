package BugBuster.Screens.UIComponents;

import BugBuster.Pathogens.Pathogen;
import BugBuster.Pathogens.PathogenFactory;
import BugBuster.Player;
import BugBuster.Tile;
import BugBuster.Towers.Tower;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class WorldViewComponent extends Pane implements ComponentIF
{
	Canvas canvas;
	GraphicsContext gc;
	Tile[][] worldMap = new Tile[16][12];
	Tower[][] towerLocations = new Tower[16][12];

	private ArrayList<Pathogen> pathogens;

	AnimationTimer timer = new AnimationTimer()
	{
		@Override
		public void handle(long l)
		{
			// animate pathogens
//			for(Pathogen p : pathogens)
//			{
//				p.navigate(worldMap);
//			}

		}
	};

	public WorldViewComponent(int mapNum)
	{
		canvas = new Canvas(800, 600);
		gc = canvas.getGraphicsContext2D();

		pathogens = new ArrayList<>();

		getChildren().add(canvas);
		drawWorld(mapNum);
	}

	public Tower[][] getTowerLocations()
	{
		return towerLocations;
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
		for (int i = 0; i < 16; i++)
		{
			for (int j = 0; j < 12; j++)
			{
				worldMap[i][j] = new Tile(i * Tile.TILE_WIDTH, j * Tile
						.TILE_HEIGHT, "resources/cell-tile.png", false);
			}
		}

		//Draw Path
		worldMap[0][4] = new Tile(0 * Tile.TILE_WIDTH, 4 * Tile
				.TILE_HEIGHT, "resources/path-tile.png", true);
		worldMap[1][4] = new Tile(1 * Tile.TILE_WIDTH, 4 * Tile
				.TILE_HEIGHT, "resources/path-tile.png", true);
		worldMap[2][4] = new Tile(2 * Tile.TILE_WIDTH, 4 * Tile
				.TILE_HEIGHT, "resources/path-tile.png", true);
		worldMap[3][4] = new Tile(3 * Tile.TILE_WIDTH, 4 * Tile
				.TILE_HEIGHT, "resources/path-tile.png", true);
		worldMap[3][5] = new Tile(3 * Tile.TILE_WIDTH, 5 * Tile
				.TILE_HEIGHT, "resources/path-tile.png", true);
		worldMap[3][6] = new Tile(3 * Tile.TILE_WIDTH, 6 * Tile
				.TILE_HEIGHT, "resources/path-tile.png", true);
		worldMap[2][6] = new Tile(2 * Tile.TILE_WIDTH, 6 * Tile
				.TILE_HEIGHT, "resources/path-tile.png", true);
		worldMap[1][6] = new Tile(1 * Tile.TILE_WIDTH, 6 * Tile
				.TILE_HEIGHT, "resources/path-tile.png", true);
		worldMap[1][7] = new Tile(1 * Tile.TILE_WIDTH, 7 * Tile
				.TILE_HEIGHT, "resources/path-tile.png", true);

		for (int i = 1; i < 10; i++)
		{
			worldMap[i][8] = new Tile(i * Tile.TILE_WIDTH, 8 * Tile
					.TILE_HEIGHT, "resources/path-tile.png", true);
		}

		for (int j = 7; j >= 4; j--)
		{
			worldMap[9][j] = new Tile(9 * Tile.TILE_WIDTH, j * Tile
					.TILE_HEIGHT, "resources/path-tile.png", true);
		}

		for (int i = 10; i < 15; i++)
		{
			worldMap[i][4] = new Tile(i * Tile.TILE_WIDTH, 4 * Tile
					.TILE_HEIGHT, "resources/path-tile.png", true);
		}

		worldMap[15][4] = new Tile(15 * Tile.TILE_WIDTH, 4 * Tile
				.TILE_HEIGHT, "resources/path-tile.png", true, true);

		return worldMap;
	}

	private Tile[][] loadMap2()
	{
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
		debugTowers();
	}

	/**
	 * Return true if tower is placed, false if tower can't be placed
	 * @param x
	 * @param y
	 * @param tower
	 * @return
	 */
	public boolean placeTower(int x, int y, Tower tower)
	{
		if(towerLocations[x][y] == null && worldMap[x][y].isWalkable() == false)
		{
			tower.setTileLocX(x);
			tower.setTileLocY(y);
			Player.getInstance().setCurrency(Player.getInstance().getCurrency() - tower.getCost());
			towerLocations[x][y] = tower;
			gc.drawImage(tower.getImage(), x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
			return true;
		}
		return false;
	}

	public void startRound(int roundNumber)
	{
		PathogenFactory pf = new PathogenFactory(gc);
		for (int i = 0; i < roundNumber; i++)
		{
			pathogens.add(pf.createProduct(1));
		}
		pathogens.get(0).navigate(worldMap);
		timer.start();
	}

	/**
	 * Print locations of towers
	 */
	private void debugTowers()
	{
		for (int i = 0; i < 16; i++)
		{
			for (int j = 0; j < 12; j++)
			{
				if(towerLocations[i][j] != null)
					System.out.println(towerLocations[i][j]);
			}
		}
	}

}
