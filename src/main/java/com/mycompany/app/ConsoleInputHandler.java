package com.mycompany.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import com.mycompany.data.game.Directions;
import com.mycompany.app.GameFactory.GameType;

public class ConsoleInputHandler
{

    public int[] getCoordinates(int[] ambits)
    {
        int[] coordinates = { -1, -1 };
        do {
            try {
                buffer = reader.readLine();
            } catch (IOException e) {
            }
            try (Scanner scanner = new Scanner(buffer);)
            {
                coordinates[0] = scanner.nextInt() - 1;
                coordinates[1] = scanner.nextInt() - 1;
            } catch (Exception e) {
            }
        } while (!checkCoordinateResults(coordinates, ambits));
        return coordinates;
    }

    public Directions getShipDirection()
    {
        Directions direction;
        do {
            try {
                buffer = reader.readLine();
            } catch (IOException e) {
            }
            switch (buffer.toLowerCase()) {
            case "north":
                direction = Directions.North;
                break;
            case "south":
                direction = Directions.South;
                break;
            case "east":
                direction = Directions.East;
                break;
            case "west":
                direction = Directions.West;
                break;
            default:
                direction = Directions.None;
                break;
            }
        } while (direction == Directions.None);
        return direction;
    }

    public GameType getType()
    {
        GameType type;

        do {
            try {
                buffer = reader.readLine();
            } catch (IOException e) {
            }
            switch (buffer.toLowerCase()) {
            case "web":
                type = GameType.Web;
                break;
            case "local":
                type = GameType.Local;
                break;
            default:
                type = GameType.None;
                break;
            }
        } while (type == GameType.None);
        return type;
    }

    public String getLine()
    {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return "";
        }
    }

    private boolean checkCoordinateResults(int[] coordinates, int[] ambits)
    {
        if ((coordinates[0] < 0) || (coordinates[1] < 0)) {
            return false;
        }
        if ((coordinates[0] > ambits[0]) || (coordinates[1] > ambits[1])) {
            return false;
        }
        return true;
    }

    private String buffer;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
}
