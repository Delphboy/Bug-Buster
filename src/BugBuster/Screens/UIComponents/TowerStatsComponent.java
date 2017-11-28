package BugBuster.Screens.UIComponents;

import BugBuster.Towers.Tower;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;


public class TowerStatsComponent extends Pane implements ComponentIF
{
	Label titleLabel;
	Label rangeLabel;
	Label damageLabel;

	Button upgradeRangeButton;
	Button upgradeDamageButton;
	Button sellTowerButton;

	public TowerStatsComponent(Tower tower)
	{
		setWidth(250);
		setHeight(300);

		// Set the title of the Tower Box
		titleLabel = new Label(tower.getType());
		titleLabel.setFont(new Font("Cooper Black", 36));
		titleLabel.setLayoutX(10);
		titleLabel.setLayoutY(10);

		// Output the tower's damage
		damageLabel = new Label("Damage: " + tower.getDamage());
		damageLabel.setFont(new Font("Cooper Black", 22));
		damageLabel.setLayoutX(10);
		damageLabel.setLayoutY(50);

		// Output the tower's Range
		rangeLabel = new Label("Range: " + tower.getRadius());
		rangeLabel.setFont(new Font("Cooper Black", 22));
		rangeLabel.setLayoutX(10);
		rangeLabel.setLayoutY(100);

		// Set the increase damage button
		upgradeDamageButton = new Button("+1 Damage");
		upgradeDamageButton.setFont(new Font("Cooper Black", 12));
		upgradeDamageButton.setLayoutX(160);
		upgradeDamageButton.setLayoutY(50);
		upgradeDamageButton.setMinSize(85, 30);
		upgradeDamageButton.setMaxSize(85, 30);
		upgradeDamageButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				System.out.println("Upgrade tower damage");
			}
		});

		// Set the increase damage button
		upgradeRangeButton = new Button("+1 Range");
		upgradeRangeButton.setFont(new Font("Cooper Black", 12));
		upgradeRangeButton.setLayoutX(160);
		upgradeRangeButton.setLayoutY(100);
		upgradeRangeButton.setMinSize(85, 30);
		upgradeRangeButton.setMaxSize(85, 30);
		upgradeRangeButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				System.out.println("Upgrade tower range");
			}
		});

		getChildren().addAll(titleLabel, rangeLabel, damageLabel, upgradeDamageButton,
				upgradeRangeButton);
	}

	@Override
	public void update()
	{

	}
}
