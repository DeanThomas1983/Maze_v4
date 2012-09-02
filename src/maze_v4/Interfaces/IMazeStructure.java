/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze_v4.Interfaces;

import java.util.ArrayList;

/**
 *
 * @author dean
 */
public interface IMazeStructure
{
    public ArrayList<IMazeCell> getMazeCells();
    public IMazeCell getOriginCell();
    public IMazeCell getExitCell();
    public void clear();
}
