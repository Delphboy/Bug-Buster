package BugBuster.Screens.UIComponents;

import BugBuster.Player;
import BugBuster.Towers.Tower;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;


public class TowerStatsComponent extends Pane implements ComponentIF
{
	private static TowerStatsComponent instance = null;
	private Tower towerToDisplay;

	Label titleLabel;
	Label rangeLabel;
	Label damageLabel;
	Label aboutTowerLabel;

	Button upgradeRangeButton;
	Button upgradeDamageButton;

	public static TowerStatsComponent getInstance()
	{
		if (instance == null)
		{
			instance = new TowerStatsComponent();
		}

		return instance;
	}

	private TowerStatsComponent()
	{
		setWidth(250);
		setHeight(300);

		// Set the title of the Tower Box
		titleLabel = new Label("");
		titleLabel.setFont(new Font("Cooper Black", 24));
		titleLabel.setLayoutX(10);
		titleLabel.setLayoutY(10);

		// Output the tower's damage
		damageLabel = new Label("Damage: ");
		damageLabel.setFont(new Font("Cooper Black", 22));
		damageLabel.setLayoutX(10);
		damageLabel.setLayoutY(50);

		// Output the tower's Range
		rangeLabel = new Label("Range: ");
		rangeLabel.setFont(new Font("Cooper Black", 22));
		rangeLabel.setLayoutX(10);
		rangeLabel.setLayoutY(100);

		// Output details about the science behind the tower
		aboutTowerLabel = new Label("");
		aboutTowerLabel.setFont(new Font("Cooper Black", 12));
		aboutTowerLabel.setLayoutX(10);
		aboutTowerLabel.setLayoutY(140);

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
				towerToDisplay.increaseDamage();
				update();
				HeaderBarComponent.getInstance().update();
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
				towerToDisplay.increaseRadius();
				update();
				HeaderBarComponent.getInstance().update();
			}
		});

		update();

		getChildren().addAll(titleLabel, rangeLabel, damageLabel,
				aboutTowerLabel, upgradeDamageButton, upgradeRangeButton);
	}

	/**
	 * set the tower field and then update the UI
	 * @param givenTower
	 */
	public void setTowerToDisplay(Tower givenTower)
	{
		this.towerToDisplay = givenTower;
		update();
	}

	@Override
	public void update()
	{
		if(towerToDisplay != null)
		{
			titleLabel.setText(towerToDisplay.getType());
			aboutTowerLabel.setText(towerToDisplay.getAboutMessage());

			rangeLabel.setText("Range: " + towerToDisplay.getRadius());
			damageLabel.setText("Damage: " + towerToDisplay.getDamage());

			rangeLabel.setDisable(false);
			damageLabel.setDisable(false);
			upgradeDamageButton.setDisable(false);
			upgradeRangeButton.setDisable(false);
		}
		else
		{
			titleLabel.setText("Select a Tower");
			aboutTowerLabel.setText("Select a tower to discover how it works");
			rangeLabel.setText("Range: ");
			damageLabel.setText("Damage: ");

			rangeLabel.setDisable(true);
			damageLabel.setDisable(true);
			upgradeDamageButton.setDisable(true);
			upgradeRangeButton.setDisable(true);
		}
	}

	@Override
	public void killComponent()
	{
		instance = null;

		towerToDisplay = null;

		titleLabel = null;
		rangeLabel = null;
		damageLabel = null;
		aboutTowerLabel = null;

		upgradeRangeButton = null;
		upgradeDamageButton = null;
	}
}
