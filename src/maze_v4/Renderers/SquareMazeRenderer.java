/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze_v4.Renderers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import maze_v4.DataModel;
import maze_v4.Enums.EnumSquareMazeCellDirection;
import maze_v4.Interfaces.IDisplayElement;
import maze_v4.Interfaces.IMazeRenderer;
import maze_v4.Interfaces.IMazeStructure;
import maze_v4.Interfaces.IObserver;

/**
 *
 * @author dean
 */
public class SquareMazeRenderer
implements IMazeRenderer, IObserver, IDisplayElement
{

    private Color wallColor = Color.BLUE;

    public static final int CELL_WIDTH = 16;
    public static final int CELL_HEIGHT = 16;

    @Override
    public Image drawMaze()
    {
        IMazeStructure mazeStructure = DataModel.getInstance().getMazeStructure();

        BufferedImage image =
                new BufferedImage(mazeStructure.getWidth() * CELL_WIDTH,
                                  mazeStructure.getHeight() * CELL_HEIGHT,
                                  BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();

        graphics.setPaint(Color.BLACK);

        graphics.fillRect(0, 0, image.getWidth(), image.getHeight());

        for (int x = 0; x < mazeStructure.getHeight(); x++)
        {
            for (int y = 0; y < mazeStructure.getWidth(); y++)
            {
                graphics.drawImage(drawCell(indexOfCell(x,y)),
                                   x*CELL_HEIGHT,
                                   y*CELL_WIDTH,
                                   null);
            }
        }

        return image;
    }

    private int indexOfCell(int col, int row)
    {
        IMazeStructure mazeStructure = DataModel.getInstance().getMazeStructure();

        return row * mazeStructure.getWidth() + col;
    }

    private Image drawCell(int index)
    {
        IMazeStructure mazeStructure = DataModel.getInstance().getMazeStructure();

        BufferedImage result = new BufferedImage(CELL_WIDTH,
                                                 CELL_HEIGHT,
                                                 BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = result.createGraphics();
        graphics.setColor(wallColor);

        if (mazeStructure.getMazeCells().get(index).getWall(EnumSquareMazeCellDirection.NORTH.ordinal()))
        {
            drawNorthWall(graphics);
        }

        if (mazeStructure.getMazeCells().get(index).getWall(EnumSquareMazeCellDirection.EAST.ordinal()))
        {
            drawEastWall(graphics);
        }

        if (mazeStructure.getMazeCells().get(index).getWall(EnumSquareMazeCellDirection.SOUTH.ordinal()))
        {
            drawSouthWall(graphics);
        }

        if (mazeStructure.getMazeCells().get(index).getWall(EnumSquareMazeCellDirection.WEST.ordinal()))
        {
            drawWestWall(graphics);
        }

        return result;
    }

    private void drawNorthWall(Graphics g)
    {
        g.drawLine(0,0,CELL_WIDTH-1,0);
    }

    private void drawSouthWall(Graphics g)
    {
        g.drawLine(0,CELL_HEIGHT-1,CELL_WIDTH-1,CELL_HEIGHT-1);
    }

    private void drawWestWall(Graphics g)
    {
        g.drawLine(0, 0, 0, CELL_HEIGHT-1);
    }

    private void drawEastWall(Graphics g)
    {
        g.drawLine(CELL_WIDTH-1, 0, CELL_WIDTH-1, CELL_HEIGHT-1);
    }

    @Override
    public void update()
    {
        System.out.println(this.getClass().getSimpleName() + " updated");

        this.display();
    }

    @Override
    public void display()
    {
        System.out.println("Re-rendering maze");

        this.drawMaze();
    }
}
