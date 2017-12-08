package BugBuster.Screens;

import BugBuster.Controller;
import BugBuster.Screens.UIComponents.*;
import BugBuster.Towers.Tower;
import javafx.scene.layout.Pane;

public class GameScreen extends Pane
{
	TowerShopComponent towerShop;
	TowerStatsComponent towerStats;
	HeaderBarComponent headerBar;
	OptionsComponent optionsBar;
	WorldViewComponent worldView;

    Controller controller;

	public GameScreen(int mapNum)
	{
		towerShop = new TowerShopComponent();
//		towerStats = new TowerStatsComponent(
//				new Tower("Tower",70, 70));
		optionsBar = new OptionsComponent();
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

//		towerStats.setMinSize(250, 325);
//		towerStats.setMaxSize(250, 325);
//		towerStats.setLayoutX(0);
//		towerStats.setLayoutY(325);

		worldView.setMinSize(800, 600);
		worldView.setMaxSize(800, 600);
		worldView.setLayoutX(250);
		worldView.setLayoutY(50);

		getChildren().addAll(towerShop/*, towerStats*/, headerBar, worldView, optionsBar);

		controller = new Controller(this);
		towerShop.getBuyWhiteBloodCellBtn().setOnAction(controller);
		towerShop.getBuyAntiBioticsBtn().setOnAction(controller);
		towerShop.getBuyVaccineBtn().setOnAction(controller);

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
}
