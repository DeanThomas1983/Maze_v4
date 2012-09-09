package maze_v4.SquareMaze;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
import java.awt.Point;
import maze_v4.AbstractClasses.AbstractMazeStructure;
import maze_v4.DebugVariables;
import maze_v4.Enums.EnumMazeCellType;
import maze_v4.Enums.EnumSquareMazeCellDirection;
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

        for (int y = 0; y < this.height; y++)
        {
            for (int x = 0; x < this.width; x++)
            {
                IMazeCell cellToNorth;
                IMazeCell cellToWest;

                if (DebugVariables.GRID_MAPPING_INFORMATION)
                {
                    System.out.println("Generating cell " + x + "," + y);
                }

                IMazeCell mazeCell = MazeCellFactory.getMazeCell(
                        EnumMazeCellType.SQUARE_MAZE_CELL,new Point(x, y));

                mazeCell.registerObserver(this);

                this.mazeCells.add(mazeCell);

                System.out.println();
            }
        }

        if (DebugVariables.GRID_MAPPING_INFORMATION)
        {
            System.out.println("Maze generated with: " + this.getMazeCells().size()
                    + " cells");

            System.out.println("Connecting cells");
        }

        connectCells();
    }

    private void connectCells()
    {
        for (int y = 0; y < this.height; y++)
        {
            for (int x = 0; x < this.width; x++)
            {
                IMazeCell originCell = this.mazeCells.get(indexOfCell(x, y));

                if (y > 0)
                {
                    //  Setup a connection with the cell to the north
                    IMazeCell cellToNorth = this.mazeCells.get(indexOfCellToNorth(x, y));
                    originCell.setNeighbour(EnumSquareMazeCellDirection.NORTH.ordinal(), cellToNorth);

                    if (DebugVariables.GRID_MAPPING_INFORMATION)
                    {
                        System.out.println(originCell + " north neighbour is " + cellToNorth);
                    }
                }

                if (y < this.height - 1)
                {
                    //  Setup a connection with the cell to the south
                    IMazeCell cellToSouth = this.mazeCells.get(indexOfCellToSouth(x, y));
                    originCell.setNeighbour(EnumSquareMazeCellDirection.SOUTH.ordinal(), cellToSouth);

                    if (DebugVariables.GRID_MAPPING_INFORMATION)
                    {
                        System.out.println(originCell + " south neighbour is " + cellToSouth);
                    }
                }

                if (x > 0)
                {
                    //  Setup connection with the cell to the west
                    IMazeCell cellToWest = this.mazeCells.get(indexOfCellToWest(x, y));
                    originCell.setNeighbour(EnumSquareMazeCellDirection.WEST.ordinal(), cellToWest);

                    if (DebugVariables.GRID_MAPPING_INFORMATION)
                    {
                        System.out.println(originCell + " west neighbour is " + cellToWest);
                    }
                }

                if (x < this.width - 1)
                {
                    //  Setup connection with the cell to the east
                    IMazeCell cellToEast = this.mazeCells.get(indexOfCellToEast(x, y));
                    originCell.setNeighbour(EnumSquareMazeCellDirection.EAST.ordinal(), cellToEast);

                    if (DebugVariables.GRID_MAPPING_INFORMATION)
                    {
                        System.out.println(originCell + " east neighbour is " + cellToEast);
                    }
                }

                if (DebugVariables.GRID_MAPPING_INFORMATION)
                {
                    System.out.println();
                }
            }
        }
    }

    private Integer indexOfCell(Integer x, Integer y)
    {
        return x + (y * width);
    }

    private Integer indexOfCellToNorth(Integer x, Integer y)
    {
        return x + ((y - 1) * width);
    }

    private Integer indexOfCellToWest(Integer x, Integer y)
    {
        return (x - 1) + (y * width);
    }

    private Integer indexOfCellToEast(Integer x, Integer y)
    {
        return (x + 1) + (y * width);
    }

    private Integer indexOfCellToSouth(Integer x, Integer y)
    {
        return x + ((y + 1) * width);
    }
}
