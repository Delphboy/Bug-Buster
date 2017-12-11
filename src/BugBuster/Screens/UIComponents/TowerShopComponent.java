package BugBuster.Screens.UIComponents;

import BugBuster.Player;
import BugBuster.Towers.Tower;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class TowerShopComponent extends Pane implements ComponentIF
{
	private static TowerShopComponent instance;

	Button buyWhiteBloodCellBtn;
	Button buyAntiBioticsBtn;
	Button buyVaccineBtn;
	Button buyNextVaccineBtn;

	public static TowerShopComponent getInstance()
	{
		if (instance == null)
		{
			instance = new TowerShopComponent();
		}
		return instance;
	}

	private TowerShopComponent()
	{
		setWidth(250);
		setHeight(50);

		// Button to buy a white blood cell tower
		buyWhiteBloodCellBtn = new Button("Buy White Blood Cell Tower");
		buyWhiteBloodCellBtn.setFont(new Font("Cooper Black", 12));
		buyWhiteBloodCellBtn.setLayoutX(25);
		buyWhiteBloodCellBtn.setLayoutY(10);
		buyWhiteBloodCellBtn.setMinSize(200, 50);
		buyWhiteBloodCellBtn.setMaxSize(200, 50);

		// Button to buy an antibiotics tower
		buyAntiBioticsBtn = new Button("Buy Anti-Biotics Tower");
		buyAntiBioticsBtn.setFont(new Font("Cooper Black", 12));
		buyAntiBioticsBtn.setLayoutX(25);
		buyAntiBioticsBtn.setLayoutY(70);
		buyAntiBioticsBtn.setMinSize(200, 50);
		buyAntiBioticsBtn.setMaxSize(200, 50);

		// Button to buy an antibiotics tower
		buyVaccineBtn = new Button("Buy Vaccine Tower");
		buyVaccineBtn.setFont(new Font("Cooper Black", 12));
		buyVaccineBtn.setLayoutX(25);
		buyVaccineBtn.setLayoutY(130);
		buyVaccineBtn.setMinSize(200, 50);
		buyVaccineBtn.setMaxSize(200, 50);

		// Button to buy an antibiotics tower
		buyNextVaccineBtn = new Button("Buy MMR Immunisation: 100");
		buyNextVaccineBtn.setFont(new Font("Cooper Black", 8));
		buyNextVaccineBtn.setLayoutX(25);
		buyNextVaccineBtn.setLayoutY(190);
		buyNextVaccineBtn.setMinSize(200, 50);
		buyNextVaccineBtn.setMaxSize(200, 50);

		getChildren().addAll(buyWhiteBloodCellBtn, buyAntiBioticsBtn,
				buyVaccineBtn, buyNextVaccineBtn);
	}

	@Override
	public void update()
	{
		Player playerInstance = Player.getInstance();
		if (playerInstance.getCurrentVaccine().equalsIgnoreCase("flu"))
		{
			buyNextVaccineBtn.setText("Buy MMR Immunisation: 100");
		} else if (playerInstance.getCurrentVaccine().equalsIgnoreCase("MMR"))
		{
			buyNextVaccineBtn.setText("Buy Smallpox Immunisation: 250");
		}
		else
		{
			buyNextVaccineBtn.setText("Vaccines fully upgraded");
			buyNextVaccineBtn.setDisable(true);
		}
	}

	@Override
	public void killComponent()
	{
		instance = null;
		buyWhiteBloodCellBtn = null;
		buyAntiBioticsBtn = null;
		buyVaccineBtn = null;
		buyNextVaccineBtn = null;
	}

	public Button getBuyWhiteBloodCellBtn()
	{
		return buyWhiteBloodCellBtn;
	}

	public Button getBuyAntiBioticsBtn()
	{
		return buyAntiBioticsBtn;
	}

	public Button getBuyVaccineBtn()
	{
		return buyVaccineBtn;
	}

	public Button getBuyNextVaccineBtn()
	{
		return buyNextVaccineBtn;
	}
}
