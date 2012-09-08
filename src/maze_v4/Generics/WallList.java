/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze_v4.Generics;

import java.util.ArrayList;
import maze_v4.DebugVariables;
import maze_v4.Interfaces.IMazeCell;
import maze_v4.Interfaces.IObserver;
import maze_v4.Interfaces.ISubject;

/**
 *
 * @author dean
 */
public class WallList extends ArrayList<Wall>
implements IObserver, ISubject
{
    ArrayList<IObserver> observers = new ArrayList<IObserver>();
    IMazeCell mazeCell;

    public WallList(IMazeCell mazeCell)
    {
        this.mazeCell = mazeCell;
    }

    private WallList()
    {

    }

    @Override
    public boolean add(Wall newWall)
    {
        boolean result;
        result = super.add(newWall);
        newWall.registerObserver(this);
        return result;

    }

    @Override
    public boolean contains(Object o)
    {
        Wall w = (Wall)o;

        for (Wall w1 : this)
        {
            if ((w1.getOwner() == w.getOwner())
                && (w1.getConnectedCell() == w.getConnectedCell()))
            {
                return true;
            }
        }

        return false;
    }

    public boolean replace(Integer index, Wall newWall)
    {
        boolean result;

        if (this.set(index, newWall) != null)
        {
            newWall.registerObserver(this);
            result = true;
        }
        else
        {
            result = false;
        }
        return result;
    }

    public void fillWithBlanks(Integer count)
    {
        for (int i = 0; i < count; i++)
        {
            Wall newWall = new Wall(this.mazeCell);
            this.add(newWall);
        }
    }

    public Wall findWall(IMazeCell neighbourCell)
    {
        for (Wall w : this)
        {
            if (w.getConnectedCell().equals(neighbourCell))
            {
                return w;
            }
            else
            {
                if (w.getOwner().equals(neighbourCell) && w.getConnectedCell().equals(this.mazeCell))
                {
                    return w;
                }
            }
        }
        return null;
    }

    @Override
    public void update()
    {
        if (DebugVariables.SHOW_OBSERVER_INFORMATION)
        {
            System.out.println(this.getClass().getSimpleName() + " updated");
        }
        this.notifyObservers();
    }

    @Override
    public void registerObserver(IObserver o)
    {
        if (!this.observers.contains(o))
        {
            this.observers.add(o);
            if (DebugVariables.SHOW_OBSERVER_INFORMATION)
            {
                System.out.println(this.getClass().getSimpleName()
                        + " has a new observer: " + o.getClass().getSimpleName());
            }
        }
    }

    @Override
    public void removeObserver(IObserver o)
    {
        if (this.observers.contains(o))
        {
            this.observers.remove(o);
            if (DebugVariables.SHOW_OBSERVER_INFORMATION)
            {
                System.out.println(this.getClass().getSimpleName()
                        + " deregistered an observer: " + o.getClass().getSimpleName());
            }
        }
    }

    @Override
    public void notifyObservers()
    {
        for (IObserver o : this.observers)
        {
            o.update();
        }
    }
}
