/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze_v4.Interfaces;

import java.util.ArrayList;
import maze_v4.Wall;

/**
 *
 * @author dean
 */
public interface IMazeCell
{
    public String getIdentity();
    public ArrayList<Wall> getWalls();
    public int getWallCount();
    public int getNumberOfIntactWalls();
    public int getNumberOfAccessibleNeighbours();
    public int getTotalNumberOfNeighbours();
    public void addNeighbourCell(IMazeCell neighbour, int location);
    public ArrayList<IMazeCell> getListedOfNeighboursWithAllWallsIntact();
    public IMazeCell getRandomNeighbourCell();
}
