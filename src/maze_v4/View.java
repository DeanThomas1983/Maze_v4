/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze_v4;

import java.util.ArrayList;
import maze_v4.Interfaces.IObserver;
import maze_v4.Interfaces.ISubject;
import maze_v4.ViewComponents.MazeTestForm;

/**
 *
 * @author dean
 */
public final class View
        implements IObserver, ISubject
{

    private volatile static View uniqueInstance;
    MazeTestForm mazeTestForm;
    ArrayList<IObserver> observers = new ArrayList<IObserver>();

    private View()
    {
        mazeTestForm = new MazeTestForm();
        this.registerObserver(mazeTestForm);
        mazeTestForm.setVisible(true);
    }

    public static View getInstance()
    {
        if (uniqueInstance == null)
        {
            synchronized (DataModel.class)
            {
                if (uniqueInstance == null)
                {
                    uniqueInstance = new View();
                }
            }
        }
        return uniqueInstance;
    }

    @Override
    public void update()
    {
        if (DebugVariables.SHOW_OBSERVER_INFORMATION)
        {
            System.out.println(this.getClass().getSimpleName() + " updated");
        }
        this.notifyObservers();
    }

    @Override
    public void registerObserver(IObserver o)
    {
        if (!this.observers.contains(o))
        {
            this.observers.add(o);

            if (DebugVariables.SHOW_OBSERVER_INFORMATION)
            {
                System.out.println(this.getClass().getSimpleName()
                        + " has a new observer: " + o.getClass().getSimpleName());
            }
        }
    }

    @Override
    public void removeObserver(IObserver o)
    {
        if (this.observers.contains(o))
        {
            this.observers.remove(o);

            if (DebugVariables.SHOW_OBSERVER_INFORMATION)
            {
                System.out.println(this.getClass().getSimpleName()
                        + " deregistered an observer: " + o.getClass().getSimpleName());
            }
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
