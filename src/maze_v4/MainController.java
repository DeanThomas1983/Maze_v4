/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze_v4;

import maze_v4.Interfaces.IMazeStructure;
import maze_v4.SquareMaze.SquareMazeStructure;
import maze_v4.ViewComponents.MazeTestForm;

/**
 *
 * @author dean
 */
public class MainController
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        DataModel dataModel;
        MazeTestForm mazeTestForm;
    
        dataModel = DataModel.getInstance();
        
        mazeTestForm = new MazeTestForm();
        mazeTestForm.setVisible(true);
    }
}
