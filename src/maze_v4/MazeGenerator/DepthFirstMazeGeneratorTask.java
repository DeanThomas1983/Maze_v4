/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze_v4.MazeGenerator;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import maze_v4.Generics.Wall;
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
    Integer pauseBetweenSteps = 0;

    /**
     * Create a new task and specify the maze structure which it will work with
     *
     * @author dean
     * @since 2-9-2012
     *
     * @param mazeStructure the MazeStructure in which the generated maze will
     *                      reside
     */
    public DepthFirstMazeGeneratorTask(IMazeStructure mazeStructure,
                                       Integer pauseBetweenSteps)
    {
        this.mazeStructure = mazeStructure;
        this.pauseBetweenSteps = pauseBetweenSteps;
    }

    /**
     * Generate a new maze using the Depth First Algorithm
     *
     * @author dean
     * @since 2-9-2012
     */
    private void generateMaze()
    {
        System.out.println();
        System.out.println("Generating maze");
        System.out.println("===============");

        Stack<IMazeCell> cellStack = new Stack<IMazeCell>();
        int visitedCells = 0;

        IMazeCell currentCell = chooseOriginCell();
        mazeStructure.setOriginCell(currentCell);

        cellStack.push(currentCell);
        visitedCells++;
        System.out.println("Current cell is: " + currentCell.toString());

        while (visitedCells < this.mazeStructure.getMazeCells().size())
        {
            System.out.println("Neighbours with all walls intact: ");

            ArrayList<IMazeCell> intactNeighbours =
                    currentCell.getListedOfNeighboursWithAllWallsIntact();

            for (IMazeCell c : intactNeighbours)
            {
                System.out.println(c.getIdentity());
            }

            if (!intactNeighbours.isEmpty())
            {
                IMazeCell nextCell =
                        currentCell.chooseRandomIntactNeighbourCell(intactNeighbours);

                System.out.println("Next cell will be: " + nextCell.getIdentity());

                Wall wallToDemolish = currentCell.getWalls().findWall(nextCell);

                if (wallToDemolish != null)
                {
                    System.out.println("Found a wall to demolish");
                }

                wallToDemolish.setBlocked(false);

                currentCell = nextCell;

                System.out.println("Current cell is " + currentCell.toString());
                System.out.println();

                cellStack.push(currentCell);

                visitedCells++;
            }
            else
            {
                currentCell = cellStack.pop();

                System.out.println("Moved back to " + currentCell.toString());
                System.out.println();
            }


            try
            {
                Thread.sleep(pauseBetweenSteps);
            }
            catch (InterruptedException ex)
            {
                Logger.getLogger(DepthFirstMazeGeneratorTask.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        this.mazeStructure.setExitCell(currentCell);
    }

    /**
     * Choose a random cell from which to grow the maze from
     *
     * @author dean
     * @since 2-9-2012
     *
     * @return a random cell with the maze structure
     */
    private IMazeCell chooseOriginCell()
    {
        return this.mazeStructure.getMazeCells().get(
                random.nextInt(this.mazeStructure.getMazeCells().size()));
    }

    /**
     * Call the thread to begin generating a maze in the specified structure
     * array
     *
     * @author dean
     * @since 2-9-2012
     *
     * @return Void
     *
     * @throws Exception
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
