package engine.player;

import engine.TileState;
import engine.battleShip.BattleShip;

/**
 * Created by OFer.Spivak on 13/04/2017.
 */
public interface Tile extends Cloneable {
    BattleShip getBattleShip();

    TileState getState();

    void markBombed();
}
