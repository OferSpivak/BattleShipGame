package engine.settings;

import engine.enums.Direction;

/**
 * Created by Ofer.Spivak on 16/04/2017.
 */
public interface Ship {
    Direction getDirection();
    int getPoisitionX();
    int getPositionY();
    ShipType getShipType();
}
