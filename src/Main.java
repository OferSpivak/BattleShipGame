import engine.BaseGameEngine;
import engine.settings.Settings;
import engine.settings.SettingsImpl;
import exceptions.InitializationFailException;
import ui.ConsoleUI;


public class Main {

    public static void main(String[] args) {
        try {
            Settings settings = new SettingsImpl();
            BaseGameEngine gameEngine = new BaseGameEngine(settings);
            ConsoleUI consoleUI = new ConsoleUI(gameEngine);
            consoleUI.startGAme();
        } catch (InitializationFailException initializationFailException){
            System.out.println("***** Fail to initialize the Game *****");
            System.out.println(initializationFailException.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
