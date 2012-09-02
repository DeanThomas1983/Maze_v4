/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze_v4;

import MazeGenerator.MazeGenerator;
import maze_v4.Interfaces.IMazeStructure;
import maze_v4.SquareMaze.SquareMazeStructure;

/**
 *
 * @author dean
 */
public class DataModel
{
    private volatile static DataModel uniqueInstance;

    IMazeStructure mazeStructure;
    MazeGenerator mazeGenerator;

    public IMazeStructure getMazeStructure()
    {
        return mazeStructure;
    }

    public MazeGenerator getMazeGenerator()
    {
        return mazeGenerator;
    }

    private DataModel()
    {
        mazeStructure = new SquareMazeStructure(5,5);
        mazeGenerator = new MazeGenerator(mazeStructure);

    }

    public static DataModel getInstance()
    {
        if (uniqueInstance == null)
        {
            synchronized (DataModel.class)
            {
                if (uniqueInstance == null)
                {
                    uniqueInstance = new DataModel();
                }
            }
        }
        return uniqueInstance;
    }
}
