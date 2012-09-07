/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze_v4.Generics;

import java.util.ArrayList;

/**
 *
 * @author dean
 */
public class WallList extends ArrayList<Wall>
{
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
}
