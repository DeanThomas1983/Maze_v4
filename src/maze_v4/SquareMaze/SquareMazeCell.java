/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze_v4.SquareMaze;

import java.awt.Point;
import java.util.ArrayList;
import maze_v4.AbstractClasses.AbstractMazeCell;
import maze_v4.Enums.EnumSquareMazeCellDirection;
import maze_v4.Interfaces.IMazeCell;

/**
 *
 * @author dean is a usless twat
 */
public final class SquareMazeCell
        extends AbstractMazeCell
        implements IMazeCell
{

    public SquareMazeCell()
    {
        this(new Point());
    }

    public SquareMazeCell(Point coordinates)
    {
        super();
        //  Create a new cell with no neighbours
        this.coordinates = coordinates;
    }

    public IMazeCell getCellToNorth()
    {
        return this.connectedCells.get(EnumSquareMazeCellDirection.NORTH.ordinal());
    }

    public IMazeCell getCellToSouth()
    {
        return this.connectedCells.get(EnumSquareMazeCellDirection.SOUTH.ordinal());
    }

    public IMazeCell getCellToEast()
    {
        return this.connectedCells.get(EnumSquareMazeCellDirection.EAST.ordinal());
    }

    public IMazeCell getCellToWest()
    {
        return this.connectedCells.get(EnumSquareMazeCellDirection.WEST.ordinal());
    }

    public Boolean getNorthWall()
    {
        return this.getWall(EnumSquareMazeCellDirection.NORTH.ordinal());
    }

    public Boolean getSouthWall()
    {
        return this.getWall(EnumSquareMazeCellDirection.SOUTH.ordinal());
    }

    public Boolean getEastWall()
    {
        return this.getWall(EnumSquareMazeCellDirection.EAST.ordinal());
    }

    public Boolean getWestWall()
    {
        return this.getWall(EnumSquareMazeCellDirection.WEST.ordinal());
    }

    @Override
    public IMazeCell chooseRandomCell(ArrayList<IMazeCell> potentialCells)
    {
        IMazeCell result;

        int r = random.nextInt(potentialCells.size());

        System.out.println("Selected cell with index: " + r);

        EnumSquareMazeCellDirection positionRelative = getPositionRelative(potentialCells.get(r));

        System.out.println("Next cell will be: " + potentialCells.get(r));

        System.out.println("this is " + positionRelative);

        switch(positionRelative)
        {
            case NORTH: demolishNorthWall();
                result = potentialCells.get(r);
                break;
            case EAST: demolishEastWall();
                result = potentialCells.get(r);
                break;
            case SOUTH: demolishSouthWall();
                result = potentialCells.get(r);
                break;
            case WEST: demolishWestWall();
                result = potentialCells.get(r);
                break;
            default: System.err.println("Could not pick a cell to move into");
                result = null;
                break;
        }

        return result;
    }

    private EnumSquareMazeCellDirection getPositionRelative(IMazeCell testCell)
    {
        EnumSquareMazeCellDirection result;

        if (this.coordinates.x < testCell.getCoordinates().x)
        {
            result = EnumSquareMazeCellDirection.EAST;
        }
        else
        {
            if (this.coordinates.x > testCell.getCoordinates().x)
            {
                result = EnumSquareMazeCellDirection.WEST;
            }
            else
            {
                if (this.coordinates.y < testCell.getCoordinates().y)
                {
                    result = EnumSquareMazeCellDirection.SOUTH;
                }
                else
                {
                    result = EnumSquareMazeCellDirection.NORTH;
                }
            }
        }


        return result;
    }

    public Boolean demolishNorthWall()
    {
        Boolean result;

        if (this.getCellToNorth() != null)
        {
            this.setWall(EnumSquareMazeCellDirection.NORTH.ordinal(), false);
            this.getCellToNorth().setWall(EnumSquareMazeCellDirection.SOUTH.ordinal(), false);
            result = true;
        }
        else
        {
            result = false;
        }
       return result;
    }

    public Boolean demolishSouthWall()
    {
        Boolean result;

        if (this.getCellToSouth() != null)
        {
            this.setWall(EnumSquareMazeCellDirection.SOUTH.ordinal(), false);
            this.getCellToSouth().setWall(EnumSquareMazeCellDirection.NORTH.ordinal(), false);
            result = true;
        }
        else
        {
            result = false;
        }
        return result;
    }

    public Boolean demolishEastWall()
    {
        Boolean result;

        if (this.getCellToEast() != null)
        {
            System.out.println("EAST = " + EnumSquareMazeCellDirection.EAST.ordinal());
            this.setWall(EnumSquareMazeCellDirection.EAST.ordinal(), false);

            System.out.println("WEST = " + EnumSquareMazeCellDirection.WEST.ordinal());
            this.getCellToEast().setWall(EnumSquareMazeCellDirection.WEST.ordinal(), false);

            System.out.println("Demolishing east wall");

            result = true;
        }
        else
        {
            result = false;
        }
        return result;
    }

    public Boolean demolishWestWall()
    {
        Boolean result;

        if (this.getCellToWest() != null)
        {
            this.setWall(EnumSquareMazeCellDirection.WEST.ordinal(), false);
            this.getCellToWest().setWall(EnumSquareMazeCellDirection.EAST.ordinal(), false);

            System.out.println("Demolishing west wall");

            result = true;
        }
        else
        {
            result = false;
        }
        return result;
    }

    @Override
    public Integer getMaximumNumberOfWalls()
    {
        return 4;
    }



}
