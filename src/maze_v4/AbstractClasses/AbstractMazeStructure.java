/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze_v4.AbstractClasses;

import java.util.ArrayList;
import maze_v4.DataModel;
import maze_v4.Interfaces.IMazeCell;
import maze_v4.Interfaces.IMazeStructure;
import maze_v4.Interfaces.IObserver;

/**
 *
 * @author dean
 */
public abstract class AbstractMazeStructure
implements IMazeStructure
//IObserver,
//ISubject
{
    ArrayList<IObserver> observers = new ArrayList<IObserver>();
    IMazeCell originCell;
    IMazeCell exitCell;

    protected ArrayList<IMazeCell> mazeCells = new ArrayList<IMazeCell>();

    @Override
    public ArrayList<IMazeCell> getMazeCells()
    {
        return mazeCells;
    }

    @Override
    public IMazeCell getOriginCell()
    {
        return originCell;
    }

    @Override
    public IMazeCell getExitCell()
    {
        return exitCell;
    }

    @Override
    public void clear()
    {
        this.mazeCells.clear();
    }

    @Override
    public void setOriginCell(IMazeCell origin)
    {
        this.originCell = origin;
    }

    @Override
    public void setExitCell(IMazeCell exit)
    {
        this.exitCell = exit;
    }

    @Override
    public void update()
    {
        System.out.println(this.getClass().getSimpleName() + " updated");

        this.notifyObservers();
    }

    @Override
    public void registerObserver(IObserver o)
    {
        if (!this.observers.contains(o))
        {
            this.observers.add(o);

            System.out.println(this.getClass().getSimpleName()
                + " has a new observer: " + o.getClass().getSimpleName());
        }
    }

    @Override
    public void removeObserver(IObserver o)
    {
        if (this.observers.contains(o))
        {
            this.observers.remove(o);

            System.out.println(this.getClass().getSimpleName()
                + " deregistered an observer: " + o.getClass().getSimpleName());
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
