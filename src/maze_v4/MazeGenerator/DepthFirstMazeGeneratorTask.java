/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze_v4.MazeGenerator;

import java.util.Random;
import java.util.Stack;
import java.util.concurrent.Callable;
import maze_v4.Interfaces.IMazeCell;
import maze_v4.Interfaces.IMazeGeneratorTask;
import maze_v4.Interfaces.IMazeStructure;

/**
 *
 * @author dean
 */
public final class DepthFirstMazeGeneratorTask
implements IMazeGeneratorTask,
Callable<Void>
{
    Random random = new Random();
    IMazeStructure mazeStructure;

    /**
     *  Create a new task and specify the maze structure which it will work
     *  with
     *
     *  @author dean
     *  @since 2-9-2012
     *
     *  @param mazeStructure the MazeStructure in which the generated maze
     *                       will reside
     */
    public DepthFirstMazeGeneratorTask(IMazeStructure mazeStructure)
    {
        this.mazeStructure = mazeStructure;
    }

    /**
     *  Generate a new maze using the Depth First Algorithm
     *
     *  @author dean
     *  @since 2-9-2012
     */
    private void generateMaze()
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
            System.out.println("Current cell has " +
                    currentCell.getListedOfNeighboursWithAllWallsIntact().size()
                    + " neighbour with all walls intact");
            for (IMazeCell c : currentCell.getListedOfNeighboursWithAllWallsIntact())
            {
                System.out.println(c.getIdentity());
            }

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

                System.out.println("Moved back to " + currentCell.toString());
            }
        }

        this.mazeStructure.setExitCell(currentCell);
    }

    /**
     *  Choose a random cell from which to grow the maze from
     *
     *  @author dean
     *  @since 2-9-2012
     *
     *  @return a random cell with the maze structure
     */
    private IMazeCell chooseOriginCell()
    {
        return this.mazeStructure.getMazeCells().get(
                random.nextInt(this.mazeStructure.getMazeCells().size()));
    }

    /**
     *  Call the thread to begin generating a maze in the specified structure
     *  array
     *
     *  @author dean
     *  @since 2-9-2012
     *
     *  @return Void
     *
     *  @throws Exception
     */
    @Override
    public Void call() throws Exception
    {
        System.out.println("Generating maze");

        if (this.mazeStructure != null)
        {
            this.generateMaze();
        }
        return null;
    }

}
