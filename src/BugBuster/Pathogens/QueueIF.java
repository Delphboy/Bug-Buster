package BugBuster.Pathogens;

public interface QueueIF
{
    boolean isEmpty();
    Object peek();
    void put(CommandIF item);
    CommandIF remove();
    int getLength();
}