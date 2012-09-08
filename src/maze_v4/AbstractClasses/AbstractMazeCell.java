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
import maze_v4.Generics.Wall;
import maze_v4.Generics.WallList;

/**
 *
 * @author dean
 */
public class AbstractMazeCell implements IMazeCell
{

    private ArrayList<IObserver> observers = new ArrayList<IObserver>();
    private Random random = new Random();
    protected String identity;

    @Override
    public String toString()
    {
        return this.identity;
    }

    @Override
    public void addNeighbourCell(IMazeCell neighbour, int location)
    {
        int neighbourIndex = SquareMazeCell.getOppositeDirection(location);

        Wall newWall = new Wall(this, neighbour);

        //  Share the same wall between the two cells
        this.getWalls().replace(location, newWall);
        neighbour.getWalls().replace(neighbourIndex, newWall);
    }
    /**
     *
     */
    protected WallList walls;

    @Override
    public WallList getWalls()
    {
        return walls;
    }

    public AbstractMazeCell()
    {
        this.walls = new WallList(this);
        walls.registerObserver(this);
    }

    @Override
    public int getNumberOfIntactWalls()
    {
        int result = 0;

        for (Wall w : walls)
        {
            if (w.getBlocked())
            {
                result++;
            }
        }
        return result;
    }

    @Override
    public int getWallCount()
    {
        return this.walls.size();
    }

    @Override
    public int getNumberOfAccessibleNeighbours()
    {
        int result = 0;

        for (Wall w : walls)
        {
            if (!w.getBlocked() && (w.getConnectedCell() != null))
            {
                result++;
            }
        }

        return result;
    }

    @Override
    public int getTotalNumberOfNeighbours()
    {
        int result = 0;

        for (Wall w : walls)
        {
            if (w.getConnectedCell() != null)
            {
                result++;
            }
        }

        return result;
    }

    @Override
    public String getIdentity()
    {
        return this.identity;
    }

    @Override
    public ArrayList<IMazeCell> getListedOfNeighboursWithAllWallsIntact()
    {
        ArrayList<IMazeCell> result = new ArrayList<IMazeCell>();

        for (Wall w : this.walls)
        {
            System.out.print("Evaluating " + w.toString());

            if (w.getConnectedCell() != null)
            {
                if (w.getConnectedCell().getNumberOfIntactWalls()
                        == w.getConnectedCell().getWallCount())
                {
                    if (w.getOwner() == this)
                    {
                        result.add(w.getConnectedCell());
                    }
                    else
                    {
                        result.add(w.getOwner());
                    }
                    System.out.print(" PASS\n");
                }
                else
                {
                    System.out.print(" FAIL\n");
                }
            }
            else
            {
                System.out.print(" FAIL\n");
            }
        }
        return result;
    }

    @Override
    public IMazeCell chooseRandomIntactNeighbourCell(
            ArrayList<IMazeCell> intactNeighbours)
    {
        int index = random.nextInt(intactNeighbours.size());

        return intactNeighbours.get(index);
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
