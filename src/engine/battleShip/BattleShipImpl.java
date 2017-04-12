package engine.battleShip;

/**
 * Created by OFer.Spivak on 12/04/2017.
 */
public class BattleShipImpl implements BattleShip{
    private int currentSize;
    private int originalSize;
    private int positionX;
    private int posistionY;
    private Direction direction;
    //int score; //todo - to figure out if i gonna used it at the next HW

    public BattleShipImpl(int size, Direction direction, int positionX, int posistionY) {
        originalSize = currentSize = size;
        this.direction = direction;
        this.positionX = positionX;
        this.posistionY = posistionY;
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
        return posistionY;
    }

    public Direction getDirection() {
        return direction;
    }
}
