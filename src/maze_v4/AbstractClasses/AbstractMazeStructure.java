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
public class AbstractMazeStructure 
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
    
}
