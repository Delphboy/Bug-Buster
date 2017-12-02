package BugBuster;

import BugBuster.Screens.GameScreen;
import BugBuster.Screens.UIComponents.*;
import BugBuster.Towers.Tower;
import BugBuster.Towers.TowerFactory;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

/**
 * Created by stc765 on 30/11/17.
 */
public class Controller implements EventHandler
{
    GameScreen parent;
    Tower unplacedTower = null;

    private TowerFactory towerFactory = new TowerFactory();

    public Controller(GameScreen parent)
    {
        this.parent = parent;
    }

    @Override
    public void handle(Event event)
    {
        if(event.getSource()== parent.getTowerShop().getBuyWhiteBloodCellBtn())
        {
            createTower(1);
        }
        else if(event.getSource()== parent.getTowerShop().getBuyAntiBioticsBtn())
        {
            createTower(1);
        }
        else if(event instanceof MouseEvent)
        {
            Point2D tile = getTileClickedLocation(((MouseEvent) event).getX(), ((MouseEvent) event).
                    getY());
            if (unplacedTower != null)
            {
                parent.getWorldView().placeTower((int)tile.getX(), (int)tile.getY(), unplacedTower);
                updateUI();
                unplacedTower = null;
            }
        }

        updateUI();
    }

    private void createTower(int towerToMake)
    {
        unplacedTower = towerFactory.createProduct(towerToMake);
    }

    private Point2D getTileClickedLocation(double mouseX, double mouseY)
    {
        Point2D tileLoc = new Point2D(((int)mouseX / Tile.TILE_WIDTH), ((int)mouseY / Tile.TILE_HEIGHT));
        System.out.println("Clicked!\tx: " + tileLoc.getX()+ "\ty:" + tileLoc.getY());

        return tileLoc;
    }

    private void updateUI()
    {
        HeaderBarComponent.getInstance().update();
//        parent.getWorldView().update();
    }

}
