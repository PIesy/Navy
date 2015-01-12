package com.mycompany.app;

import com.mycompany.data.exceptions.GameOverException;
import com.mycompany.data.exceptions.ShipIsKilledException;
import com.mycompany.data.game.Bot;
import com.mycompany.data.game.GameRules;
import com.mycompany.data.game.Grid;
import com.mycompany.data.game.GridItemDescriptor;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class BotTest extends TestCase
{

    public BotTest( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( BotTest.class );
    }

    public void testBot()
    {
        int i = 0;
        
        for(int j = 0; j < 100; j++)
        {
            resetField();
            while(true)
            {
                try {
                    bot.hit(grid);
                } catch (ShipIsKilledException e) {
                    try {
                        bot.destroyShip();
                    } catch (GameOverException e1) {
                        break;
                    }
                }
                i++;
            }
            i++;
        }
        painter.drawGrid(descriptor.fill(getIntDescriptor(grid)), false);
        System.out.println("Average turns required " + (float)i / 100);
        assertTrue( true );
    }
    
    private int[][] getIntDescriptor(Grid field)
    {
        GridItemDescriptor[][] descriptors = field.getState();
        int[][] result = new int[field.getSizeVertical()][field.getSizeHorizontal()];

        for (int i = 0; i < field.getSizeVertical(); i++) {
            for (int j = 0; j < field.getSizeHorizontal(); j++) {
                result[i][j] = descriptors[i][j].toInt();
            }
        }
        return result;
    }
    
    private void resetField()
    {
        grid = new Grid(rules.getFieldDimensions()[0], rules.getFieldDimensions()[1]);
        bot = new Bot(rules);
        bot.setShips(grid);
    }
    
    protected void setUp()
    {
        rules = new GameRules();
        descriptor = new GridDescriptor(rules.getFieldDimensions());
        painter = new ConsolePainter();
    }
    
    private GameRules rules;
    private Painter painter;
    private GridDescriptor descriptor;
    private Grid grid;
    private Bot bot;
}
