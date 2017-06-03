package dal;

import engine.enums.ShipTypeStyle;

/**
 * Created by Ofer.Spivak on 15/04/2017.
 */
public interface ShipType {
    int getShipSize();
    int getShipAmountOnBoard();
    ShipTypeStyle getType();
    String getShipTypeName();
    int getScore();
}
