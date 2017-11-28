package BugBuster;

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
                createdPathogen = new Pathogen();
                break;
            default:
                createdPathogen = null;
                break;
        }

        return (Pathogen)createdPathogen;
    }
}
