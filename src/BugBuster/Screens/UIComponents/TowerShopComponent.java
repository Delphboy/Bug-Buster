package BugBuster.Screens.UIComponents;

import BugBuster.GameObjects.Towers.Tower;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TowerShopComponent extends ScrollPane
{
	private int numOfShopItems = 0;

	public TowerShopComponent()
	{
		Pane paneToAdd = null;

		// set scroll properties
		setHbarPolicy(ScrollBarPolicy.NEVER);
		setVbarPolicy(ScrollBarPolicy.AS_NEEDED);

		// generate tower items
		for (int i = 0; i < 5; i++)
		{
			paneToAdd = createPane(new Tower("Generic Tower", i, i));
			if (paneToAdd != null)
			{
				paneToAdd.setLayoutX(0);
				paneToAdd.setLayoutY(0 * numOfShopItems);
				getChildren().add(paneToAdd);
				numOfShopItems++;
			}
		}
	}

	private HBox createPane(Tower towerForPane)
	{
		HBox towerItem = new HBox();

		towerItem.setMaxWidth(250);
		towerItem.setMaxHeight(100);
		towerItem.setMinWidth(250);
		towerItem.setMinHeight(100);

		Canvas canvas = new Canvas(250, 100);
		GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

		Label towerInfo = new Label(towerForPane.toString());
		towerInfo.setLayoutX(10);
		towerInfo.setLayoutY(10);

		graphicsContext.drawImage(towerForPane.getImage(), 20, 20, 20, 20);
		graphicsContext.setFill(Color.GREENYELLOW);
		graphicsContext.fillRect(0,0,250,100);

		Button btnBuyTower = new Button("Buy");

		// Add information to shop item
		towerItem.getChildren().add(canvas);
		towerItem.getChildren().add(towerInfo);
		towerItem.getChildren().add(btnBuyTower);

		return towerItem;
	}
}
