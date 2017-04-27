package exceptions;

/**
 * Created by OFer.Spivak on 18/04/2017.
 */
public class NotAllShipTypeAddedToBoardException extends InitializationFailException {
    public NotAllShipTypeAddedToBoardException(String shipTypeName) {
        super("Not all ships for type " + shipTypeName + " were added to player board");
    }
}
