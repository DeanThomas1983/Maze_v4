/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze_v4;

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

    public IMazeCell getOwner()
    {
        return owner;
    }
    
    public Boolean getBlocked()
    {
        return blocked;
    }

    public void setBlocked(Boolean blocked)
    {
        this.blocked = blocked;
    }

    public Wall(IMazeCell owner,
                IMazeCell connectedCell)
    {
        this.owner = owner;
        this.connectedCell = connectedCell;
        this.blocked = true;
    }

    public IMazeCell getConnectedCell()
    {
        return this.connectedCell;
    }
    
    public void setConnectedCell(IMazeCell mazeCell)
    {
        this.connectedCell = mazeCell;
    }
}
