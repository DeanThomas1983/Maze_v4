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
public interface IMazeCell extends IObserver, ISubject
{
    public String getIdentity();
    public ArrayList<IMazeCell> getListOfIntactNeighbours();
    public Integer getNumberOfIntactWalls();
    public Integer getNumberOfWalls();
}
