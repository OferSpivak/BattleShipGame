package engine;

import engine.enums.HitBoardType;
import engine.player.Tile;
import exceptions.HittingTargetOutsideTheBoardException;
import exceptions.TileAlreadyBombedException;

/**
 * Created by Ofer.Spivak on 14/04/2017.
 */
public interface EngineUIInterface {

     HitBoardType bombPoint(int x, int y) throws TileAlreadyBombedException, HittingTargetOutsideTheBoardException;

     Tile[][] getCurrentPlayerOwnBoard();

     HitBoardType[][] getCurrentPlayerHitBoard();

     public Tile[][] getOpponentPlayerOwnBoard();

     public HitBoardType[][] getOpponentPlayerHitBoard();

     String getCurrentPlayerName();

     String getOpponentPlayerName();

     int getCurrentPlayerScore();

     int getOpponentPlayerScore();

     int getCurrentPlayerHitCount();

     int getCurrentPlayerMissCount();

     int getOpponentPlayerHitCount();

     int getOpponentPlayerMissCount();

     boolean isCurrentPlayerWon();

     void setCurrentPlayerId(String id);

     void setOpponentPlayerId(String id);

     String getCurrentPlayerId();

     String getOpponentPlayerId();

}
