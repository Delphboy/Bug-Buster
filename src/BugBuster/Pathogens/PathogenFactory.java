package BugBuster.Pathogens;

import BugBuster.FactoryIF;
import javafx.scene.canvas.GraphicsContext;

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
