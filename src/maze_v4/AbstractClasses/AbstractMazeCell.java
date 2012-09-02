/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze_v4.AbstractClasses;

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
    private String identity;

    @Override
    public void addNeighbourCell(IMazeCell neighbour)
    {
        this.walls.add(new Wall(this,neighbour));
        
        if (neighbour != null)
        {
            neighbour.getWalls().add(new Wall(neighbour,this));
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
}
