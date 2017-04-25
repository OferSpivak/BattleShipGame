package exceptions;

import dal.Ship;

/**
 * Created by OFer.Spivak on 18/04/2017.
 */
public class AddingShipsAboveAllowedAmountException extends InitializationFailException {
    public AddingShipsAboveAllowedAmountException(Ship ship) {
        super("Adding ship {" + ship.toString() + "} above the allowed amount to its type.");
    }
}
