package exceptions;

/**
 * Created by OFer.Spivak on 19/04/2017.
 */
public class TileAlreadyBombed extends Exception {
    int x, y;

    public TileAlreadyBombed(int positionX, int poxistionY) {
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
