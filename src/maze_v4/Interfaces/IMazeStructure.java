/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze_v4.Interfaces;

import java.util.ArrayList;
import maze_v4.DataModel;

/**
 *
 * @author dean
 */
public interface IMazeStructure extends ISubject, IObserver
{
    public ArrayList<IMazeCell> getMazeCells();
    public IMazeCell getOriginCell();
    public void setOriginCell(IMazeCell origin);
    public IMazeCell getExitCell();
    public void setExitCell(IMazeCell exit);
    public void clear();
    public int getWidth();
    public int getHeight();

}
