package gameInterface;

import dal.DalImpl;
import engine.BaseGameEngine;
import engine.EngineUIInterface;
import engine.enums.HitBoardType;
import engine.player.Tile;
import engine.settings.Settings;
import engine.settings.SettingsImpl;
import exceptions.HittingTargetOutsideTheBoardException;
import exceptions.InitializationFailException;
import exceptions.TileAlreadyBombedException;

import java.io.File;

public class GameInterface {
    private EngineUIInterface gameEngine;
    private Settings settings;

    public void initializeGame(File gameSettingsFile) throws InitializationFailException {
        DalImpl dal = new DalImpl(gameSettingsFile);
        settings = new SettingsImpl(dal);
        gameEngine = new BaseGameEngine(settings);
    }

    public HitBoardType bombPoint(int x, int y) throws TileAlreadyBombedException, HittingTargetOutsideTheBoardException {
        return gameEngine.bombPoint(x, y);
    }

    public Tile[][] getCurrentPlayerOwnBoard() {
        return gameEngine.getCurrentPlayerOwnBoard();
    }

    public HitBoardType[][] getCurrentPlayerHitBoard() {
        return gameEngine.getCurrentPlayerHitBoard();
    }

    public Tile[][] getOpponentPlayerOwnBoard() {
        return gameEngine.getOpponentPlayerOwnBoard();
    }

    public HitBoardType[][] getOpponentPlayerHitBoard() {
        return gameEngine.getOpponentPlayerHitBoard();
    }

    public String getCurrentPlayerName() {
        return gameEngine.getCurrentPlayerName();
    }

    public String getOpponentPlayerName() {
        return gameEngine.getOpponentPlayerName();
    }

    public int getCurrentPlayerScore() {
        return gameEngine.getCurrentPlayerScore();
    }

    public int getOpponentPlayerScore() {
        return gameEngine.getOpponentPlayerScore();
    }

    public int getCurrentPlayerHitCount() {
        return gameEngine.getCurrentPlayerHitCount();
    }

    public int getCurrentPlayerMissCount() {
        return gameEngine.getCurrentPlayerMissCount();
    }

    public int getOpponentPlayerHitCount() {
        return gameEngine.getOpponentPlayerHitCount();
    }

    public int getOpponentPlayerMissCount() {
        return gameEngine.getOpponentPlayerMissCount();
    }

    public boolean isCurrentPlayerWon() {
        return gameEngine.isCurrentPlayerWon();
    }

    public void setCurrentPlayerId(String id) {
        gameEngine.setCurrentPlayerId(id);
    }

    public void setOpponentPlayerId(String id) {
        gameEngine.setOpponentPlayerId(id);
    }

    public String getCurrentPlayerId() {
        return gameEngine.getCurrentPlayerId();
    }

    public String getOpponentPlayerId() {
        return gameEngine.getOpponentPlayerId();
    }

    public int getBoardSize() { return settings.getBoardSize(); }
}
