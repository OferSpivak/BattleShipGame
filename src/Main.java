import engine.BaseGameEngine;
import engine.settings.Settings;
import engine.settings.SettingsImpl;
import ui.ConsoleUI;


public class Main {

    public static void main(String[] args) {
        //System.out.println("Hello World!");

        Settings settings = new SettingsImpl("player1", "player2", 5);
        ConsoleUI consoleUI = new ConsoleUI();
        BaseGameEngine gameEngine = new BaseGameEngine(settings);
        consoleUI.drawPlayerOwnBoard(gameEngine.getCurrentPlayerOwnBoard());

        gameEngine.bombPoint(0,0); //hit
        consoleUI.drawPlayerOwnBoard(gameEngine.getCurrentPlayerOwnBoard());
        gameEngine.bombPoint(0, 1); //hit
        consoleUI.drawPlayerOwnBoard(gameEngine.getCurrentPlayerOwnBoard());
        gameEngine.bombPoint(1, 0); //miss
        gameEngine.bombPoint(2,2); //hit

    }
}
