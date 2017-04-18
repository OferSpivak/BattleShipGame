package exceptions;

import engine.settings.Ship;

/**
 * Created by OFer.Spivak on 18/04/2017.
 */
public class NotAllShipTypeAddedToBoard extends InitializationFail {
    public NotAllShipTypeAddedToBoard(String shipTypeName) {
        super("Not all ships for type " + shipTypeName + " were added to player board");
    }
}
