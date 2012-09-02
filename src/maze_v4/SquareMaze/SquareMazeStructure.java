package maze_v4.SquareMaze;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import maze_v4.SquareMaze.SquareMazeCell;
import maze_v4.AbstractClasses.AbstractMazeStructure;
import maze_v4.Interfaces.IMazeCell;
import maze_v4.Interfaces.IMazeStructure;

/**
 *
 * @author dean
 */
public final class SquareMazeStructure
extends AbstractMazeStructure
implements IMazeStructure
{
    private int width;
    private int height;
    
    public SquareMazeStructure()
    {
        
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }
    
    public SquareMazeStructure(int width, int height)
    {
        this.width = width;
        this.height = height;
        buildMaze();
    }
    
    private void buildMaze()
    {
        this.mazeCells.clear();
        
        for (int row = 0; row < this.width; row++)
        {
            for (int col = 0; col < this.height; col++)
            {
                IMazeCell cellToNorth = null;
                IMazeCell cellToWest = null;
                
                if (row > 0)
                {
                    cellToNorth = this.mazeCells.get(col + ((row-1) * height));
                }
                
                if (col > 0)
                {
                    cellToWest = this.mazeCells.get((col-1) + (row * height));
                }
                
                IMazeCell mazeCell = new SquareMazeCell("[" + col + ","
                                                        + row + "]",
                                                        cellToNorth,
                                                        null,
                                                        null,
                                                        cellToWest);
                
                this.mazeCells.add(mazeCell);
            }
        }
        
        System.out.println("Maze generated with: " + this.getMazeCells().size()
                + " cells");
    }
}
