package engine;

import engine.enums.HitBoardType;
import engine.player.Player;
import engine.player.PlayerImpl;
import engine.player.Tile;
import engine.settings.Settings;
import exceptions.HittingTargetOutsideTheBoardException;
import exceptions.InitializationFailException;
import exceptions.TileAlreadyBombedException;

/**
 * Created by Ofer.Spivak on 12/04/2017.
 */
public class BaseGameEngine implements EngineUIInterface {
    private Player currentPlayer;
    private  Player opponentPlayer;
    private int scoreToWin;

    public BaseGameEngine(Settings settings) throws InitializationFailException {
        initGame(settings);
        scoreToWin = settings.getTotalShipsTilesAmount();
    }

    private void initGame(Settings settings) throws InitializationFailException {
        // create players
        currentPlayer = new PlayerImpl(settings.getPlayer1Name(), settings.getBoardSize());
        opponentPlayer = new PlayerImpl(settings.getPlayer2Name(), settings.getBoardSize());

        //adding ships
        currentPlayer.addShips(settings.getPlayer1Ships());
        opponentPlayer.addShips(settings.getPlayer2Ships());
    }

    private void switchPlayers() {
        Player tmp = currentPlayer;
        currentPlayer = opponentPlayer;
        opponentPlayer = tmp;
    }

    public HitBoardType bombPoint(int x, int y) throws TileAlreadyBombedException, HittingTargetOutsideTheBoardException {
        HitBoardType hit = opponentPlayer.tryToHitMyShip(x, y);
        switch (hit) {
            case HIT: {
                int hitShipScore = opponentPlayer.getShipScore(x, y);
                currentPlayer.markHit(x, y, hitShipScore);
                break;
            }
            case MISS: {
                currentPlayer.markMiss(x, y);
                switchPlayers();
                break;
            }
        }
        return hit;
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
        return currentPlayer.getHitCount();
    }

    public int getOpponentPlayerScore() {
        return opponentPlayer.getHitCount();
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

    public boolean isCurrentPlayerWon() {
        return getCurrentPlayerHitCount() == scoreToWin;
    }
}
