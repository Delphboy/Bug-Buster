package BugBuster;

/**
 * Created by stc765 on 22/11/17.
 */
public interface TowerIF
{
    void shoot(Pathogen enemy);
    Pathogen getTarget();
    void upgradeTowerRange();
    void upgradeTowerDamage();
}
