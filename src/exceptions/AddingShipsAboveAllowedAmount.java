package exceptions;

import engine.settings.Ship;
import engine.settings.ShipType;

/**
 * Created by OFer.Spivak on 18/04/2017.
 */
public class AddingShipsAboveAllowedAmount extends InitializationFail {
    public AddingShipsAboveAllowedAmount(Ship ship) {
        super("Adding ship {" + ship.toString() + "} above the allowed amount to its type.");
    }
}
