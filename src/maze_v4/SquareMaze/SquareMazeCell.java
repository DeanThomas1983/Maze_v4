/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze_v4.SquareMaze;

import maze_v4.AbstractClasses.AbstractMazeCell;
import maze_v4.Interfaces.IMazeCell;
import maze_v4.Wall;

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
    
   public SquareMazeCell(String identity)
    {
        //  Create a new cell with no neighbours
        this(identity, null, null, null, null);
    }
    
    public SquareMazeCell(String identity,
                          IMazeCell cellToNorth,
                          IMazeCell cellToEast,
                          IMazeCell cellToSouth,
                          IMazeCell cellToWest)
    {
        this.identity = identity;
        
        this.walls.add(new Wall(this,null));
        this.walls.add(new Wall(this,null));
        this.walls.add(new Wall(this,null));
        this.walls.add(new Wall(this,null));
        
        this.addNeighbourCell(cellToNorth,NORTH);
        this.addNeighbourCell(cellToEast,EAST);
        this.addNeighbourCell(cellToSouth,SOUTH);
        this.addNeighbourCell(cellToWest,WEST);

        //System.out.println("New cell " + identity + " created with "
        //        + this.getNumberOfIntactWalls() + " intact walls");
        //System.out.println("Number of neighbours: "
        //        + this.getTotalNumberOfNeighbours());
        //System.out.println("Number of directly accessible neighbours: "
        //        + this.getNumberOfAccessibleNeighbours());
        //System.out.println();
    }
}
