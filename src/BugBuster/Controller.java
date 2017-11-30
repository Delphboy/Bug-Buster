package BugBuster;

import BugBuster.Screens.GameScreen;
import BugBuster.Screens.UIComponents.*;
import javafx.event.Event;
import javafx.event.EventHandler;

/**
 * Created by stc765 on 30/11/17.
 */
public class Controller implements EventHandler
{
    public Controller(GameScreen parent)
    {
        this.parent = parent;
    }

    GameScreen parent;

    @Override
    public void handle(Event event)
    {
        if(event.getSource()== parent.getTowerShop().getGiveTowerBtn())
        {
            System.out.println("sent from the controller!");
        }
    }
}
