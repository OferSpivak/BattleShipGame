package engine;

import engine.player.Player;
import engine.player.PlayerImpl;
import engine.settings.Settings;

/**
 * Created by Ofer.Spivak on 12/04/2017.
 */
public class BaseGameEngine {
    Player currentPlayer;
    Player opponentPlayer;

    public BaseGameEngine(Settings settings) {
        currentPlayer = new PlayerImpl(settings.getPlayer1Name(), settings.getBoardSize());
        opponentPlayer= new PlayerImpl(settings.getPlayer2Name(), settings.getBoardSize());


    }
}
