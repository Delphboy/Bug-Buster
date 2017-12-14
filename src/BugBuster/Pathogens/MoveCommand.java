package BugBuster.Pathogens;

import java.util.ArrayList;

/**
 * A class to implement the command design pattern
 * Use the Queue interface to ensure the queue is correctly implemented
 * Use the queue implemented in the class to store all the commands that need to be executed
 *
 * @author Henry Senior
 * @version 1.0.0
 */
public class MoveCommand implements QueueIF, CommandIF
{
    ArrayList<CommandIF> queue = new ArrayList<>();

    /**
     * The command that is to be added to the queue
     * @param d
     */
    public void addCommand(CommandIF d)
    {
        queue.add(d);
    }

    /**
     * Return the queue of commands
     * @return a queue of commands
     */
    public ArrayList<CommandIF> getQueue()
    {
        return queue;
    }

    /**
     * determine whether or not the queue is empty
     * @return queue.size() == 0
     */
    @Override
    public boolean isEmpty()
    {
        return queue.size() == 0;
    }

    /**
     * View the first item in the queue, without removing it
     * @return queue(0)
     */
    @Override
    public CommandIF peek()
    {
        return queue.get(0);
    }

    /**
     * Add a command to the queue
     * @param item
     */
    @Override
    public void put(CommandIF item)
    {
        queue.add(item);
    }

    /**
     * Return the next command that should be executed, then remove it from the list
     * @return
     */
    @Override
    public CommandIF remove()
    {
        if(! isEmpty())
        {
            CommandIF item = queue.get(0);
            queue.remove(0);
            return item;
        }

        return null;
    }

    /**
     * Return how many items are in the queue
     * @return the size of the queue
     */
    @Override
    public int getLength()
    {
        return queue.size();
    }
}
