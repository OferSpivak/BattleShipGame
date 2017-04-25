package dal;

import engine.enums.Direction;

/**
 * Created by Ofer.Spivak on 16/04/2017.
 */
public interface Ship {
    Direction getDirection();
    int getPositionX();
    int getPositionY();
    ShipType getShipType();
}
