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
        this.walls.fillWithBlanks(NUMBER_OF_WALLS);
    }


}
