package maze_v4.SquareMaze;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
import java.awt.Point;
import maze_v4.AbstractClasses.AbstractMazeStructure;
import maze_v4.DebugVariables;
import maze_v4.Enums.EnumMazeCellType;
import maze_v4.Factories.MazeCellFactory;
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
        this(3, 3);
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
        setupMaze();
    }

    private void setupMaze()
    {
        this.mazeCells.clear();

        if (DebugVariables.GRID_MAPPING_INFORMATION)
        {
            System.err.println("Laying out basic maze structure");
        }

        for (int row = 0; row < this.height; row++)
        {
            for (int col = 0; col < this.width; col++)
            {
                IMazeCell cellToNorth;
                IMazeCell cellToWest;

                if (DebugVariables.GRID_MAPPING_INFORMATION)
                {
                    System.out.println("Generating cell " + col + "," + row);
                }

                IMazeCell mazeCell = MazeCellFactory.getMazeCell(
                        EnumMazeCellType.SQUARE_MAZE_CELL,
                        "[" + col + "," + row + "]");

                mazeCell.registerObserver(this);

                this.mazeCells.add(mazeCell);

                System.out.println();
            }
        }

        if (DebugVariables.GRID_MAPPING_INFORMATION)
        {
            System.out.println("Maze generated with: " + this.getMazeCells().size()
                    + " cells");

            System.err.println("Connecting cells");
        }

        connectCells();
    }

    private void connectCells()
    {
        for (int row = 0; row < this.height; row++)
        {
            for (int col = 0; col < this.width; col++)
            {
                if (row > 0)
                {
                    IMazeCell originCell = this.mazeCells.get(indexOfCell(col,row));
                    IMazeCell cellToNorth = this.mazeCells.get(indexOfCellToNorth(col,row));
                    originCell.setNeighbour(SquareMazeCell.NORTH, cellToNorth);

                    System.out.println(originCell + " north neighbour is " + cellToNorth);
                }
            }
        }
    }

    private Integer indexOfCell(Integer col, Integer row)
    {
        return col + (row * width);
    }

    private Integer indexOfCellToNorth(Integer col, Integer row)
    {
        return col + ((row - 1) * height);
    }

    private Integer indexOfCellToWest(Integer col, Integer row)
    {
        return (col - 1) + (row * height);
    }
}
