package BugBuster.Pathogens;

import BugBuster.FactoryIF;
import javafx.scene.canvas.GraphicsContext;

/**
 * A class to represent the PathogenFactory
 * This class utilises the Factory Design Pattern
 * The factory will create a pathogen based on a given value
 * @author Henry Senior
 * @version 1.0.0
 */
public class PathogenFactory implements FactoryIF
{
    private GraphicsContext graphicsContext;

    /**
     * Create a new PathogenFactory object
     * @param gc
     */
    public PathogenFactory(GraphicsContext gc)
    {
        graphicsContext = gc;
    }

    /**
     * Create a pathogen that corresponds the the given discriminator
     * @param discriminator
     * @return the requested pathogen
     */
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
                createdPathogen = new Flu(graphicsContext, 0, 200);
                break;
            case 3:
                createdPathogen = new MMR(graphicsContext, 0, 200);
                break;
            case 4:
                createdPathogen = new SmallPox(graphicsContext, 0, 200);
                break;
            default:
                System.out.println("FACTORY ERROR: UNRECOGNISED DISCRIMINATOR");
                createdPathogen = null;
                break;
        }

        return createdPathogen;
    }
}
