/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze_v4.Generics;

import maze_v4.Interfaces.IMazeCell;

/**
 *
 * @author dean
 */
public class Wall
{
    private IMazeCell owner;
    private IMazeCell connectedCell;
    private Boolean blocked;

    /**
     *  Accessor method to return the owner of the wall object
     *
     *  @author dean
     *  @since 1-9-2012
     *
     *  @return IMazeCell the cell which owns the wall
     */
    public IMazeCell getOwner()
    {
        return owner;
    }

    /**
     *  Accessor method to return if a wall is blocking or not
     *
     *  @author dean
     *  @since 1-9-2012
     *
     *  @return true if the wall is blocking
     */
    public Boolean getBlocked()
    {
        return blocked;
    }

    /**
     *  Setter method to allow us to set if a wall is blocking or not
     *
     *  @author dean
     *  @since 1-9-2012
     *
     *  @param blocked should be true if the wall is blocking
     */
    public void setBlocked(Boolean blocked)
    {
        this.blocked = blocked;
    }

    /**
     *  Constructor
     *
     *  @author dean
     *  @since 1-9-2012
     *
     *  @param owner the cell which will own the wall
     *  @param connectedCell the cell which should reside behind the wall
     */
    public Wall(IMazeCell owner,
                IMazeCell connectedCell)
    {
        this.owner = owner;
        this.connectedCell = connectedCell;
        this.blocked = true;
    }

    /**
     *  Accessor method to return the cell behind the wall (if any)
     *
     *  @author dean
     *  @since 1-9-2012
     *
     *  @return IMazeCell the cell behind the wall
     */
    public IMazeCell getConnectedCell()
    {
        return this.connectedCell;
    }

    /**
     *  Setter method to allow us to add a neighboring cell behind this wall
     *
     *  @author dean
     *  @since 1-9-2012
     *
     *  @param mazeCell the cell to connect the wall to
     */
    public void setConnectedCell(IMazeCell mazeCell)
    {
        this.connectedCell = mazeCell;
    }

    public Wall(IMazeCell owner)
    {
        this.owner = owner;
        this.connectedCell = null;
        this.blocked = true;
    }
}
