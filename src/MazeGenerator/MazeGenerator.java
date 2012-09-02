/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MazeGenerator;

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

    public MazeGenerator(IMazeStructure mazeStructure)
    {
        this.mazeStructure = mazeStructure;

        this.executor.setKeepAliveTime(10, TimeUnit.SECONDS);
        this.executor.allowCoreThreadTimeOut(true);
        this.executorService =
                Executors.unconfigurableScheduledExecutorService(executor);
    }

    public void generateMaze()
    {
        DepthFirstMazeGeneratorTask mazeGeneratorTask
                = new DepthFirstMazeGeneratorTask(this.mazeStructure);
        this.executorService.schedule(
                mazeGeneratorTask, 1, TimeUnit.MILLISECONDS);

    }



}
