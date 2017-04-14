package engine;

import engine.battleShip.BattleShip;
import engine.battleShip.BattleShipImpl;
import engine.enums.Direction;
import engine.enums.HitBoardType;
import engine.player.Player;
import engine.player.PlayerImpl;
import engine.player.Tile;
import engine.settings.Settings;

/**
 * Created by Ofer.Spivak on 12/04/2017.
 */
public class BaseGameEngine implements EngineUIInterface {
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

    public HitBoardType bombPoint(int x, int y) {
        try {
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
            }
            return hit;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Tile[][] getCurrentPlayerOwnBoard() {
        return currentPlayer.getOwnBoard();
    }

    public HitBoardType[][] getCurrentPlayerHitBoard() {
        return currentPlayer.getHitBoard();
    }

    public Tile[][] getOpponentPlayerOwnBoard() {
        return opponentPlayer.getOwnBoard();
    }

    public HitBoardType[][] getOpponentPlayerHitBoard() {
        return opponentPlayer.getHitBoard();
    }

    public String getCurrentPlayerName() {
        return currentPlayer.getName();
    }
    
    public String getOpponentPlayerName() {
        return opponentPlayer.getName();
    }
    
    public int getCurrentPlayerScore() {
        return currentPlayer.getScore();
    }
    
    public int getOpponentPlayerScore() {
        return opponentPlayer.getScore();
    }

    public int getCurrentPlayerHitCount() {
        return currentPlayer.getHitCount();
    }

    public int getCurrentPlayerMissCount() {
        return currentPlayer.getMissCount();
    }

    public int getOpponentPlayerHitCount() {
        return opponentPlayer.getHitCount();
    }

    public int getOpponentPlayerMissCount() {
        return opponentPlayer.getMissCount();
    }
}
