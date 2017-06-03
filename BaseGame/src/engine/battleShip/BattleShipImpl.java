package engine.battleShip;

import engine.enums.Direction;
import engine.enums.ShipTypeStyle;

/**
 * Created by OFer.Spivak on 12/04/2017.
 */
public class BattleShipImpl implements BattleShip{
    private int currentSize;
    private int originalSize;
    private int positionX;
    private int positionY;
    private int score;
    private Direction direction;
    private ShipTypeStyle shipType;
    //int score; //todo - to figure out if i gonna used it at the next HW

    public BattleShipImpl(int size, Direction direction, ShipTypeStyle shipTypeStyle, int positionX, int positionY, int score) {
        originalSize = currentSize = size;
        this.direction = direction;
        this.shipType = shipTypeStyle;
        this.positionX = positionX;
        this.positionY = positionY;
        this.score = score;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public void setCurrentSize(int currentSize) {
        this.currentSize = currentSize;
    }

    public void reduceCurrentSize() {
        this.currentSize--;
    }

    public int getOriginalSize() {
        return originalSize;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public Direction getDirection() {
        return direction;
    }

    public ShipTypeStyle getShipType() {
        return shipType;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Position: " + (getPositionX() + 1) + ", " + (getPositionY() + 1) + ". Direction: " + getDirection() + ". Size: " + getOriginalSize();
    }
}
