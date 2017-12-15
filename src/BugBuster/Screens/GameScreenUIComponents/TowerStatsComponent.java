package BugBuster.Screens.GameScreenUIComponents;

import BugBuster.Towers.Tower;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

/**
 * A class to represent the Tower Stats UI Component
 * Holds all the buttons and labels used to display information about a selected tower and to allow
 * the player to upgrade the tower
 * @author Henry Senior
 * @version 1.0.0
 */
public class TowerStatsComponent extends Pane implements ComponentIF
{
	//Create a static self pointer to implement the Singleton Design Pattern
	private static TowerStatsComponent instance = null;
	private Tower towerToDisplay;

	Label titleLabel;
	Label rangeLabel;
	Label damageLabel;
	Label aboutTowerLabel;

	Button upgradeRangeButton;
	Button upgradeDamageButton;

	/**
	 * Return a pointer to the static instance of the class. If such an instance doesn't exist, create
	 * one
	 * @return a reference to a static instance of the class
	 */
	public static TowerStatsComponent getInstance()
	{
		if (instance == null)
		{
			instance = new TowerStatsComponent();
		}

		return instance;
	}

	/**
	 * Create a new TowerStatsComponent
	 * Constructor made private to ensure only one instance is created
	 */
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
		aboutTowerLabel.setFont(new Font("Cooper Black", 10));
		aboutTowerLabel.setLayoutX(10);
		aboutTowerLabel.setLayoutY(140);

		// Set the increase damage button
		upgradeDamageButton = new Button("+1");
		upgradeDamageButton.setFont(new Font("Cooper Black", 10));
		upgradeDamageButton.setLayoutX(140);
		upgradeDamageButton.setLayoutY(50);
		upgradeDamageButton.setMinSize(100, 30);
		upgradeDamageButton.setMaxSize(100, 30);
		upgradeDamageButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				towerToDisplay.upgradeTowerDamage();
				update();
				HeaderBarComponent.getInstance().update();
			}
		});

		// Set the increase damage button
		upgradeRangeButton = new Button("+1");
		upgradeRangeButton.setFont(new Font("Cooper Black", 12));
		upgradeRangeButton.setLayoutX(140);
		upgradeRangeButton.setLayoutY(100);
		upgradeRangeButton.setMinSize(100, 30);
		upgradeRangeButton.setMaxSize(100, 30);
		upgradeRangeButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				towerToDisplay.upgradeTowerRange();
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

	/**
	 * Update the display to show details about the tower
	 * Update the values in the upgrade buttons
	 * Toggle whether or not the buttons are enabled
	 */
	@Override
	public void update()
	{
		if(towerToDisplay != null)
		{
			titleLabel.setText(towerToDisplay.getType());
			aboutTowerLabel.setText(towerToDisplay.getAboutMessage());

			rangeLabel.setText("Range: " + towerToDisplay.getRadius());
			damageLabel.setText("Damage: " + towerToDisplay.getDamage());

			upgradeRangeButton.setText("+1    " + (towerToDisplay.getRadius() * 5) + " apples");
			upgradeDamageButton.setText("+1    " + (towerToDisplay.getDamage() * 5) + " apples");

			rangeLabel.setDisable(false);
			damageLabel.setDisable(false);

			upgradeRangeButton.setDisable(towerToDisplay.isMaxRadiusAchieved());
			upgradeDamageButton.setDisable(towerToDisplay.isMaxDamageAchieved());
		}
		else
		{
			titleLabel.setText("Select a Tower");
			aboutTowerLabel.setText("Select a tower to discover how it\nworks");
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

		upgradeRangeButton = null;
		upgradeDamageButton = null;

		titleLabel = null;
		rangeLabel = null;
		damageLabel = null;
		aboutTowerLabel = null;

	}
}
