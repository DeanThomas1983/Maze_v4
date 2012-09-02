/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MazeGenerator;

import java.util.Random;
import java.util.Stack;
import maze_v4.Interfaces.IMazeCell;
import maze_v4.Interfaces.IMazeGenerationAlgorithm;
import maze_v4.Interfaces.IMazeStructure;

/**
 *
 * @author dean
 */
public class DepthFirstMazeGenerator implements IMazeGenerationAlgorithm
{
    private Random random = new Random();
    IMazeStructure mazeStructure;
    
    public DepthFirstMazeGenerator(IMazeStructure mazeStructure)
    {
        this.mazeStructure = mazeStructure;
    }

    @Override
    public void generateMaze()
    {
        Stack<IMazeCell> cellStack = new Stack<IMazeCell>();
        int visitedCells = 0;
        
        IMazeCell currentCell = chooseOriginCell();
        mazeStructure.setOriginCell(currentCell);
        
        cellStack.push(currentCell);
        visitedCells++;
        
        while (visitedCells < this.mazeStructure.getMazeCells().size())
        {
            
        }
        
    }

    private IMazeCell chooseOriginCell()
    {
        return this.mazeStructure.getMazeCells().get(
                random.nextInt(this.mazeStructure.getMazeCells().size()));
    }
}
