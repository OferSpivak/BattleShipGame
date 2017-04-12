package engine.battleShip;

/**
 * Created by OFer.Spivak on 12/04/2017.
 */
public interface BattleShip {
    int getCurrentSize();

    void setCurrentSize(int currentSize);

    void reduceCurrentSize();

    int getOriginalSize();

    int getPositionX();

    int getPositionY();

    Direction getDirection();
}
