import engine.BaseGameEngine;
import engine.settings.Settings;
import engine.settings.SettingsImpl;

public class Main {

    public static void main(String[] args) {
        //System.out.println("Hello World!");

        Settings settings = new SettingsImpl("player1", "player2", 5);
        BaseGameEngine gameEngine = new BaseGameEngine(settings);
        gameEngine.bombPoint(0,0); //hit
        gameEngine.bombPoint(0, 1); //hit
        gameEngine.bombPoint(1, 0); //miss
        gameEngine.bombPoint(2,2); //hit

    }
}
