/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze_v4.Interfaces;

import java.util.ArrayList;
import maze_v4.Generics.Wall;
import maze_v4.Generics.WallList;

/**
 *
 * @author dean
 */
public interface IMazeCell extends IObserver, ISubject
{
    public String getIdentity();
    public WallList getWalls();
    public int getWallCount();
    public int getNumberOfIntactWalls();
    public int getNumberOfAccessibleNeighbours();
    public int getTotalNumberOfNeighbours();
    public void addNeighbourCell(IMazeCell neighbour, int location);
    public ArrayList<IMazeCell> getListedOfNeighboursWithAllWallsIntact();
    public IMazeCell chooseRandomIntactNeighbourCell(ArrayList<IMazeCell> intactNeighbours);
}
