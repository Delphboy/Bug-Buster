package BugBuster.Screens.UIComponents;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class TowerShopComponent extends Pane
{
	public TowerShopComponent()
	{
		Label lbl = new Label("I should provide a way of buying towers");
		getChildren().add(lbl);
	}
}
