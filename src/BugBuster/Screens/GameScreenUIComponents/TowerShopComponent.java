package BugBuster.Screens.GameScreenUIComponents;

import BugBuster.Player;
import BugBuster.Towers.Antibiotics;
import BugBuster.Towers.Vaccine;
import BugBuster.Towers.WhiteBloodCell;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

/**
 * A class to represent the Tower Shop UI Component
 * Holds all the buttons used to buy towers and vaccine upgrades
 * @author Henry Senior
 * @version 1.0.0
 */
public class TowerShopComponent extends Pane implements ComponentIF
{
	// Make a static self pointer to implement the Singleton Design Patter
	private static TowerShopComponent instance;

	Button buyWhiteBloodCellBtn;
	Button buyAntiBioticsBtn;
	Button buyVaccineBtn;
	Button buyNextVaccineBtn;

	/**
	 * Return a pointer to the static instance of the UI component. If no instance exists, create one
	 * @return a static instance of TowerShopComponent
	 */
	public static TowerShopComponent getInstance()
	{
		if (instance == null)
		{
			instance = new TowerShopComponent();
		}
		return instance;
	}

	/**
	 * Create a new TowerShopComponent object
	 * The constructor is private so only one instance can be created
	 */
	private TowerShopComponent()
	{
		WhiteBloodCell blankWhiteBloodCell= new WhiteBloodCell();
		Antibiotics blankAntibioticsTower = new Antibiotics();
		Vaccine blankVaccineTower = new Vaccine();

		setWidth(250);
		setHeight(50);

		// Button to buy a white blood cell tower
		buyWhiteBloodCellBtn = new Button("Buy White Blood Cell Tower\n" +
				blankWhiteBloodCell.getCost() + " apples");
		buyWhiteBloodCellBtn.setFont(new Font("Cooper Black", 12));
		buyWhiteBloodCellBtn.setLayoutX(25);
		buyWhiteBloodCellBtn.setLayoutY(10);
		buyWhiteBloodCellBtn.setMinSize(200, 50);
		buyWhiteBloodCellBtn.setMaxSize(200, 50);
		buyWhiteBloodCellBtn.setTooltip(new Tooltip(new WhiteBloodCell().getAboutMessage()));

		// Button to buy an antibiotics tower
		buyAntiBioticsBtn = new Button("Buy Anti-Biotics Tower\n" +
				blankAntibioticsTower.getCost() + " apples");
		buyAntiBioticsBtn.setFont(new Font("Cooper Black", 12));
		buyAntiBioticsBtn.setLayoutX(25);
		buyAntiBioticsBtn.setLayoutY(70);
		buyAntiBioticsBtn.setMinSize(200, 50);
		buyAntiBioticsBtn.setMaxSize(200, 50);
		buyAntiBioticsBtn.setTooltip(new Tooltip(new Antibiotics().getAboutMessage()));

		// Button to buy an antibiotics tower
		buyVaccineBtn = new Button("Buy Vaccine Tower\n" + blankVaccineTower
				.getCost() +" apples");
		buyVaccineBtn.setFont(new Font("Cooper Black", 12));
		buyVaccineBtn.setLayoutX(25);
		buyVaccineBtn.setLayoutY(130);
		buyVaccineBtn.setMinSize(200, 50);
		buyVaccineBtn.setMaxSize(200, 50);
		buyVaccineBtn.setTooltip(new Tooltip(new Vaccine().getAboutMessage()));

		// Button to buy an antibiotics tower
		buyNextVaccineBtn = new Button("Buy MMR Immunisation\n100 apples");
		buyNextVaccineBtn.setFont(new Font("Cooper Black", 12));
		buyNextVaccineBtn.setLayoutX(25);
		buyNextVaccineBtn.setLayoutY(190);
		buyNextVaccineBtn.setMinSize(200, 50);
		buyNextVaccineBtn.setMaxSize(200, 50);


		getChildren().addAll(buyWhiteBloodCellBtn, buyAntiBioticsBtn,
				buyVaccineBtn, buyNextVaccineBtn);
		update();
	}

	/**
	 * Update the Vaccine immunisation button
	 */
	@Override
	public void update()
	{
		Player playerInstance = Player.getInstance();
		if (playerInstance.getCurrentImmunisationLevel().equalsIgnoreCase("flu"))
		{
			buyNextVaccineBtn.setText("Buy MMR Immunisation\n100 apples");
		} else if (playerInstance.getCurrentImmunisationLevel().equalsIgnoreCase("MMR"))
		{
			buyNextVaccineBtn.setText("Buy Smallpox Immunisation\n250 apples");
		}
		else
		{
			buyNextVaccineBtn.setText("Vaccines fully upgraded");
			buyNextVaccineBtn.setDisable(true);
		}
		buyNextVaccineBtn.setTooltip(new Tooltip("Current Immunisation " +
				"level: " + Player.getInstance().getCurrentImmunisationLevel()));
	}

	/**
	 * delete the instance and all fields
	 */
	@Override
	public void killComponent()
	{
		instance = null;
		buyWhiteBloodCellBtn = null;
		buyAntiBioticsBtn = null;
		buyVaccineBtn = null;
		buyNextVaccineBtn = null;
	}

	/**
	 * return the BuyWhiteBloodCell button for use in the controller
	 * @return buyWhiteBloodCellBtn
	 */
	public Button getBuyWhiteBloodCellBtn()
	{
		return buyWhiteBloodCellBtn;
	}

	/**
	 * return the BuyAntiBiotics button for use in the controller
	 * @return buyAntiBioticsBtn
	 */
	public Button getBuyAntiBioticsBtn()
	{
		return buyAntiBioticsBtn;
	}

	/**
	 * return the BuyVaccine button for use in the controller
	 * @return buyVaccineBtn
	 */
	public Button getBuyVaccineBtn()
	{
		return buyVaccineBtn;
	}

	/**
	 * return the BuyNextVaccine button for use in the controller
	 * @return buyNextVaccineBtn
	 */
	public Button getBuyNextVaccineBtn()
	{
		return buyNextVaccineBtn;
	}
}
