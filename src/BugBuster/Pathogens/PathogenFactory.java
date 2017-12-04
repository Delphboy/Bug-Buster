package BugBuster.Pathogens;

import BugBuster.FactoryIF;
import BugBuster.Pathogens.Pathogen;
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
                createdPathogen = new Pathogen(graphicsContext, 0, 200);
                break;
            default:
                createdPathogen = null;
                break;
        }

        return (Pathogen)createdPathogen;
    }
}
