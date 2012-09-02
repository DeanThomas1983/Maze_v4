/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze_v4.SquareMaze;

import maze_v4.AbstractClasses.AbstractMazeCell;
import maze_v4.Interfaces.IMazeCell;

/**
 *
 * @author dean
 */
public final class SquareMazeCell
        extends AbstractMazeCell
        implements IMazeCell
{
    public static int NORTH = 0;
    public static int EAST = 1;
    public static int SOUTH = 2;
    public static int WEST = 3;
    
    public SquareMazeCell()
    {
        //  Create a new cell with no neighbours
        this(null, null, null, null);
    }

    public SquareMazeCell(IMazeCell cellToNorth,
                          IMazeCell cellToEast,
                          IMazeCell cellToSouth,
                          IMazeCell cellToWest)
    {
        this.addNeighbourCell(cellToNorth);
        this.addNeighbourCell(cellToEast);
        this.addNeighbourCell(cellToSouth);
        this.addNeighbourCell(cellToWest);

        System.out.println("New cell created with "
                + this.getNumberOfIntactWalls() + " intact walls");
        System.out.println("Number of neighbours: "
                + this.getTotalNumberOfNeighbours());
        System.out.println("Number of directly accessible neighbours: "
                + this.getNumberOfAccessibleNeighbours());
        System.out.println();
    }
}
