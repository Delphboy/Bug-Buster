package BugBuster.Screens;

import BugBuster.Screens.UIComponents.*;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * A class to represent the GameScreen
 * This class loads the different UI components and plays the game sound track
 * The class also loads the controller object, which is used to process operations between the User
 * Interface (View) and the Towers/Pathogens (Model) as part of the MVC Design Pattern
 * @author Henry Senior
 * @version 1.0.0
 */
public class GameScreen extends Pane implements ScreenIF
{
	TowerShopComponent towerShop;
	TowerStatsComponent towerStats;
	HeaderBarComponent headerBar;
	OptionsComponent optionsBar;
	WorldViewComponent worldView;

    Controller controller;

	/**
	 * Create a new gamescreen. Use the mapNum to load the correct game map
	 * @param mapNum
	 */
	public GameScreen(int mapNum)
	{
		towerShop = TowerShopComponent.getInstance();
		towerStats = TowerStatsComponent.getInstance();
		optionsBar = OptionsComponent.getInstance();
		headerBar = HeaderBarComponent.getInstance();
		worldView = new WorldViewComponent(mapNum);

		headerBar.setMinSize(800,50);
		headerBar.setMinSize(800,50);
		headerBar.setLayoutX(250);
		headerBar.setLayoutY(0);

		optionsBar.setMinSize(250, 50);
		optionsBar.setMaxSize(250, 50);
		optionsBar.setLayoutX(0);
		optionsBar.setLayoutY(0);

		towerShop.setMinSize(250, 275);
		towerShop.setMaxSize(250, 275);
		towerShop.setLayoutX(0);
		towerShop.setLayoutY(50);

		towerStats.setMinSize(250, 325);
		towerStats.setMaxSize(250, 325);
		towerStats.setLayoutX(0);
		towerStats.setLayoutY(325);

		worldView.setMinSize(800, 600);
		worldView.setMaxSize(800, 600);
		worldView.setLayoutX(250);
		worldView.setLayoutY(50);

		getChildren().addAll(towerShop, towerStats, headerBar, worldView, optionsBar);


		//Play Music
		ClassLoader classLoader = getClass().getClassLoader();
		Media soundFile = new Media(classLoader.getResource
				("resources/background.mp3").toExternalForm());

		MediaPlayer player = new MediaPlayer(soundFile);
		player.setOnEndOfMedia(new Runnable() {
			@Override
			public void run()
			{
				player.seek(Duration.ZERO);
			}
		});
		player.play();


		// Configure controller
		controller = new Controller(this);
		towerShop.getBuyWhiteBloodCellBtn().setOnAction(controller);
		towerShop.getBuyAntiBioticsBtn().setOnAction(controller);
		towerShop.getBuyVaccineBtn().setOnAction(controller);
		towerShop.getBuyNextVaccineBtn().setOnAction(controller);

		optionsBar.getStartRoundBtn().setOnAction(controller);

		worldView.setOnMouseClicked(controller);
	}

	/**
	 * Return TowerShopComponent module, used by the Controller object as part of the MVC Design
	 * Pattern
	 * @return TowerShopComponent
	 */
    public TowerShopComponent getTowerShop()
    {
        return towerShop;
    }

	/**
	 * Return TowerStatsComponent module, used by the Controller object as part of the MVC Design
	 * Pattern
	 * @return TowerStatsComponent
	 */
    public TowerStatsComponent getTowerStats()
    {
        return towerStats;
    }

	/**
	 * Return OptionsComponent module, used by the Controller object as part of the MVC Design
	 * Pattern
	 * @return OptionsComponent
	 */
    public OptionsComponent getOptionsBar()
    {
        return optionsBar;
    }

	/**
	 * Return WorldViewComponent module, used by the Controller object as part of the MVC Design
	 * Pattern
	 * @return ViewComponent
	 */
    public WorldViewComponent getWorldView()
    {
        return worldView;
    }

	/**
	 * kill the timers in world view and kill each UI Component
	 */
	@Override
	public void killScreen()
	{
		worldView.killTimers();

		towerShop.killComponent();
		towerStats.killComponent();
		optionsBar.killComponent();
		headerBar.killComponent();
		worldView.killComponent();

		controller = null;
	}
}
