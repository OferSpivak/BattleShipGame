package engine.battleShip;

import engine.enums.Direction;

/**
 * Created by OFer.Spivak on 12/04/2017.
 */
public class BattleShipImpl implements BattleShip{
    private int currentSize;
    private int originalSize;
    private int positionX;
    private int positionY;
    private Direction direction;
    //int score; //todo - to figure out if i gonna used it at the next HW

    public BattleShipImpl(int size, Direction direction, int positionX, int positionY) {
        originalSize = currentSize = size;
        this.direction = direction;
        this.positionX = positionX;
        this.positionY = positionY;
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

    @Override
    public String toString() {
        return "Position: " + (getPositionX() + 1) + ", " + (getPositionY() + 1) + ". Direction: " + getDirection() + ". Size: " + getOriginalSize();
    }
}
