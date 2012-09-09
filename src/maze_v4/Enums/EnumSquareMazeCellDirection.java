/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze_v4.Enums;

import java.util.EnumSet;

/**
 *
 * @author dean
 */
public enum EnumSquareMazeCellDirection
{

    NORTH,
    EAST,
    SOUTH,
    WEST;

    private static final int amount =
            EnumSet.allOf(EnumSquareMazeCellDirection.class).size();
    private static EnumSquareMazeCellDirection[] val = new EnumSquareMazeCellDirection[amount];

    static
    {
        for (EnumSquareMazeCellDirection mcd : EnumSet.allOf(EnumSquareMazeCellDirection.class))
        {
            val[mcd.ordinal()] = mcd;
        }
    }

    public static EnumSquareMazeCellDirection fromInt(int i)
    {
        return val[i];
    }

    public EnumSquareMazeCellDirection next()
    {
        return fromInt((ordinal() + 1) % amount);
    }

    public EnumSquareMazeCellDirection opposite()
    {
        return fromInt((ordinal() + 2) % amount);
    }
}
