package exceptions;

import engine.battleShip.BattleShip;

/**
 * Created by OFer.Spivak on 18/04/2017.
 */
public class AddingShipOutOfBoardBoundsException extends InitializationFailException {
    public AddingShipOutOfBoardBoundsException(BattleShip ship) {
        super("Adding the ship {"+ ship.toString() + "} is impossible due to it reaching out of the board bounds.");
    }
}
