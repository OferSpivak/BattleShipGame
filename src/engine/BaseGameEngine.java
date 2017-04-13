package engine;

import engine.battleShip.BattleShip;
import engine.battleShip.BattleShipImpl;
import engine.battleShip.Direction;
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
        initGame(settings);
    }

    private void initGame(Settings settings) {
        // create players
        currentPlayer = new PlayerImpl(settings.getPlayer1Name(), settings.getBoardSize());
        opponentPlayer = new PlayerImpl(settings.getPlayer2Name(), settings.getBoardSize());

        //adding ships - todo: extract to method with validation etc...
        BattleShip ship = new BattleShipImpl(4, Direction.ROW, 0, 0);
        opponentPlayer.addShip(ship);
        ship = new BattleShipImpl(2, Direction.COLUMN, 3, 1);
        opponentPlayer.addShip(ship);

        ship = new BattleShipImpl(2, Direction.COLUMN, 3, 1);
        currentPlayer.addShip(ship);
        ship = new BattleShipImpl(4, Direction.ROW, 2, 2);
        currentPlayer.addShip(ship);
    }

    private void switchPlayers() {
        Player tmp = currentPlayer;
        currentPlayer = opponentPlayer;
        opponentPlayer = tmp;
    }

    public void bombPoint(int x, int y) {
        HitBoardType hit = opponentPlayer.tryToHitMyShip(x, y);
        switch (hit) {
            case HIT: {
                currentPlayer.markHit(x, y);
                break;
            }
            case MISS: {
                currentPlayer.markMiss(x, y);
                switchPlayers();
                break;
            }
            case ALREADY_HIT: {
                break;
            }
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getOpponentPlayer() {
        return opponentPlayer;
    }
}
