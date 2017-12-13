package BugBuster.Screens.UIComponents;

import BugBuster.Pathogens.Pathogen;
import BugBuster.Pathogens.PathogenFactory;
import BugBuster.Player;
import BugBuster.BugBuster;
import BugBuster.Screens.WinScreen;
import BugBuster.Tile;
import BugBuster.Towers.Tower;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class WorldViewComponent extends Pane implements ComponentIF
{
	Canvas canvas;
	GraphicsContext gc;
	Tile[][] worldMap = new Tile[16][12];
	Tower[][] towerLocations = new Tower[16][12];
	private ArrayList<Pathogen> pathogens;
	private ArrayList<Pathogen> pathogensForRemoval = new ArrayList<>();
	private ArrayList<Tower> towers;

	private int roundNumber = 1;
	private boolean isRoundActive = false;
	private ArrayList<Integer> enemiesForRound = new ArrayList<>();

	Timeline towerShootTargetTimeline = new Timeline(new KeyFrame(Duration.seconds(0.5), new EventHandler<ActionEvent>()
	{
		@Override
		public void handle(ActionEvent event) {
			for(Tower t : towers)
			{
				t.shootPathogen(pathogens);
				Pathogen target = t.getTarget();

				if(target != null)
				{
					Rectangle laser = new Rectangle(t.getTileLocX() * Tile.TILE_WIDTH, t.getTileLocY() * Tile.TILE_HEIGHT, 10, 10);
					laser.setFill(Color.RED);

					TranslateTransition laserTransition = new TranslateTransition(Duration.millis(100));
					laserTransition.setCycleCount(1);
					laserTransition.setNode(laser);

					laserTransition.setFromX(t.getTileLocX() * Tile.TILE_WIDTH);
					laserTransition.setToX(target.getTileX());
					laserTransition.setFromX(t.getTileLocY() * Tile.TILE_HEIGHT);
					laserTransition.setToX(target.getTileY());
					getChildren().add(laser);
					laserTransition.play();
//
//					Line laser = new Line(t.getTileLocX() * Tile.TILE_WIDTH, t.getTileLocY() * Tile.TILE_HEIGHT, target.getTileX() * Tile.TILE_WIDTH, target.getTileY() * Tile.TILE_HEIGHT);
//					getChildren().add(laser);
				}
			}
		}
	}));

	Timeline pathogenSpawnTimeline = new Timeline(new KeyFrame(Duration.seconds(0.75), new EventHandler<ActionEvent>()
	{
		@Override
		public void handle(ActionEvent event) {
			if(isRoundActive)
			{
				playRound();
			}
		}
	}));

	AnimationTimer gameLoopTimer = new AnimationTimer()
	{
		@Override
		public void handle(long l)
		{
			// process pathogens
			for(Pathogen p : pathogens)
			{
				drawTile(worldMap[p.getTileX()][p.getTileY()].getTileImg(), p
						.getTileX() * Tile.TILE_WIDTH, p.getTileY() * Tile.TILE_HEIGHT);

				p.navigate(worldMap);
				if(p.getHealth() <= 0)
				{
					pathogensForRemoval.add(p);
					drawTile(worldMap[p.getTileX()][p.getTileY()].getTileImg(), p
							.getTileX() * Tile.TILE_WIDTH, p.getTileY() * Tile.TILE_HEIGHT);

					switch (p.getLastDir())
					{
						case LEFT:
							drawTile(worldMap[p.getTileX() - 1][p.getTileY()].getTileImg(), (p
									.getTileX() - 1) * Tile.TILE_WIDTH, p.getTileY() * Tile.TILE_HEIGHT);
							break;
						case RIGHT:
							drawTile(worldMap[p.getTileX() + 1][p.getTileY()].getTileImg(), (p
									.getTileX() + 1) * Tile.TILE_WIDTH, p.getTileY() * Tile.TILE_HEIGHT);
							break;
						case UP:
							drawTile(worldMap[p.getTileX()][p.getTileY() - 1].getTileImg(), p
									.getTileX() * Tile.TILE_WIDTH, (p.getTileY() - 1)* Tile.TILE_HEIGHT);
							break;
						case DOWN:
							drawTile(worldMap[p.getTileX()][p.getTileY() + 1].getTileImg(), p
									.getTileX() * Tile.TILE_WIDTH, (p.getTileY() + 1)* Tile.TILE_HEIGHT);
							break;
					}
				}

				if(worldMap[p.getTileX()][p.getTileY()].isEndTile())
					pathogensForRemoval.add(p);

				p.attack();
			}

			// Disable start round button if a round is being played
			OptionsComponent oc = OptionsComponent.getInstance();
			oc.getStartRoundBtn().setDisable(isRoundActive);

			HeaderBarComponent.getInstance().update();

			if(pathogens != null)
				pathogens.removeAll(pathogensForRemoval);
		}
	};

	public WorldViewComponent(int mapNum)
	{
		canvas = new Canvas(800, 600);
		gc = canvas.getGraphicsContext2D();

		pathogens = new ArrayList<>();
		towers = new ArrayList<>();

		getChildren().add(canvas);
		drawWorld(mapNum);

		gameLoopTimer.start();
		towerShootTargetTimeline.setCycleCount(Timeline.INDEFINITE);
		towerShootTargetTimeline.play();

		pathogenSpawnTimeline.setCycleCount(Timeline.INDEFINITE);
		pathogenSpawnTimeline.play();
	}

	public Tower[][] getTowerLocations()
	{
		return towerLocations;
	}

	/**
	 * Draw the world based on what map is loaded
	 * @param mapNum
	 */
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
				drawTile(worldMap[i][j].getTileImg(), worldMap[i][j].getPosX(),
						worldMap[i][j].getPosY());
			}
		}
	}

	/**
	 * Draw a tile at a specific x, y
	 * @param img
	 * @param xPos : Actual X, not tileX
	 * @param yPos : Actual Y, not tileY
	 */
	public void drawTile(Image img, int xPos, int yPos)
	{
		gc.drawImage(img, xPos, yPos, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
//		System.out.println("Tile drawn at: " + xPos + ":" + yPos +
//		"\t" + ((int)(xPos / Tile.TILE_WIDTH)) + ":" + ((int)(yPos / Tile.TILE_HEIGHT)));
	}

	/**
	 * Load map 1
	 * @return a 2D Tile array representing the world
	 */
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
				.TILE_HEIGHT, "resources/path-tile.png", true, true, false);

		worldMap[1][4] = new Tile(1 * Tile.TILE_WIDTH, 4 * Tile.TILE_HEIGHT, "resources/path-tile.png", true);

		worldMap[1][1] = new Tile(1 * Tile.TILE_WIDTH, 1 * Tile.TILE_HEIGHT, "resources/path-tile.png", true);
		worldMap[1][2] = new Tile(1 * Tile.TILE_WIDTH, 2 * Tile.TILE_HEIGHT, "resources/path-tile.png", true);
		worldMap[1][3] = new Tile(1 * Tile.TILE_WIDTH, 3 * Tile.TILE_HEIGHT, "resources/path-tile.png", true);
		worldMap[2][1] = new Tile(2 * Tile.TILE_WIDTH, 1 * Tile.TILE_HEIGHT, "resources/path-tile.png", true);
		worldMap[4][10] = new Tile(4 * Tile.TILE_WIDTH, 10 * Tile.TILE_HEIGHT, "resources/path-tile.png", true);
		worldMap[6][1] = new Tile(6 * Tile.TILE_WIDTH, 1 * Tile.TILE_HEIGHT, "resources/path-tile.png", true);
		worldMap[8][10] = new Tile(8 * Tile.TILE_WIDTH, 10 * Tile.TILE_HEIGHT, "resources/path-tile.png", true);
		worldMap[10][1] = new Tile(10 * Tile.TILE_WIDTH, 1 * Tile.TILE_HEIGHT, "resources/path-tile.png", true);
		worldMap[11][1] = new Tile(11 * Tile.TILE_WIDTH, 1 * Tile.TILE_HEIGHT, "resources/path-tile.png", true);
		worldMap[11][2] = new Tile(11 * Tile.TILE_WIDTH, 2 * Tile.TILE_HEIGHT, "resources/path-tile.png", true);
		worldMap[11][3] = new Tile(11 * Tile.TILE_WIDTH, 3 * Tile.TILE_HEIGHT, "resources/path-tile.png", true);
		worldMap[11][4] = new Tile(11 * Tile.TILE_WIDTH, 4 * Tile.TILE_HEIGHT, "resources/path-tile.png", true);
		worldMap[12][4] = new Tile(12 * Tile.TILE_WIDTH, 4 * Tile.TILE_HEIGHT, "resources/path-tile.png", true);
		worldMap[13][4] = new Tile(13 * Tile.TILE_WIDTH, 4 * Tile.TILE_HEIGHT, "resources/path-tile.png", true);
		worldMap[14][4] = new Tile(14 * Tile.TILE_WIDTH, 4 * Tile.TILE_HEIGHT, "resources/path-tile.png", true);


		for (int i = 3; i <= 9; i += 2)
		{
			for (int j = 1; j <= 10; j++)
			{
				worldMap[i][j] = new Tile(i * Tile.TILE_WIDTH, j * Tile.TILE_HEIGHT, "resources/path-tile.png", true);
			}
		}


		worldMap[15][4] = new Tile(15 * Tile.TILE_WIDTH, 4 * Tile
				.TILE_HEIGHT, "resources/path-tile.png", true, false, true);

		return worldMap;
	}

	/**
	 * Load map 2
	 * @return a 2D Tile array representing the world
	 */
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

		//Draw Path
		worldMap[0][4] = new Tile(0 * Tile.TILE_WIDTH, 4 * Tile
				.TILE_HEIGHT, "resources/path-tile.png", true, true, false);

		for (int j = 4; j >= 1; j--)
			worldMap[1][j] = new Tile(1 * Tile.TILE_WIDTH, j * Tile
					.TILE_HEIGHT, "resources/path-tile.png", true);

		for(int i = 2; i < 7; i++)
			worldMap[i][1] = new Tile(i * Tile.TILE_WIDTH, 1 * Tile
					.TILE_HEIGHT, "resources/path-tile.png", true);

		for(int j = 2; j < 6; j++)
			worldMap[6][j] = new Tile(6 * Tile.TILE_WIDTH, j * Tile
					.TILE_HEIGHT, "resources/path-tile.png", true);

		for(int i = 6; i > 2; i--)
			worldMap[i][6] = new Tile(i * Tile.TILE_WIDTH, 6 * Tile
					.TILE_HEIGHT, "resources/path-tile.png", true);

		worldMap[3][7] = new Tile(3 * Tile.TILE_WIDTH, 7 * Tile
				.TILE_HEIGHT, "resources/path-tile.png", true);
		worldMap[3][8] = new Tile(3 * Tile.TILE_WIDTH, 8 * Tile
				.TILE_HEIGHT, "resources/path-tile.png", true);

		for(int i = 3; i < 14; i++)
			worldMap[i][9] = new Tile(i * Tile.TILE_WIDTH, 9 * Tile
					.TILE_HEIGHT, "resources/path-tile.png", true);

		for(int j = 8; j > 3; j--)
			worldMap[13][j] = new Tile(13 * Tile.TILE_WIDTH, j * Tile
					.TILE_HEIGHT, "resources/path-tile.png", true);

		worldMap[14][4] = new Tile(14 * Tile.TILE_WIDTH, 4 * Tile
				.TILE_HEIGHT, "resources/path-tile.png", true);

		worldMap[15][4] = new Tile(15 * Tile.TILE_WIDTH, 4 * Tile
				.TILE_HEIGHT, "resources/path-tile.png", true, false, true);

		return worldMap;
	}

	/**
	 * Load map 3
	 * @return a 2D Tile array representing the world
	 */
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

		//Draw Path
		worldMap[0][4] = new Tile(0 * Tile.TILE_WIDTH, 4 * Tile
				.TILE_HEIGHT, "resources/path-tile.png", true, true, false);
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
				.TILE_HEIGHT, "resources/path-tile.png", true, false, true);

		return worldMap;
	}

	@Override
	public void update()
	{
		debugTowers();
	}

	@Override
	public void killComponent()
	{
		canvas = null;
		gc = null;
		worldMap = null;
		towerLocations = null;

		pathogens = null;
		pathogensForRemoval = null;
		towers = null;

		enemiesForRound = null;
//		factoryTimer = null;
//		factoryTimerTask = null;
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
			tower.setGraphicsContext(gc);
			towers.add(tower);
			gc.drawImage(tower.getImage(), x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
			return true;
		}
		return false;
	}

	public void startRound()
	{
		Random rnd = new Random();
		if(roundNumber <= 10)
		{
			switch (roundNumber)
			{
				case 1:
					enemiesForRound.add(4);
					enemiesForRound.add(3);
					enemiesForRound.add(2);
					enemiesForRound.add(3);
					enemiesForRound.add(4);
					break;
				case 2:
					enemiesForRound.add(1);
					enemiesForRound.add(1);
					enemiesForRound.add(1);
					enemiesForRound.add(1);
					enemiesForRound.add(1);
					enemiesForRound.add(2);
					enemiesForRound.add(2);
					enemiesForRound.add(2);
					break;
				case 3:
					enemiesForRound.add(1);
					enemiesForRound.add(2);
					enemiesForRound.add(1);
					enemiesForRound.add(2);
					enemiesForRound.add(1);
					enemiesForRound.add(2);
					enemiesForRound.add(3);
					enemiesForRound.add(3);
					break;
				case 4:
					enemiesForRound.add(1);
					enemiesForRound.add(2);
					enemiesForRound.add(1);
					enemiesForRound.add(2);
					enemiesForRound.add(3);
					enemiesForRound.add(3);
					enemiesForRound.add(3);
					enemiesForRound.add(3);
					break;
				case 5:
					enemiesForRound.add(1);
					enemiesForRound.add(2);
					enemiesForRound.add(1);
					enemiesForRound.add(3);
					enemiesForRound.add(1);
					enemiesForRound.add(3);
					for (int i = 0; i < 5; i++)
						enemiesForRound.add(rnd.nextInt(4 - 1) + 1);

					break;
				case 6:
					enemiesForRound.add(1);
					enemiesForRound.add(2);
					enemiesForRound.add(1);
					enemiesForRound.add(2);
					enemiesForRound.add(1);
					enemiesForRound.add(3);
					for (int i = 0; i < 7; i++)
						enemiesForRound.add(rnd.nextInt(4 - 1) + 1);
					break;
				case 7:
					enemiesForRound.add(1);
					enemiesForRound.add(2);
					enemiesForRound.add(1);
					enemiesForRound.add(2);
					enemiesForRound.add(1);
					enemiesForRound.add(3);
					for (int i = 0; i < 9; i++)
						enemiesForRound.add(rnd.nextInt(4 - 1) + 1);
					break;
				case 8:
					enemiesForRound.add(1);
					enemiesForRound.add(2);
					enemiesForRound.add(1);
					enemiesForRound.add(2);
					enemiesForRound.add(1);
					enemiesForRound.add(3);
					for (int i = 0; i < 11; i++)
						enemiesForRound.add(rnd.nextInt(4 - 1) + 1);
					break;
				case 9:
					enemiesForRound.add(1);
					enemiesForRound.add(2);
					enemiesForRound.add(1);
					enemiesForRound.add(2);
					enemiesForRound.add(1);
					enemiesForRound.add(3);
					for (int i = 0; i < 15; i++)
						enemiesForRound.add(rnd.nextInt(4 - 1) + 1);
					break;
				case 10:
					enemiesForRound.add(1);
					enemiesForRound.add(2);
					enemiesForRound.add(1);
					enemiesForRound.add(2);
					enemiesForRound.add(1);
					enemiesForRound.add(3);
					for (int i = 0; i < 15; i++)
						enemiesForRound.add(rnd.nextInt(4 - 1) + 1);
					enemiesForRound.add(4);
					enemiesForRound.add(4);
					enemiesForRound.add(4);
					enemiesForRound.add(4);
					enemiesForRound.add(4);
					enemiesForRound.add(4);
					enemiesForRound.add(4);
					enemiesForRound.add(4);
					enemiesForRound.add(4);
					enemiesForRound.add(4);
					enemiesForRound.add(4);
					enemiesForRound.add(4);
					enemiesForRound.add(4);
					enemiesForRound.add(4);
					enemiesForRound.add(4);
					enemiesForRound.add(4);
					break;
				default:
					System.out.println("UNKNOWN ROUND");
					break;
			}
		}
		else
			BugBuster.updateScene(new WinScreen());

		isRoundActive = true;
	}

	private void playRound()
	{
		//ie) round has just finished
		if((enemiesForRound.size() == 0 && pathogens.size() == 0) && (isRoundActive))
		{
			isRoundActive = false;
			Player player = Player.getInstance();
			player.setCurrency(player.getCurrency() + 100);
			player.setWavesComplete(roundNumber);
			roundNumber += 1;
		}
		// Round still active
		else
		{
			PathogenFactory pf = new PathogenFactory(gc);
			if(enemiesForRound.size() > 0)
			{
				pathogens.add(pf.createProduct(enemiesForRound.get(enemiesForRound.size() - 1)));
				enemiesForRound.remove(enemiesForRound.size() - 1);
			}
		}
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

	public void killTimers()
	{
		towerShootTargetTimeline.stop();
		pathogenSpawnTimeline.stop();

		if(gameLoopTimer != null)
			gameLoopTimer.stop();
	}

}
