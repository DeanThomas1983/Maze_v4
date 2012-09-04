/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze_v4.Interfaces;

/**
 *
 * @author dean
 */
public interface ISubject
{
    public void registerObserver(IObserver o);
    public void removeObserver(IObserver o);
    public void notifyObservers();
}
