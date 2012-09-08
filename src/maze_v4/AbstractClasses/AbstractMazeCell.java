/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze_v4.AbstractClasses;

import java.util.ArrayList;
import java.util.Random;
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

        System.out.println("New wall: " + newWall.getOwner().getIdentity()
                + " / " + newWall.getConnectedCell().getIdentity());
        //  Share the same wall between the two cells
        this.getWalls().replace(location, newWall);
        neighbour.getWalls().replace(neighbourIndex, newWall);
    }
    /**
     *
     */
    protected WallList walls = new WallList(this);

    @Override
    public WallList getWalls()
    {
        return walls;
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

        for (Wall w : walls)
        {
            if (w.getConnectedCell() != null)
            {
                if (w.getConnectedCell().getNumberOfIntactWalls()
                        == w.getConnectedCell().getWallCount())
                {
                    result.add(w.getConnectedCell());
                }
            }
        }
        return result;
    }

    @Override
    public IMazeCell getRandomNeighbourCell()
    {
        IMazeCell result;
        int indexOfWallToDemolish;
        do
        {
            indexOfWallToDemolish = random.nextInt(this.getWallCount());

        } while (this.getWalls().get(indexOfWallToDemolish).getConnectedCell()
                == null);

        this.getWalls().get(indexOfWallToDemolish).setBlocked(false);

        result = this.getWalls().get(indexOfWallToDemolish).getConnectedCell();

        update();

        return result;
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
