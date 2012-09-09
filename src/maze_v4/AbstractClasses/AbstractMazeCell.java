/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze_v4.AbstractClasses;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import maze_v4.DebugVariables;
import maze_v4.Interfaces.IMazeCell;
import maze_v4.Interfaces.IObserver;

/**
 *
 * @author dean
 */
public abstract class AbstractMazeCell implements IMazeCell
{

    private ArrayList<IObserver> observers = new ArrayList<IObserver>();
    protected Random random = new Random();
    protected Point coordinates;
    protected Boolean walls[];
    protected ArrayList<IMazeCell> connectedCells = new ArrayList<IMazeCell>();

    public abstract Integer getMaximumNumberOfWalls();

    /**
     * Return the coordinates of the cell.
     *
     * @return a cell representing the coordinates of the cell
     */
    @Override
    public String toString()
    {
        return this.coordinates.x + "," + this.coordinates.y;
    }

    /**
     * Default constructor - all derived classes should call this else the cell
     * linking mechanism will not work.
     */
    public AbstractMazeCell()
    {
        setupNeighbourArray();
    }

    /**
     * Setup the list of walls and neighbors based upon how many walls a cell
     * should have. Called from the default constructor.
     */
    private void setupNeighbourArray()
    {
        this.walls = new Boolean[getMaximumNumberOfWalls()];

        for (Integer i = 0; i < getMaximumNumberOfWalls(); i++)
        {
            this.connectedCells.add(null);
            this.walls[i] = true;
        }
    }

    /**
     * Return the coordinates of this cell.
     *
     * @return the coordinates of this cell
     */
    @Override
    public Point getCoordinates()
    {
        return this.coordinates;
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
     * Send a message to all observing objects to update.
     */
    @Override
    public void notifyObservers()
    {
        for (IObserver o : this.observers)
        {
            o.update();
        }
    }

    /**
     * Return a list of neighbors which have all of their walls intact.
     *
     * @return a list of neighboring intact cells
     */
    @Override
    public ArrayList<IMazeCell> getListOfIntactNeighbours()
    {
        ArrayList<IMazeCell> result = new ArrayList<IMazeCell>();

        for (IMazeCell cell : this.connectedCells)
        {
            if (cell != null)
            {
                if (cell.getNumberOfIntactWalls() == cell.getNumberOfWalls())
                {
                    result.add(cell);
                }
            }
        }
        return result;
    }

    /**
     * Return the number of walls which are intact in the cell.
     *
     * @return an integer the number of intact walls
     */
    @Override
    public Integer getNumberOfIntactWalls()
    {
        Integer result = 0;

        for (Boolean b : walls)
        {
            if (b)
            {
                result++;
            }
        }

        return result;
    }

    /**
     * Return the number of walls the cell has.
     *
     * @return an integer representing the number of walls.
     */
    @Override
    public Integer getNumberOfWalls()
    {
        return walls.length;
    }

    /**
     * Return the state of a wall in the array.
     *
     * @param index the index of the wall in the array
     *
     * @return true if the wall is intact
     */
    @Override
    public Boolean getWall(Integer index)
    {
        return walls[index];
    }

    /**
     * Set the state of a wall.
     *
     * @param index the index of the wall in the array
     * @param value true if the wall should be blocking (intact)
     */
    @Override
    public void setWall(Integer index, Boolean value)
    {
        this.walls[index] = value;

        this.update();
    }

    /**
     * Return the neighbor at a given position in the array.
     *
     * @param index the index of the neighbor in the array
     *
     * @return a cell representing the neighbor
     */
    @Override
    public IMazeCell getNeighbour(Integer index)
    {
        return this.connectedCells.get(index);
    }

    /**
     * Set a cell as a specific neighbor in the array
     *
     * @param index     the index in the array where the neighbor should be
     *                  stored
     * @param neighbour the cell to be stored in the array
     */
    @Override
    public void setNeighbour(Integer index, IMazeCell neighbour)
    {
        this.connectedCells.set(index, neighbour);
    }
}
