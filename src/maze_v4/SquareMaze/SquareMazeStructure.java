package maze_v4.SquareMaze;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
import maze_v4.Enums.EnumMazeCellType;
import maze_v4.Factories.MazeCellFactory;
import maze_v4.AbstractClasses.AbstractMazeStructure;
import maze_v4.DataModel;
import maze_v4.Interfaces.IMazeCell;
import maze_v4.Interfaces.IMazeStructure;
import maze_v4.Interfaces.IObserver;
import maze_v4.Interfaces.ISubject;

/**
 *
 * @author dean
 */
public final class SquareMazeStructure
        extends AbstractMazeStructure
implements IObserver, ISubject, IMazeStructure
{

    private int width;
    private int height;

    public SquareMazeStructure()
    {
        this(5, 5);
    }

    @Override
    public int getWidth()
    {
        return width;
    }

    @Override
    public int getHeight()
    {
        return height;
    }

    public SquareMazeStructure(int width, int height)
    {
        super();

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
                    cellToNorth = this.mazeCells.get(col + ((row - 1) * height));
                }

                if (col > 0)
                {
                    cellToWest = this.mazeCells.get((col - 1) + (row * height));
                }

                IMazeCell mazeCell = MazeCellFactory.getMazeCell(EnumMazeCellType.SQUARE_MAZE_CELL);

                mazeCell.addNeighbourCell(cellToNorth, SquareMazeCell.NORTH);
                mazeCell.addNeighbourCell(cellToWest, SquareMazeCell.WEST);

                mazeCell.registerObserver(this);

                this.mazeCells.add(mazeCell);

            }
        }

        System.out.println("Maze generated with: " + this.getMazeCells().size()
                + " cells");
    }



}
