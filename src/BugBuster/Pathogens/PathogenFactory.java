package BugBuster.Pathogens;

import BugBuster.FactoryIF;
import BugBuster.Pathogens.Pathogen;

/**
 * Created by stc765 on 28/11/17.
 */
public class PathogenFactory implements FactoryIF
{
    public PathogenFactory()
    {}

    @Override
    public Object createProduct(int discriminator)
    {
        Pathogen createdPathogen;

        switch(discriminator)
        {
            case 1:
                createdPathogen = new Pathogen(null, 10, 10);
                break;
            default:
                createdPathogen = null;
                break;
        }

        return (Pathogen)createdPathogen;
    }
}
