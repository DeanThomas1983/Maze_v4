/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze_v4;

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
        View view;

        dataModel = DataModel.getInstance();
        view = View.getInstance();

        dataModel.registerObserver(view);
    }
}
