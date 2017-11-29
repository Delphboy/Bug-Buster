package BugBuster.Screens.UIComponents;

import BugBuster.Screens.BugBuster;
import BugBuster.Screens.MainMenuScreen;
import BugBuster.Player;
import BugBuster.Towers.Tower;
import BugBuster.Towers.TowerFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class TowerShopComponent extends Pane implements ComponentIF
{
	Button giveTowerBtn;

	public TowerShopComponent()
	{
		final Player player = Player.getInstance();
		final TowerFactory tf = new TowerFactory();
		final Tower createdTower;
		setWidth(250);
		setHeight(50);

		// Button to test tower creation
		giveTowerBtn = new Button("Test Tower");
		giveTowerBtn.setFont(new Font("Cooper Black", 12));
		giveTowerBtn.setLayoutX(10);
		giveTowerBtn.setLayoutY(10);
		giveTowerBtn.setMinSize(85, 30);
		giveTowerBtn.setMaxSize(85, 30);
		giveTowerBtn.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				if(player.getCurrency() >= 10)
				{
					System.out.println("MAKE A TOWER");
					player.setCurrency(player.getCurrency() - 10);
					
					HeaderBarComponent.getInstance().update(); //update UI
				}
			}
		});

		getChildren().add(giveTowerBtn);
	}

	@Override
	public void update()
	{

	}
}
