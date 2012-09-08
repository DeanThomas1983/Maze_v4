/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze_v4.MazeGenerator;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import maze_v4.Interfaces.IMazeStructure;

/**
 *
 * @author dean
 */
public class MazeGenerator
{
    protected Random random = new Random();
    protected IMazeStructure mazeStructure;
    protected ScheduledThreadPoolExecutor executor
            = new ScheduledThreadPoolExecutor(1);
    protected ScheduledExecutorService executorService;

    /**
     *  Constructor - create a MazeGenerator object which is capable of
     *  scheduling tasks to create mazes on a background thread
     *
     *  @param mazeStructure the MazeStructure array to work with
     */
    public MazeGenerator(IMazeStructure mazeStructure)
    {
        this.mazeStructure = mazeStructure;

        this.executor.setKeepAliveTime(10, TimeUnit.SECONDS);
        this.executor.allowCoreThreadTimeOut(true);
        this.executorService =
                Executors.unconfigurableScheduledExecutorService(executor);
    }

    /**
     *  Create a task to generate a new maze within the specified structure
     *  array.
     *
     *  @author dean
     *  @since 2-9-2012
     */
    public void generateMaze()
    {
        //  Create a new task
        DepthFirstMazeGeneratorTask mazeGeneratorTask
                = new DepthFirstMazeGeneratorTask(this.mazeStructure,5000);
        //  Schedule to start almost immediately
        this.executorService.schedule(
                mazeGeneratorTask, 1, TimeUnit.MILLISECONDS);
    }



}
