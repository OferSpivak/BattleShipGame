package engine.player;

import engine.TileState;
import engine.battleShip.BattleShip;

/**
 * Created by OFer.Spivak on 13/04/2017.
 */
public class TileImpl implements Tile {
    BattleShip battleShip;
    TileState state;

    public TileImpl() {
        this.battleShip = null;
        this.state = TileState.EMPTY;
    }

    public TileImpl(BattleShip battleShip) {
        this.battleShip = battleShip;
        this.state = TileState.FULL;
    }

    public BattleShip getBattleShip() {
        return battleShip;
    }

    public TileState getState() {
        return state;
    }

    public void markBombed() {
        this.state = TileState.BOMBED;
    }
}
