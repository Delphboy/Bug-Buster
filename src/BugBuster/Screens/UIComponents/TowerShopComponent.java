package BugBuster.Screens.UIComponents;

import BugBuster.GameObjects.Towers.Tower;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;


import java.beans.EventHandler;

public class TowerShopComponent extends Pane
{
	public TowerShopComponent()
	{
		Label lbl = new Label("I should provide a way of buying towers");
		getChildren().add(lbl);
	}
}
