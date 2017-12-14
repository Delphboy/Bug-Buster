package BugBuster.Towers;

import BugBuster.FactoryIF;

/**
 * A class to represent the TowerFactory
 * This class utilises the Factory Design Pattern
 * The factory will create a tower based on a given value
 * @author Henry Senior
 * @version 1.0.0
 */
public class TowerFactory implements FactoryIF
{
    /**
     * Create a new TowerFactory object
     */
    public TowerFactory()
    {}

    /**
     * Create a tower that corresponds the the given discriminator
     * @param discriminator
     * @return the requested Tower
     */
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

        return createdTower;
    }
}
