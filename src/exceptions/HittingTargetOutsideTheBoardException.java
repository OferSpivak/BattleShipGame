package exceptions;

/**
 * Created by Ofer.Spivak on 19/04/2017.
 */
public class HittingTargetOutsideTheBoardException extends Exception{
    int x, y;

    public HittingTargetOutsideTheBoardException(int positionX, int poxistionY) {
        super();
        x = positionX;
        y = poxistionY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
