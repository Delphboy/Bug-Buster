package BugBuster.Pathogens;

import BugBuster.FactoryIF;
import BugBuster.Pathogens.Pathogen;
import javafx.scene.canvas.GraphicsContext;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by stc765 on 28/11/17.
 */
public class PathogenFactory implements FactoryIF
{
    private GraphicsContext graphicsContext;
    public PathogenFactory(GraphicsContext gc)
    {
        graphicsContext = gc;
    }

    @Override
    public Pathogen createProduct(int discriminator)
    {
        Pathogen createdPathogen;
        switch(discriminator)
        {
            case 1:
                createdPathogen = new Bacteria(graphicsContext, 0, 200);
                break;
            case 2:
                createdPathogen = new H1N1(graphicsContext, 0, 200);
                break;
            default:
                System.out.println("FACTORY BROKE");
                createdPathogen = null;
                break;
        }

        System.out.println("FACTORY RETURN: " + createdPathogen);
        return createdPathogen;
    }
}
