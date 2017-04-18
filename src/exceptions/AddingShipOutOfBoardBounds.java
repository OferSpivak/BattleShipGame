package exceptions;

import engine.battleShip.BattleShip;

/**
 * Created by OFer.Spivak on 18/04/2017.
 */
public class AddingShipOutOfBoardBounds extends InitializationFail {
    public AddingShipOutOfBoardBounds(BattleShip ship) {
        super("Adding the ship {"+ ship.toString() + "} is impossible due to it reaching out of the board bounds.");
    }
}
