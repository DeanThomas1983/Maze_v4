/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze_v4.AbstractClasses;

import java.util.ArrayList;
import maze_v4.DebugVariables;
import maze_v4.Interfaces.IMazeCell;
import maze_v4.Interfaces.IMazeStructure;
import maze_v4.Interfaces.IObserver;

/**
 *
 * @author dean
 */
public abstract class AbstractMazeStructure
        implements IMazeStructure
{

    ArrayList<IObserver> observers = new ArrayList<IObserver>();
    IMazeCell originCell;
    IMazeCell exitCell;
    protected ArrayList<IMazeCell> mazeCells = new ArrayList<IMazeCell>();

    /**
     * Return the list of cells in the structure
     *
     * @return an array list of IMazeCell
     */
    @Override
    public ArrayList<IMazeCell> getMazeCells()
    {
        return mazeCells;
    }

    /**
     * Return the cell marked as the origin of the maze. The origin is the cell
     * from which the origin maze spawned from.
     *
     * @return a maze cell
     */
    @Override
    public IMazeCell getOriginCell()
    {
        return originCell;
    }

    /**
     * Return the cell marked as the exit of the maze. The exit is the last cell
     * picked in the generation algorithm.
     *
     * @return a maze cell
     */
    @Override
    public IMazeCell getExitCell()
    {
        return exitCell;
    }

    /**
     * Clear the maze array. Does not reinitialize the structure.
     */
    @Override
    public void clear()
    {
        this.mazeCells.clear();
    }

    /**
     * Set the cell marked as the origin. The origin is the cell from which the
     * origin maze spawned from.
     *
     * @param origin the cell the maze was spawned from
     */
    @Override
    public void setOriginCell(IMazeCell origin)
    {
        this.originCell = origin;
    }

    /**
     * Set the cell marked as the exit. The exit is the last cell picked in the
     * generation algorithm.
     *
     * @param exit the cell that was the last to assigned in the structure
     */
    @Override
    public void setExitCell(IMazeCell exit)
    {
        this.exitCell = exit;
    }

    /**
     * The object has been updated in some way and should among other things
     * notify its observers that they need to react.
     */
    @Override
    public void update()
    {
        if (DebugVariables.SHOW_OBSERVER_INFORMATION)
        {
            System.out.println(this.getClass().getSimpleName() + " updated");
        }
        this.notifyObservers();
    }

    /**
     * Add an observer to the list of observing objects.
     *
     * @param newObserver the observing object to be added
     */
    @Override
    public void registerObserver(IObserver newObserver)
    {
        if (!this.observers.contains(newObserver))
        {
            this.observers.add(newObserver);
            if (DebugVariables.SHOW_OBSERVER_INFORMATION)
            {
                System.out.println(this.getClass().getSimpleName()
                        + " has a new observer: "
                        + newObserver.getClass().getSimpleName());
            }
        }
    }

    /**
     * Remove an observer from the list of observers.
     *
     * @param observerForRemoval the object to be removed
     */
    @Override
    public void removeObserver(IObserver observerForRemoval)
    {
        if (this.observers.contains(observerForRemoval))
        {
            this.observers.remove(observerForRemoval);
            if (DebugVariables.SHOW_OBSERVER_INFORMATION)
            {
                System.out.println(this.getClass().getSimpleName()
                        + " deregistered an observer: "
                        + observerForRemoval.getClass().getSimpleName());
            }
        }
    }

    /**
     *  Send a message to all observing objects to update.
     */
    @Override
    public void notifyObservers()
    {
        for (IObserver o : this.observers)
        {
            o.update();
        }
    }
}
