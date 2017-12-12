package BugBuster.Pathogens;

import java.util.ArrayList;

public class MoveCommand implements QueueIF
{
    ArrayList<CommandIF> queue = new ArrayList<>();
    public void addCommand(Direction d)
    {
        queue.add(d);
    }

    public ArrayList<CommandIF> getQueue()
    {
        return queue;
    }

    public void removeItem(CommandIF remove)
    {
        if(queue.size() > 0)
            queue.remove(remove);
    }

    @Override
    public boolean isEmpty()
    {
        return queue.size() == 0;
    }

    @Override
    public Object peek()
    {
        return queue.get(0);
    }

    @Override
    public void put(CommandIF item)
    {
        queue.add(item);
    }

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

    @Override
    public int getLength()
    {
        return queue.size();
    }
}
