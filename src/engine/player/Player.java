package engine.player;

import engine.battleShip.BattleShip;
import engine.HitBoardType;

/**
 * Created by OFer.Spivak on 12/04/2017.
 */
public interface Player {

    void addShip(BattleShip ship);

    void markHit(int x, int y);

    void markMiss(int x, int y);

    HitBoardType tryToHitMyShip(int x, int y) throws Exception;

    String getName();

    int getScore();

    void setScore(int score);

    int getMissCount();

    int getHitCount();

    HitBoardType[][] getHitBoard();

    Tile[][] getOwnBoard();
}
