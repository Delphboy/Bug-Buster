package BugBuster.Screens;

import BugBuster.Player;
import BugBuster.Tower;
import BugBuster.Screens.UIComponents.HeaderBarComponent;
import BugBuster.Screens.UIComponents.TowerShopComponent;
import BugBuster.Screens.UIComponents.TowerStatsComponent;
import BugBuster.Screens.UIComponents.WorldViewComponent;
import javafx.scene.layout.Pane;

public class GameScreen extends Pane
{
	TowerShopComponent towerShop;
	TowerStatsComponent towerStats;
	HeaderBarComponent headerBar;
	WorldViewComponent worldView;

	public GameScreen()
	{
		towerShop = new TowerShopComponent();
		towerStats = new TowerStatsComponent(
				new Tower("Tower",70, 70));
		headerBar = new HeaderBarComponent(new Player());
		worldView = new WorldViewComponent();

		headerBar.setMinSize(800,50);
		headerBar.setMinSize(800,50);
		headerBar.setLayoutX(250);
		headerBar.setLayoutY(0);

		towerShop.setMinSize(250, 325);
		towerShop.setMaxSize(250, 325);
		towerShop.setLayoutX(0);
		towerShop.setLayoutY(0);

		towerStats.setMinSize(250, 325);
		towerStats.setMaxSize(250, 325);
		towerStats.setLayoutX(0);
		towerStats.setLayoutY(325);

		worldView.setMinSize(800, 600);
		worldView.setMaxSize(800, 600);
		worldView.setLayoutX(250);
		worldView.setLayoutY(50);

		getChildren().addAll(towerShop, towerStats, headerBar, worldView);
	}
}
