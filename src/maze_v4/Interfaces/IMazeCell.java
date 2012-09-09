/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze_v4.Interfaces;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author dean
 */
public interface IMazeCell extends IObserver, ISubject
{
    public Point getCoordinates();
    public ArrayList<IMazeCell> getListOfIntactNeighbours();
    public Integer getNumberOfIntactWalls();
    public Integer getNumberOfWalls();
    public Boolean getWall(Integer wall);
    public void setWall(Integer wall, Boolean value);
    public IMazeCell getNeighbour(Integer index);
    public void setNeighbour(Integer index, IMazeCell neighbour);
    public IMazeCell chooseRandomCell(ArrayList<IMazeCell> potentialCells);
}
