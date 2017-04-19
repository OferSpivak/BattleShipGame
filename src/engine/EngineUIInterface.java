package engine;

import engine.enums.HitBoardType;
import engine.player.Tile;
import exceptions.TileAlreadyBombed;

/**
 * Created by Ofer.Spivak on 14/04/2017.
 */
public interface EngineUIInterface {

     HitBoardType bombPoint(int x, int y) throws TileAlreadyBombed;

     Tile[][] getCurrentPlayerOwnBoard();

     HitBoardType[][] getCurrentPlayerHitBoard();

     String getCurrentPlayerName();

     String getOpponentPlayerName();

     int getCurrentPlayerScore();

     int getOpponentPlayerScore();

     int getCurrentPlayerHitCount();

     int getCurrentPlayerMissCount();

     int getOpponentPlayerHitCount();

     int getOpponentPlayerMissCount();

}
