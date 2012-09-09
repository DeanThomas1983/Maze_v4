/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze_v4.SquareMaze;

import maze_v4.AbstractClasses.AbstractMazeCell;
import maze_v4.Interfaces.IMazeCell;

/**
 *
 * @author dean
 */
public final class SquareMazeCell
        extends AbstractMazeCell
        implements IMazeCell
{

    public static int NORTH = 0;
    public static int EAST = 1;
    public static int SOUTH = 2;
    public static int WEST = 3;

    public int getOppositeDirection(int direction)
    {
        int result = direction + 2;
        if (result > (getMaximumNumberOfWalls() - 1))
        {
            result -= getMaximumNumberOfWalls();
        }
        return result;
    }

    public SquareMazeCell()
    {
        this("Untitled Cell");
    }

    public SquareMazeCell(String identity)
    {
        super();
        //  Create a new cell with no neighbours
        this.identity = identity;
    }

    public IMazeCell getCellToNorth()
    {
        return this.connectedCells.get(NORTH);
    }

    public IMazeCell getCellToSouth()
    {
        return this.connectedCells.get(SOUTH);
    }

    public IMazeCell getCellToEast()
    {
        return this.connectedCells.get(EAST);
    }

    public IMazeCell getCellToWest()
    {
        return this.connectedCells.get(WEST);
    }

    public Boolean getNorthWall()
    {
        return this.getWall(NORTH);
    }

    public Boolean getSouthWall()
    {
        return this.getWall(SOUTH);
    }

    public Boolean getEastWall()
    {
        return this.getWall(EAST);
    }

    public Boolean getWestWall()
    {
        return this.getWall(WEST);
    }

    public Boolean demolishNorthWall()
    {
        Boolean result;

        if ((this.getCellToNorth() != null) && (this.walls.get(NORTH)))
        {
            this.setWall(NORTH, false);
            this.getCellToNorth().setWall(SOUTH, false);
            result = true;
        }
        else
        {
            result = false;
        }
        return result;
    }

    public Boolean demolishSouthWall()
    {
        Boolean result;

        if ((this.getCellToSouth() != null) && (this.walls.get(SOUTH)))
        {
            this.setWall(SOUTH, false);
            this.getCellToSouth().setWall(NORTH, false);
            result = true;
        }
        else
        {
            result = false;
        }
        return result;
    }

    public Boolean demolishEastWall()
    {
        Boolean result;

        if ((this.getCellToEast() != null) && (this.walls.get(EAST)))
        {
            this.setWall(EAST, false);
            this.getCellToEast().setWall(WEST, false);
            result = true;
        }
        else
        {
            result = false;
        }
        return result;
    }

    public Boolean demolishWestWall()
    {
        Boolean result;

        if ((this.getCellToWest() != null) && (this.walls.get(WEST)))
        {
            this.setWall(WEST, false);
            this.getCellToWest().setWall(EAST, false);
            result = true;
        }
        else
        {
            result = false;
        }
        return result;
    }

    @Override
    public Integer getMaximumNumberOfWalls()
    {
        return 4;
    }



}
