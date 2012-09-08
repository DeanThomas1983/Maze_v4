package maze_v4.SquareMaze;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
import maze_v4.AbstractClasses.AbstractMazeStructure;
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
        buildMaze();
    }

    private void buildMaze()
    {
        this.mazeCells.clear();

        for (int row = 0; row < this.height; row++)
        {
            for (int col = 0; col < this.width; col++)
            {
                IMazeCell cellToNorth = null;
                IMazeCell cellToWest = null;

                System.out.println("Generating cell " + col + "," + row);

                IMazeCell mazeCell = MazeCellFactory.getMazeCell(
                        EnumMazeCellType.SQUARE_MAZE_CELL,
                        "[" + col + "," + row + "]");

                if (row > 0)
                {
                    cellToNorth = this.mazeCells.get(indexOfCellToNorth(col, row));
                    System.out.println("Cell to north is: "
                            + indexOfCellToNorth(col, row) + " - "
                            + cellToNorth.getIdentity());

                    //mazeCell.addNeighbourCell(cellToNorth, SquareMazeCell.NORTH);

                }

                if (col > 0)
                {
                    cellToWest = this.mazeCells.get(indexOfCellToWest(col, row));
                    System.out.println("Cell to west is: "
                            + indexOfCellToWest(col, row) + " - "
                            + cellToWest.getIdentity());

                    //mazeCell.addNeighbourCell(cellToWest, SquareMazeCell.WEST);
                }



                mazeCell.registerObserver(this);

                this.mazeCells.add(mazeCell);

                System.out.println();
            }
        }

        System.out.println("Maze generated with: " + this.getMazeCells().size()
                + " cells");
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
