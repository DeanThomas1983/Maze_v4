/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze_v4.AbstractClasses;

import java.util.ArrayList;
import java.util.Random;
import maze_v4.DebugVariables;
import maze_v4.Interfaces.IMazeCell;
import maze_v4.Interfaces.IObserver;
import maze_v4.SquareMaze.SquareMazeCell;

/**
 *
 * @author dean
 */
public abstract class AbstractMazeCell implements IMazeCell
{
    private ArrayList<IObserver> observers = new ArrayList<IObserver>();
    private Random random = new Random();
    protected String identity;
    protected ArrayList<Boolean> walls = new ArrayList<Boolean>();
    protected ArrayList<IMazeCell> connectedCells = new ArrayList<IMazeCell>();

    @Override
    public String toString()
    {
        return this.identity;
    }

    public AbstractMazeCell()
    {
        setupNeighbourArray();
    }

    private void setupNeighbourArray()
    {
        for (Integer i = 0; i < getMaximumNumberOfWalls(); i++)
        {
            this.connectedCells.add(null);
            this.walls.add(true);
        }
    }

    public abstract Integer getMaximumNumberOfWalls();

    @Override
    public String getIdentity()
    {
        return this.identity;
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
        return walls.size();
    }

    @Override
    public Boolean getWall(Integer wall)
    {
        return walls.get(wall);
    }

    @Override
    public void setWall(Integer wall, Boolean value)
    {
        this.walls.set(wall, value);

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
