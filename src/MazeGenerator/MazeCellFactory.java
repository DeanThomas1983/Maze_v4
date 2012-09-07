/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MazeGenerator;

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
}
