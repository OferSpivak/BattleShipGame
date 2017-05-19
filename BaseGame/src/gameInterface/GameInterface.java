package gameInterface;

import dal.DalImpl;
import engine.BaseGameEngine;
import engine.EngineUIInterface;
import engine.settings.Settings;
import engine.settings.SettingsImpl;
import exceptions.InitializationFailException;

import java.io.File;

public class GameInterface {
    private EngineUIInterface gameEngine;

    public void initializeGame(File gameSettingsFile) throws InitializationFailException {
        DalImpl dal = new DalImpl(gameSettingsFile);
        Settings settings = new SettingsImpl(dal);
        gameEngine = new BaseGameEngine(settings);
    }
}
