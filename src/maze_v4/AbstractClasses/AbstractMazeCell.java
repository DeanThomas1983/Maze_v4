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
    protected Random random = new Random(1);
    protected Point coordinates;
    protected Boolean walls[];
    protected ArrayList<IMazeCell> connectedCells = new ArrayList<IMazeCell>();

    @Override
    public String toString()
    {
        return this.coordinates.x + "," + this.coordinates.y;
    }

    public AbstractMazeCell()
    {
        setupNeighbourArray();
    }

    private void setupNeighbourArray()
    {
        this.walls = new Boolean[getMaximumNumberOfWalls()];

        for (Integer i = 0; i < getMaximumNumberOfWalls(); i++)
        {
            this.connectedCells.add(null);
            this.walls[i] = true;
            //this.walls.add(true);
        }
    }

    public abstract Integer getMaximumNumberOfWalls();

    @Override
    public Point getCoordinates()
    {
        return this.coordinates;
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

    @Override
    public Integer getNumberOfWalls()
    {
        return walls.length;
    }

    @Override
    public Boolean getWall(Integer index)
    {
        return walls[index];
    }

    @Override
    public void setWall(Integer index, Boolean value)
    {
        this.walls[index] = value;

        this.update();
    }

    @Override
    public IMazeCell getNeighbour(Integer index)
    {
        return this.connectedCells.get(index);
    }

    @Override
    public void setNeighbour(Integer index, IMazeCell neighbour)
    {
        this.connectedCells.set(index, neighbour);
    }
}
