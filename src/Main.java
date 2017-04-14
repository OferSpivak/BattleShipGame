import engine.BaseGameEngine;
import engine.settings.Settings;
import engine.settings.SettingsImpl;
import ui.ConsoleUI;


public class Main {

    public static void main(String[] args) {
        //System.out.println("Hello World!");

        Settings settings = new SettingsImpl("player1", "player2", 20);
        BaseGameEngine gameEngine = new BaseGameEngine(settings);
        ConsoleUI consoleUI = new ConsoleUI(gameEngine);
        consoleUI.startGAme();

        /*consoleUI.drawBoards(gameEngine.getCurrentPlayerOwnBoard(), gameEngine.getCurrentPlayerHitBoard(), gameEngine.getCurrentUserName());

        gameEngine.bombPoint(0,0); //hit
        consoleUI.drawBoards(gameEngine.getCurrentPlayerOwnBoard(), gameEngine.getCurrentPlayerHitBoard(), gameEngine.getCurrentUserName());

        gameEngine.bombPoint(0, 1); //hit
        consoleUI.drawBoards(gameEngine.getCurrentPlayerOwnBoard(), gameEngine.getCurrentPlayerHitBoard(), gameEngine.getCurrentUserName());

        gameEngine.bombPoint(1, 0); //miss
        consoleUI.drawBoards(gameEngine.getCurrentPlayerOwnBoard(), gameEngine.getCurrentPlayerHitBoard(), gameEngine.getCurrentUserName());

        gameEngine.bombPoint(2,2); //hit
        consoleUI.drawBoards(gameEngine.getCurrentPlayerOwnBoard(), gameEngine.getCurrentPlayerHitBoard(), gameEngine.getCurrentUserName());*/
    }
}
