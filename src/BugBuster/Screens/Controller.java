package BugBuster.Screens;

import BugBuster.Player;
import BugBuster.Screens.UIComponents.*;
import BugBuster.Towers.*;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;

/**
 * This class provides a central control point allowing different UI components to communicate
 * This class is used to implement the MVC Design Pattern
 * @author Henry Senior
 * @version 1.0.0
 */
public class Controller implements EventHandler
{
    private GameScreen parent;
    private Tower unplacedTower = null;
    private TowerFactory towerFactory = new TowerFactory();

    /**
     * Create a new Controller object
     * @param parent
     */
    public Controller(GameScreen parent)
    {
        this.parent = parent;
    }

    /**
     * Method used to handle any events that occur in the parent
     * @param event
     */
    @Override
    public void handle(Event event)
    {
        Player player = Player.getInstance();
        if(event.getSource()== parent.getTowerShop().getBuyWhiteBloodCellBtn())
        {
            WhiteBloodCell wbc = new WhiteBloodCell();
            if(player.getCurrency() > wbc.getCost())
            {
                createTower(1);
                parent.setCursor(Cursor.CROSSHAIR);
            }
        }
        else if(event.getSource()== parent.getTowerShop().getBuyAntiBioticsBtn())
        {
            Antibiotics antibiotics = new Antibiotics();
            if(player.getCurrency() > antibiotics.getCost())
            {
                createTower(2);
                parent.setCursor(Cursor.CROSSHAIR);
            }
        }
        else if(event.getSource()== parent.getTowerShop().getBuyVaccineBtn())
        {
            Vaccine vaccine = new Vaccine();
            if(player.getCurrency() > vaccine.getCost())
            {
                createTower(3);
                parent.setCursor(Cursor.CROSSHAIR);
            }
        }

        else if(event.getSource()== parent.getTowerShop().getBuyNextVaccineBtn())
        {
            BugBuster.Player playerInstance = BugBuster.Player.getInstance();
            if(playerInstance.getCurrentImmunisationLevel().equalsIgnoreCase("flu"))
            {
                if(playerInstance.getCurrency() >= 100)
                {
                    playerInstance.setCurrentImmunisationLevel("MMR");
                    playerInstance.setCurrency(playerInstance.getCurrency() - 100);
                }
            }
            else if(playerInstance.getCurrentImmunisationLevel().equalsIgnoreCase("MMR"))
            {
                if(playerInstance.getCurrency() >= 250)
                {
                    playerInstance.setCurrentImmunisationLevel("Smallpox");
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

    /**
     * A specific method to handle any mouse events detected by the handle() method
     * @param clickedTile
     */
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

    /**
     * Create a tower and temporarily store it
     * @param towerToMake
     */
    private void createTower(int towerToMake)
    {
        unplacedTower = towerFactory.createProduct(towerToMake);
    }

    /**
     * get the location of a mouse click
     * @param mouseX
     * @param mouseY
     * @return
     */
    private Point2D getTileClickedLocation(double mouseX, double mouseY)
    {
        Point2D tileLoc = new Point2D(((int)mouseX / Tile.TILE_WIDTH), ((int)mouseY / Tile.TILE_HEIGHT));
        System.out.println("Clicked!\tx: " + mouseX+ "y:" + mouseY + "\tX: "
                + tileLoc.getX() + "Y:" + tileLoc.getY());

        return tileLoc;
    }

    /**
     * Update the header bar
     */
    private void updateUI()
    {
        HeaderBarComponent.getInstance().update();
    }

}
