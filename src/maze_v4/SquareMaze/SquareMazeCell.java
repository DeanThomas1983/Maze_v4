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

    public static int NUMBER_OF_WALLS = 4;

    public static int NORTH = 0;
    public static int EAST = 1;
    public static int SOUTH = 2;
    public static int WEST = 3;

    public static int getOppositeDirection(int direction)
    {
        int result = direction + 2;
        if (result > (NUMBER_OF_WALLS - 1))
        {
            result -= NUMBER_OF_WALLS;
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
        return this.walls.get(NORTH);
    }

    public Boolean getSouthWall()
    {
        return this.walls.get(SOUTH);
    }

    public Boolean getEastWall()
    {
        return this.walls.get(EAST);
    }

    public Boolean getWestWall()
    {
        return this.walls.get(WEST);
    }

    public Boolean demolishNorthWall()
    {
        if ((this.getCellToNorth() != null) && (this.walls.get(NORTH)))
        {
            this.getNorthWall() = false;
            this.getCellToNorth().get
        }
    }
}
