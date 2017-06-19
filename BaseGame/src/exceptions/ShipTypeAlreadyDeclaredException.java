package exceptions;

import dal.ShipType;

/**
 * Created by OFer.Spivak on 18/04/2017.
 */
public class ShipTypeAlreadyDeclaredException extends InitializationFailException {
    public ShipTypeAlreadyDeclaredException(ShipType shipType) {
        super("Ship-type: " + shipType.getShipTypeId() + " declared more than one");
    }
}
