package BugBuster.Screens;

import BugBuster.Player;
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

	public GameScreen()
	{
		towerShop = new TowerShopComponent();
		towerStats = new TowerStatsComponent(
				new Tower("Tower",70, 70));
		optionsBar = new OptionsComponent();
		headerBar = new HeaderBarComponent(Player.getInstance());
		worldView = new WorldViewComponent();

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
	}
}
