/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze_v4.AbstractClasses;

import java.util.ArrayList;
import java.util.Random;
import maze_v4.Interfaces.IMazeCell;
import maze_v4.Wall;
import maze_v4.WallList;

/**
 *
 * @author dean
 */
public class AbstractMazeCell
        implements IMazeCell
{

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
        int neighbourIndex = location + 2;
        if (neighbourIndex > 3)
        {
            neighbourIndex -= 4;
        }

        if (this.getWalls().get(location).getConnectedCell() != null)
        {
            this.getWalls().get(location).setConnectedCell(neighbour);
            neighbour.getWalls().get(neighbourIndex).setConnectedCell(this);
        }
    }
    /**
     *
     */
    protected WallList walls = new WallList();

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

        int indexOfWallToDemolish = random.nextInt(this.getWallCount());

        this.getWalls().get(indexOfWallToDemolish).setBlocked(false);

        result = this.getWalls().get(indexOfWallToDemolish).getConnectedCell();

        return result;
    }
}
