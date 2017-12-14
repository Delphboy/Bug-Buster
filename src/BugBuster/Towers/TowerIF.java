package BugBuster.Towers;

import BugBuster.Pathogens.Pathogen;
import java.util.ArrayList;

/**
 * An interface to represent a Tower
 * @author Henry Senior
 * @version 1.0.0
 */
public interface TowerIF
{
    void shootPathogen(ArrayList<Pathogen> pathogens);
    void upgradeTowerRange();
    void upgradeTowerDamage();
}
