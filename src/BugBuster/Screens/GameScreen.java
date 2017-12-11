package BugBuster.Screens;

import BugBuster.Controller;
import BugBuster.Player;
import BugBuster.Screens.UIComponents.*;
import BugBuster.Towers.Tower;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import javax.annotation.PreDestroy;

public class GameScreen extends Pane implements ScreenIF
{
	TowerShopComponent towerShop;
	TowerStatsComponent towerStats;
	HeaderBarComponent headerBar;
	OptionsComponent optionsBar;
	WorldViewComponent worldView;

    Controller controller;

	MediaPlayer soundPlayer;

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
		Media soundFile = new Media(this.getClass().getResource("../../resources/sounds/background.mp3").toExternalForm());
		System.out.println("Sound file @" + soundFile.getSource());
		soundPlayer = new MediaPlayer(soundFile);
		soundPlayer.setOnEndOfMedia(new Runnable()
		{
			@Override
			public void run()
			{
				soundPlayer.seek(Duration.ZERO);
			}
		});
		soundPlayer.play();

		// Configure controller
		controller = new Controller(this);
		towerShop.getBuyWhiteBloodCellBtn().setOnAction(controller);
		towerShop.getBuyAntiBioticsBtn().setOnAction(controller);
		towerShop.getBuyVaccineBtn().setOnAction(controller);
		towerShop.getBuyNextVaccineBtn().setOnAction(controller);

		optionsBar.getStartRoundBtn().setOnAction(controller);

		worldView.setOnMouseClicked(controller);
	}

    public TowerShopComponent getTowerShop()
    {
        return towerShop;
    }

    public TowerStatsComponent getTowerStats()
    {
        return towerStats;
    }

    public HeaderBarComponent getHeaderBar()
    {
        return headerBar;
    }

    public OptionsComponent getOptionsBar()
    {
        return optionsBar;
    }

    public WorldViewComponent getWorldView()
    {
        return worldView;
    }

	public void setTowerStats(TowerStatsComponent towerStats)
	{
		this.towerStats = towerStats;
	}

	@Override
	public void killScreen()
	{
		soundPlayer.stop();
		soundPlayer = null;

		worldView.killTimers();

		towerShop.killComponent();
		towerStats.killComponent();
		optionsBar.killComponent();
		headerBar.killComponent();
		worldView.killComponent();

		controller = null;
	}
}
