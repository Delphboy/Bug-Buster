package BugBuster.Towers;

import BugBuster.FactoryIF;

/**
 * Created by stc765 on 28/11/17.
 */
public class TowerFactory implements FactoryIF
{
    public TowerFactory()
    {}

    @Override
    public Tower createProduct(int discriminator)
    {
        Tower createdTower;

        switch(discriminator)
        {
            case 1:
                createdTower = new WhiteBloodCell();
                break;
            case 2:
                createdTower = new Antibiotics();
                break;
            case 3:
                createdTower = new Vaccine();
                break;
            default:
                createdTower = null;
                break;
        }

        return (Tower)createdTower;
    }
}
