package BugBuster.GameObjects.Towers;

import BugBuster.GameObjects.Pathogens.Pathogen;

/**
 * Created by stc765 on 22/11/17.
 */
public interface TowerIF
{
    void shoot(Pathogen enemy);
    Pathogen getTarget();
    void upgradeTower();
}
