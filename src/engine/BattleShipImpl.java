package engine;

/**
 * Created by OFer.Spivak on 12/04/2017.
 */
public class BattleShipImpl implements BattleShip{
    int currentSize;
    int originalSize;
    //int score; //todo - to figure out if i gonna used it at the next HW

    public BattleShipImpl(int size) {
        originalSize = currentSize = size;
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

}
