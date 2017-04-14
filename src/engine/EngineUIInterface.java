package engine;

import engine.enums.HitBoardType;
import engine.player.Tile;

/**
 * Created by Ofer.Spivak on 14/04/2017.
 */
public interface EngineUIInterface {

     HitBoardType bombPoint(int x, int y);

     Tile[][] getCurrentPlayerOwnBoard();

     HitBoardType[][] getCurrentPlayerHitBoard();

     String getCurrentUserName();

}
