package engine.player;

import engine.battleShip.BattleShip;
import engine.enums.TileState;

/**
 * Created by OFer.Spivak on 13/04/2017.
 */
public interface Tile extends Cloneable {
    BattleShip getBattleShip();

    TileState getState();

    void markBombed();
}
