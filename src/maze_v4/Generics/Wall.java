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
public class Wall
implements IObserver, ISubject
{
    private ArrayList<IObserver> observers = new ArrayList<IObserver>();
    private IMazeCell owner;
    private IMazeCell connectedCell;
    private Boolean blocked;

    @Override
    public String toString()
    {
        return "Wall - owner: " + owner + " connected to: " + connectedCell;
    }

    /**
     * Accessor method to return the owner of the wall object
     *
     * @author dean
     * @since 1-9-2012
     *
     * @return IMazeCell the cell which owns the wall
     */
    public IMazeCell getOwner()
    {
        return owner;
    }

    /**
     * Accessor method to return if a wall is blocking or not
     *
     * @author dean
     * @since 1-9-2012
     *
     * @return true if the wall is blocking
     */
    public Boolean getBlocked()
    {
        return blocked;
    }

    /**
     * Setter method to allow us to set if a wall is blocking or not
     *
     * @author dean
     * @since 1-9-2012
     *
     * @param blocked should be true if the wall is blocking
     */
    public void setBlocked(Boolean blocked)
    {
        this.blocked = blocked;

        this.update();
    }

    /**
     * Constructor
     *
     * @author dean
     * @since 1-9-2012
     *
     * @param owner         the cell which will own the wall
     * @param connectedCell the cell which should reside behind the wall
     */
    public Wall(IMazeCell owner,
                IMazeCell connectedCell)
    {
        this.owner = owner;
        this.connectedCell = connectedCell;
        this.blocked = true;

        if (DebugVariables.WALL_INFORMATION)
        {
            System.out.println("New wall: Cell" + owner.getIdentity()
                    + " | Cell" + connectedCell.getIdentity());
        }

    }

    /**
     * Accessor method to return the cell behind the wall (if any)
     *
     * @author dean
     * @since 1-9-2012
     *
     * @return IMazeCell the cell behind the wall
     */
    public IMazeCell getConnectedCell()
    {
        return this.connectedCell;
    }

    /**
     * Setter method to allow us to add a neighboring cell behind this wall
     *
     * @author dean
     * @since 1-9-2012
     *
     * @param mazeCell the cell to connect the wall to
     */
    public void setConnectedCell(IMazeCell mazeCell)
    {
        this.connectedCell = mazeCell;
    }

    public Wall(IMazeCell owner)
    {
        this.owner = owner;
        this.connectedCell = null;
        this.blocked = true;
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
