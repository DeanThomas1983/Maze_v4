/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze_v4;

import MazeGenerator.EnumMazeStructureType;
import maze_v4.Interfaces.IMazeStructure;
import maze_v4.SquareMaze.SquareMazeStructure;

/**
 *
 * @author dean
 */
public class MazeStructureFactory
{
    public static IMazeStructure getMazeStructure(EnumMazeStructureType type)
    {
        switch (type)
        {
            case SQUARE_MAZE: return new SquareMazeStructure();
        }

        return null;
    }

}
