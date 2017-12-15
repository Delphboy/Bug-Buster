package BugBuster.Screens.UIComponents;

import BugBuster.Pathogens.Pathogen;
import BugBuster.Pathogens.PathogenFactory;
import BugBuster.Player;
import BugBuster.BugBuster;
import BugBuster.Screens.WinScreen;
import BugBuster.Towers.Tower;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Random;

/**
 * A class to represent the World View
 * A class to render the world to the player
 * @author Henry Senior
 * @version 1.0.0
 */
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

	/**
	 * Every half second, tell each tower on the map to shoot their targets
	 */
	Timeline towerShootTargetTimeline = new Timeline(new KeyFrame(Duration.seconds(0.5), new EventHandler<ActionEvent>()
	{
		@Override
		public void handle(ActionEvent event) {
			for(Tower t : towers)
			{
				t.shootPathogen(pathogens);
			}
		}
	}));

	/**
	 * Spawn a new enemy once every second until no more enemies are in the spawn queue
	 */
	Timeline pathogenSpawnTimeline = new Timeline(new KeyFrame(Duration
			.seconds(1), new EventHandler<ActionEvent>()
	{
		@Override
		public void handle(ActionEvent event) {
			if(isRoundActive)
			{
				playRound();
			}
		}
	}));

	/**
	 * The AnimationTimer acts as the main game loop.
	 * Process the pathogens, moving them through the map
	 * Toggle whether or not the "start round" button is enables
	 * Update the header bar
	 * Remove any dead pathogen
	 */
	AnimationTimer gameLoopTimer = new AnimationTimer()
	{
		@Override
		public void handle(long l)
		{
			// process pathogens
			for(Pathogen p : pathogens)
			{
				drawTile(worldMap[p.getTileX()][p.getTileY()]);

				p.navigate(worldMap);
				p.attack();

				// Handle a dead pathogen
				if(p.getHealth() <= 0)
				{
					pathogensForRemoval.add(p);

					drawTile(worldMap[p.getTileX()][p.getTileY()]);

					for(int i = p.getTileX() - 2; i < p.getTileX() + 2; i++)
					{
						for(int j = p.getTileY() - 2; j < p.getTileY() + 2; j++)
						{
							if( (i > 0 && i < 16) && (j > 0 && j < 12) && worldMap[i][j].isWalkable())
								drawTile(worldMap[i][j]);
						}
					}

				}

				if(worldMap[p.getTileX()][p.getTileY()].isEndTile())
					pathogensForRemoval.add(p);
			}

			// Disable start round button if a round is being played
			OptionsComponent oc = OptionsComponent.getInstance();
			oc.getStartRoundBtn().setDisable(isRoundActive);

			// Update the header bar
			HeaderBarComponent.getInstance().update();

			// remove any dead pathogens
			if(pathogens != null)
				pathogens.removeAll(pathogensForRemoval);
		}
	};

	/**
	 * Create a new world view component based on which map is selected
	 * @param mapNum
	 */
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

	/**
	 * Return a 2D array of the tower locations
	 * @return Tower[][] towerLocations
	 */
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
				drawTile(worldMap[i][j]);
			}
		}
	}

	/**
	 * draw a given tile
	 * @param tileToDraw
	 */
	public void drawTile(Tile tileToDraw)
	{
		tileToDraw.update();
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
				worldMap[i][j] = new Tile(gc, i, j, "resources/cell-tile.png", false);
			}
		}

		//Draw Path
		worldMap[0][4] = new Tile(gc, 0, 4, "resources/path-tile.png", true, false);

		worldMap[1][4] = new Tile(gc, 1, 4, "resources/path-tile.png", true);

		worldMap[1][1] = new Tile(gc, 1, 1, "resources/path-tile.png", true);
		worldMap[1][2] = new Tile(gc, 1, 2, "resources/path-tile.png", true);
		worldMap[1][3] = new Tile(gc, 1, 3, "resources/path-tile.png", true);
		worldMap[2][1] = new Tile(gc, 2, 1, "resources/path-tile.png", true);
		worldMap[4][10] = new Tile(gc, 4, 10, "resources/path-tile.png", true);
		worldMap[6][1] = new Tile(gc, 6, 1, "resources/path-tile.png", true);
		worldMap[8][10] = new Tile(gc, 8, 10, "resources/path-tile.png", true);
		worldMap[10][1] = new Tile(gc, 10, 1, "resources/path-tile.png", true);
		worldMap[11][1] = new Tile(gc, 11, 1, "resources/path-tile.png", true);
		worldMap[11][2] = new Tile(gc, 11, 2, "resources/path-tile.png", true);
		worldMap[11][3] = new Tile(gc, 11, 3, "resources/path-tile.png", true);
		worldMap[11][4] = new Tile(gc, 11, 4, "resources/path-tile.png", true);
		worldMap[12][4] = new Tile(gc, 12, 4, "resources/path-tile.png", true);
		worldMap[13][4] = new Tile(gc, 13, 4, "resources/path-tile.png", true);
		worldMap[14][4] = new Tile(gc, 14, 4, "resources/path-tile.png", true);


		for (int i = 3; i <= 9; i += 2)
		{
			for (int j = 1; j <= 10; j++)
			{
				worldMap[i][j] = new Tile(gc, i, j, "resources/path-tile.png", true);
			}
		}


		worldMap[15][4] = new Tile(gc, 15, 4, "resources/path-tile.png", true, true);

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
				worldMap[i][j] = new Tile(gc, i, j ,"resources/cell-tile.png", false);
			}
		}

		//Draw Path
		worldMap[0][4] = new Tile(gc, 0, 4, "resources/path-tile.png", true);

		for (int j = 4; j >= 1; j--)
			worldMap[1][j] = new Tile(gc, 1, j, "resources/path-tile.png", true);

		for(int i = 2; i < 7; i++)
			worldMap[i][1] = new Tile(gc, i, 1, "resources/path-tile.png", true);

		for(int j = 2; j < 6; j++)
			worldMap[6][j] = new Tile(gc, 6, j, "resources/path-tile.png", true);

		for(int i = 6; i > 2; i--)
			worldMap[i][6] = new Tile(gc, i, 6, "resources/path-tile.png", true);

		worldMap[3][7] = new Tile(gc,3,7,"resources/path-tile.png",true);
		worldMap[3][8] = new Tile(gc,3,8,"resources/path-tile.png",true);

		for(int i = 3; i < 14; i++)
			worldMap[i][9] = new Tile(gc, i, 9, "resources/path-tile.png", true);

		for(int j = 8; j > 3; j--)
			worldMap[13][j] = new Tile(gc, 13, j, "resources/path-tile.png", true);

		worldMap[14][4] = new Tile(gc, 14, 4, "resources/path-tile.png", true);

		worldMap[15][4] = new Tile(gc, 15,4, "resources/path-tile.png", true, true);

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
				worldMap[i][j] = new Tile(gc, i, j, "resources/cell-tile.png", false);
			}
		}

		//Draw Path
		worldMap[0][4] = new Tile(gc, 0, 4, "resources/path-tile.png", true);
		worldMap[1][4] = new Tile(gc, 1, 4, "resources/path-tile.png", true);
		worldMap[2][4] = new Tile(gc, 2, 4, "resources/path-tile.png", true);
		worldMap[3][4] = new Tile(gc, 3, 4, "resources/path-tile.png", true);
		worldMap[3][5] = new Tile(gc, 3, 5, "resources/path-tile.png", true);
		worldMap[3][6] = new Tile(gc, 3, 6, "resources/path-tile.png", true);
		worldMap[2][6] = new Tile(gc, 2, 6, "resources/path-tile.png", true);
		worldMap[1][6] = new Tile(gc, 1, 6, "resources/path-tile.png", true);
		worldMap[1][7] = new Tile(gc, 1, 7, "resources/path-tile.png", true);

		for (int i = 1; i < 10; i++)
		{
			worldMap[i][8] = new Tile(gc, i,8, "resources/path-tile.png", true);
		}

		for (int j = 7; j >= 4; j--)
		{
			worldMap[9][j] = new Tile(gc, 9, j, "resources/path-tile.png", true);
		}

		for (int i = 10; i < 15; i++)
		{
			worldMap[i][4] = new Tile(gc, i, 4, "resources/path-tile.png", true);
		}

		worldMap[15][4] = new Tile(gc,15,4, "resources/path-tile.png", true, true);

		return worldMap;
	}

	@Override
	public void update()
	{

	}

	/**
	 * delete key elements from the component and stop the timers
	 */
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

	/**
	 * Based on which round is playing, load the enemies that will be played
	 */
	public void startRound()
	{
		Random rnd = new Random();
		if(roundNumber <= 10)
		{
			switch (roundNumber)
			{
				case 1:
					enemiesForRound.add(1);
					enemiesForRound.add(1);
					enemiesForRound.add(1);
					enemiesForRound.add(1);
					enemiesForRound.add(1);
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
					enemiesForRound.add(4);
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

	/**
	 * Use the Pathogen Factory to load the enemies that are being played in the round
	 */
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
	 * Stop the timers and set them to null
	 */
	public void killTimers()
	{
		towerShootTargetTimeline.stop();
		pathogenSpawnTimeline.stop();
		gameLoopTimer.stop();

		if(gameLoopTimer != null)
			gameLoopTimer.stop();
	}

}
