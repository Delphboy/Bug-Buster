package BugBuster;

import BugBuster.Screens.GameScreen;
import BugBuster.Screens.UIComponents.*;
import BugBuster.Towers.Tower;
import BugBuster.Towers.TowerFactory;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
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
            parent.setCursor(Cursor.CROSSHAIR);
        }
        else if(event.getSource()== parent.getTowerShop().getBuyAntiBioticsBtn())
        {
            createTower(2);
            parent.setCursor(Cursor.CROSSHAIR);
        }
        else if(event.getSource()== parent.getTowerShop().getBuyVaccineBtn())
        {
            createTower(3);
            parent.setCursor(Cursor.CROSSHAIR);
        }

        else if(event.getSource()== parent.getTowerShop().getBuyNextVaccineBtn())
        {
            Player playerInstance = Player.getInstance();
            if(playerInstance.getCurrentVaccine().equalsIgnoreCase("flu"))
            {
                if(playerInstance.getCurrency() >= 100)
                {
                    playerInstance.setCurrentVaccine("MMR");
                    playerInstance.setCurrency(playerInstance.getCurrency() - 100);
                }
            }
            else if(playerInstance.getCurrentVaccine().equalsIgnoreCase("MMR"))
            {
                if(playerInstance.getCurrency() >= 250)
                {
                    playerInstance.setCurrentVaccine("Smallpox");
                    playerInstance.setCurrency(playerInstance.getCurrency() - 250);
                }
            }
            else
            {
                // Do nothing, maximum upgrade achieved
            }
            parent.getTowerShop().update();
        }

        else if(event.getSource() == parent.getOptionsBar().getStartRoundBtn())
        {
            parent.getWorldView().startRound();
        }

        if(event instanceof MouseEvent)
        {
            Point2D tile = getTileClickedLocation(((MouseEvent) event).getX(), ((MouseEvent) event).
                    getY());
            handleMouseEvent(tile);
        }

        updateUI();
    }

    private void handleMouseEvent(Point2D clickedTile)
    {
        Tower towerLocations[][] = parent.getWorldView().getTowerLocations();
        TowerStatsComponent towerStatsComponent = parent.getTowerStats();

        if (unplacedTower != null)
        {
            parent.getWorldView().placeTower((int)clickedTile.getX(), (int)clickedTile.getY(),
                    unplacedTower);
            updateUI();
            unplacedTower = null;
            parent.setCursor(Cursor.DEFAULT);
        }

        if(towerLocations[(int)clickedTile.getX()][(int)clickedTile.getY()] != null)
        {
            towerStatsComponent.setTowerToDisplay(
                    towerLocations[(int)clickedTile.getX()][(int)clickedTile.getY()]);
        }
        else
        {
            towerStatsComponent.setTowerToDisplay(null);
        }
    }

    private void createTower(int towerToMake)
    {
        unplacedTower = towerFactory.createProduct(towerToMake);
    }

    private Point2D getTileClickedLocation(double mouseX, double mouseY)
    {
        Point2D tileLoc = new Point2D(((int)mouseX / Tile.TILE_WIDTH), ((int)mouseY / Tile.TILE_HEIGHT));
        System.out.println("Clicked!\tx: " + mouseX+ "y:" + mouseY + "\tX: "
                + tileLoc.getX() + "Y:" + tileLoc.getY());

        return tileLoc;
    }

    private void updateUI()
    {
        HeaderBarComponent.getInstance().update();
//        parent.getWorldView().update();
    }

}
