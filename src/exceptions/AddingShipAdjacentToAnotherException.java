package exceptions;

import engine.battleShip.BattleShip;

/**
 * Created by OFer.Spivak on 19/04/2017.
 */
public class AddingShipAdjacentToAnotherException extends InitializationFailException {
    public AddingShipAdjacentToAnotherException(BattleShip ship, int x, int y) {
        super("Adding the ship {" + ship.toString() + "} is impossible due to it been adjacent to another ship at " + (x + 1) + ", " + (y + 1) + " position");
    }
}
