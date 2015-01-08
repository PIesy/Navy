package com.mycompany.app;

public class GameFactory
{

    public static GameProxy newGame(GameType type) throws Exception
    {
        switch (type) {
        case Web:
            return new WebGameProxy();
        case Local:
            return new LocalGameProxy();
        default:
            throw new IllegalArgumentException("No valid type specified");
        }
    }

    public enum GameType
    {
        Local, Web, None;
        
        public static GameType parseString(String str)
        {
            String valueString = str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
            GameType result;
            
            try {
                result = valueOf(valueString);
            } catch(RuntimeException e) {
                return None;
            }
            return result;
        }
    }
}
