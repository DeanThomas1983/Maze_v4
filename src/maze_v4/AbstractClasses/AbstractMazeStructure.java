/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze_v4.AbstractClasses;

import java.util.ArrayList;
import maze_v4.Interfaces.IMazeCell;
import maze_v4.Interfaces.IMazeStructure;

/**
 *
 * @author dean
 */
public abstract class AbstractMazeStructure
implements IMazeStructure
{
    IMazeCell originCell;
    IMazeCell exitCell;

    protected ArrayList<IMazeCell> mazeCells = new ArrayList<IMazeCell>();

    @Override
    public ArrayList<IMazeCell> getMazeCells()
    {
        return mazeCells;
    }

    @Override
    public IMazeCell getOriginCell()
    {
        return originCell;
    }

    @Override
    public IMazeCell getExitCell()
    {
        return exitCell;
    }

    @Override
    public void clear()
    {
        this.mazeCells.clear();
    }

    @Override
    public void setOriginCell(IMazeCell origin)
    {
        this.originCell = origin;
    }

    @Override
    public void setExitCell(IMazeCell exit)
    {
        this.exitCell = exit;
    }

    @Override
    abstract public int getWidth();

    @Override
    abstract public int getHeight();
}
