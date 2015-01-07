package com.mycompany.app;

import java.io.IOException;

import com.mycompany.app.GridDescriptor;
import com.mycompany.app.Painter;

import com.mycompany.data.game.LocalPlayer;
import com.mycompany.data.game.GameRules;
import com.mycompany.data.game.ships.Ship;
import com.mycompany.data.game.Directions;
import com.mycompany.data.game.GameResponse;

public class GameHandler
{

    public GameHandler(Painter painter) throws IOException
    {
        gameHelper = new GameHelper(painter, new ConsoleInputHandler());
        GameRules rules = newGame();
        initFieldDescriptors(rules);
        player = new LocalPlayer(rules);
        this.painter = painter;
        painter.drawGrid(grid, false);
    }

    public void start()
    {
        try {
            getPlayerName();
            prepareForGame();
            gameLoop();
        } catch (Exception e) {
            painter.printLine("Sorry not today");
            e.printStackTrace();
        }
    }

    private void getPlayerName() throws Exception
    {
        String name;
        GameResponse response;

        do {
            name = gameHelper.getName("Enter your name");
            player.setName(name);
            response = proxy.makeRequest(GameRequestFactory.getSetNameRequest(name));
        } while (gameHelper.printIfError(response));
    }

    private void prepareForGame() throws Exception
    {
        Ship ship;
        int[] coordinates;
        Directions direction;
        GameResponse response;

        while (true)
        {
            ship = player.getShip();
            if (ship == null)
                break;
            do {
                coordinates = gameHelper.getCoordinates("Enter ship start coordinates in form: x y", grid.getDimensions());
                direction = gameHelper.getDirection("Enter ship direction(north, south, east, west)");
                response = proxy.makeRequest(GameRequestFactory.getSetShipRequest(coordinates, direction));
                painter.drawGrid(grid.fill(response.getPlayerField()), false);
            } while (gameHelper.printIfError(response));
        }
    }

    private GameResponse hit() throws Exception
    {
        int[] coordinates;
        GameResponse response;

        do
        {
            coordinates = gameHelper.getCoordinates("Enter coordinates to hit in form: x y", grid.getDimensions());
            response = proxy.makeRequest(GameRequestFactory.getHitRequest(coordinates));

        } while (gameHelper.printIfError(response));
        return response;
    }

    private void gameLoop() throws Exception
    {
        GameResponse response = new GameResponse();

        while (response.getGameEnd() == null)
        {
            response = hit();
            grid.fill(response.getPlayerField());
            painter.drawGrid(grid, false);
            enemyGrid.fill(response.getPlayerField());
            painter.printLine("================================================================");
            painter.drawGrid(enemyGrid, true);
            gameHelper.printIfError(response);
        }
        painter.printLine(response.getGameEnd());
    }

    private GameRules newGame() throws IOException
    {
        proxy = GameFactory.newGame(gameHelper.getGameType("Choose game type please: (Web or Local)"));
        return proxy.getRules();
    }

    private void initFieldDescriptors(GameRules rules)
    {
        enemyGrid = new GridDescriptor(rules.getFieldDimensions());
        grid = new GridDescriptor(rules.getFieldDimensions());
    }

    private GameProxy proxy;
    private GameHelper gameHelper;
    private Painter painter;
    private LocalPlayer player;
    private GridDescriptor grid, enemyGrid;
}
