/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MazeGenerator;

import java.util.Random;
import java.util.Stack;
import maze_v4.AbstractClasses.AbstractMazeGenerator;
import maze_v4.Interfaces.IMazeCell;
import maze_v4.Interfaces.IMazeGenerationAlgorithm;
import maze_v4.Interfaces.IMazeStructure;

/**
 *
 * @author dean
 */
public final class DepthFirstMazeGenerator 
extends AbstractMazeGenerator
implements IMazeGenerationAlgorithm
{
    public DepthFirstMazeGenerator(IMazeStructure mazeStructure)
    {
        super(mazeStructure);
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
        System.out.println("Current cell is " + currentCell.toString());
        
        while (visitedCells < this.mazeStructure.getMazeCells().size())
        {
            if (currentCell.getListedOfNeighboursWithAllWallsIntact().size() > 0)
            {
                currentCell = currentCell.getRandomNeighbourCell();
                
                System.out.println("Current cell is " + currentCell.toString());
        
                cellStack.push(currentCell);
                
                visitedCells++;
            }
            else
            {
                currentCell = cellStack.pop();
            }
        }
        
        this.mazeStructure.setExitCell(currentCell);
    }

    
}
