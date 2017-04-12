package engine;

/**
 * Created by OFer.Spivak on 12/04/2017.
 */
public interface Player {

    String getName();

    int getScore();

    void setScore(int score);

    int getMissCount();

    int getHitCount();
}
