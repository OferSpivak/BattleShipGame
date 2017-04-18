package exceptions;

import engine.battleShip.BattleShip;

/**
 * Created by OFer.Spivak on 18/04/2017.
 */
public class AddingShipToNoneEmptyBoardTile extends InitializationFail {
    public AddingShipToNoneEmptyBoardTile(BattleShip ship, int positionX, int positionY) {
        super("Adding ship (" + ship.toString() + ") to none empty board tile: " + positionX + ", " + positionY);
    }
}
