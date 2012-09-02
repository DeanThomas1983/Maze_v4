/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze_v4.AbstractClasses;

import java.util.Random;
import maze_v4.Interfaces.IMazeCell;
import maze_v4.Interfaces.IMazeGenerationAlgorithm;
import maze_v4.Interfaces.IMazeStructure;

/**
 *
 * @author dean
 */
public class AbstractMazeGenerator implements IMazeGenerationAlgorithm
{
    protected Random random = new Random();
    protected IMazeStructure mazeStructure;
    
    public AbstractMazeGenerator(IMazeStructure mazeStructure)
    {
        this.mazeStructure = mazeStructure;
    }

    @Override
    public void generateMaze()
    {
        
    }
    
    protected IMazeCell chooseOriginCell()
    {
        return this.mazeStructure.getMazeCells().get(
                random.nextInt(this.mazeStructure.getMazeCells().size()));
    }
}
