package BugBuster.Pathogens;

/**
 * An interface to represent a queue
 * @author Henry Senior
 * @version 1.0.0
 */
public interface QueueIF
{
    boolean isEmpty();
    Object peek();
    void put(CommandIF item);
    CommandIF remove();
    int getLength();
}