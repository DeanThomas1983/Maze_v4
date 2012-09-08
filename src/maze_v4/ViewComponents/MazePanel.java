/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze_v4.ViewComponents;

import java.awt.Graphics;
import java.util.ArrayList;
import maze_v4.DataModel;
import maze_v4.DebugVariables;
import maze_v4.Interfaces.IMazeRenderer;
import maze_v4.Interfaces.IObserver;
import maze_v4.Interfaces.ISubject;
import maze_v4.Renderers.SquareMazeRenderer;
import maze_v4.SquareMaze.SquareMazeStructure;

/**
 *
 * @author dean
 */
public class MazePanel extends javax.swing.JPanel
        implements IObserver, ISubject
{

    ArrayList<IObserver> observers = new ArrayList<IObserver>();
    Boolean isDonePainting;
    IMazeRenderer mazeRenderer;

    /**
     * Creates new form MazePanel
     */
    public MazePanel()
    {
        initComponents();


        //  Replace with factory...
        this.mazeRenderer = new SquareMazeRenderer();
        //this.registerObserver(mazeRenderer);

    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        System.out.println("MazePanel requesting new image");

        g.drawImage(mazeRenderer.drawMaze(), 0, 0, this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        setBackground(new java.awt.Color(0, 0, 0));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    @Override
    public void update()
    {
        if (DebugVariables.SHOW_OBSERVER_INFORMATION)
        {
            System.out.println(this.getClass().getSimpleName() + " updated");
        }
        this.repaint();

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
