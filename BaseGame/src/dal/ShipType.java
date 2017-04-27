package dal;

/**
 * Created by Ofer.Spivak on 15/04/2017.
 */
public interface ShipType {
    enum shipTypeStyle {
        Regular,
        Complex
    }

    int getShipSize();
    int getShipAmountOnBoard();
    shipTypeStyle getType();
    String getShipTypeName();
    int getScore();
}
