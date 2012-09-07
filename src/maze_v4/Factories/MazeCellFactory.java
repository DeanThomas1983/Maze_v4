/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze_v4.Factories;

import maze_v4.Enums.EnumMazeCellType;
import maze_v4.Interfaces.IMazeCell;
import maze_v4.SquareMaze.SquareMazeCell;

/**
 *
 * @author dean
 */
public class MazeCellFactory
{
    public static IMazeCell getMazeCell(EnumMazeCellType type)
    {
        switch (type)
        {
            case SQUARE_MAZE_CELL: return new SquareMazeCell();
        }
        return null;
    }

    public static IMazeCell getMazeCell(EnumMazeCellType type, String identifier)
    {
        switch (type)
        {
            case SQUARE_MAZE_CELL: return new SquareMazeCell(identifier);
        }
        return null;
    }
}
