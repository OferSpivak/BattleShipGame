package exceptions;

import engine.settings.ShipType;

/**
 * Created by OFer.Spivak on 18/04/2017.
 */
public class ShipTypeAlreadyDecalredException extends InitializationFailException {
    public ShipTypeAlreadyDecalredException(ShipType shipType) {
        super("Ship-type: " + shipType.getType() + " already declared");
    }
}
