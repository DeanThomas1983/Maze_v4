/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze_v4;

import maze_v4.Factories.MazeStructureFactory;
import maze_v4.Enums.EnumMazeStructureType;
import maze_v4.MazeGenerator.MazeGenerator;
import java.util.ArrayList;
import maze_v4.Interfaces.IMazeStructure;
import maze_v4.Interfaces.IObserver;
import maze_v4.Interfaces.ISubject;
import maze_v4.SquareMaze.SquareMazeStructure;

/**
 *
 * @author dean
 */
public class DataModel
implements ISubject,
IObserver
{

    private volatile static DataModel uniqueInstance;
    ArrayList<IObserver> observers = new ArrayList<IObserver>();
    IMazeStructure mazeStructure;
    MazeGenerator mazeGenerator;

    /**
     * Accessor method for the MazeStructure
     *
     * @author dean
     * @since 1-9-2012
     *
     * @return MazeStructure the structure used to hold the maze
     *
     */
    public IMazeStructure getMazeStructure()
    {
        return mazeStructure;
    }

    /**
     * Accessor method for the MazeGenerator
     *
     * @author dean
     * @since 1-9-2012
     *
     * @return MazeGenerator the maze generator used to schedule generation
     * events
     */
    public MazeGenerator getMazeGenerator()
    {
        return mazeGenerator;
    }

    /**
     * As the class is a singleton any calls to the constructor are handled by
     * the method itself
     *
     * @author dean
     * @since 1-9-2012
     */
    private DataModel()
    {
        mazeStructure = MazeStructureFactory.getMazeStructure(
                EnumMazeStructureType.SQUARE_MAZE);
        mazeStructure.registerObserver(this);

        mazeGenerator = new MazeGenerator(mazeStructure);

    }

    /**
     * If the class has already been instantiated return that instance of the
     * data model otherwise call the Private constructor to create a new
     * instance
     *
     * @author dean
     * @since 1-9-2012
     *
     * @return DataModel - the data model
     */
    public static DataModel getInstance()
    {
        if (uniqueInstance == null)
        {
            synchronized (DataModel.class)
            {
                if (uniqueInstance == null)
                {
                    uniqueInstance = new DataModel();
                }
            }
        }
        return uniqueInstance;
    }

    @Override
    public void update()
    {
        System.out.println(this.getClass().getSimpleName() + " updated");

        this.notifyObservers();
    }

    @Override
    public void registerObserver(IObserver o)
    {
        if (!this.observers.contains(o))
        {
            this.observers.add(o);

            System.out.println(this.getClass().getSimpleName()
                + " has a new observer: " + o.getClass().getSimpleName());
        }
    }

    @Override
    public void removeObserver(IObserver o)
    {
        if (this.observers.contains(o))
        {
            this.observers.remove(o);

            System.out.println(this.getClass().getSimpleName()
                + " deregistered an observer: " + o.getClass().getSimpleName());
        }
    }

    @Override
    public void notifyObservers()
    {
        for (IObserver o : this.observers)
        {
            o.update();
        }
    }
}
