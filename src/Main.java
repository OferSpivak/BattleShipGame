import dal.DalImpl;
import engine.BaseGameEngine;
import engine.settings.Settings;
import engine.settings.SettingsImpl;
import exceptions.InitializationFailException;
import ui.ConsoleUI;


public class Main {

    public static void main(String[] args) {
        try {
            ConsoleUI consoleUI = new ConsoleUI();
            String path = consoleUI.askForGameSettingPath();
            DalImpl dal = new DalImpl(path);
            Settings settings = new SettingsImpl(dal);
            BaseGameEngine gameEngine = new BaseGameEngine(settings);
            consoleUI.initConsoleWithGameEngine(gameEngine);
            consoleUI.startGAme();
        } catch (InitializationFailException initializationFailException){
            System.out.println("***** Fail to initialize the Game *****");
            System.out.println(initializationFailException.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
