/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze_v4.Generics;

import java.util.ArrayList;
import maze_v4.Interfaces.IMazeCell;

/**
 *
 * @author dean
 */
public class WallList extends ArrayList<Wall>
{
    IMazeCell mazeCell;

    public WallList(IMazeCell mazeCell)
    {
        this.mazeCell = mazeCell;
    }

    private WallList()
    {

    }

    @Override
    public boolean add(Wall e)
    {
        return super.add(e);
    }

    @Override
    public boolean contains(Object o)
    {
        Wall w = (Wall)o;

        for (Wall w1 : this)
        {
            if ((w1.getOwner() == w.getOwner())
                && (w1.getConnectedCell() == w.getConnectedCell()))
            {
                return true;
            }
        }

        return false;
    }

    public boolean replace(Integer i, Wall e)
    {
        boolean result;

        if (this.set(modCount, e) != null)
        {
            result = true;
        }
        else
        {
            result = false;
        }
        return result;
    }

    public void fillWithBlanks(Integer count)
    {
        for (int i = 0; i < count; i++)
        {
            Wall newWall = new Wall(this.mazeCell);
            this.add(newWall);
        }
    }
}
