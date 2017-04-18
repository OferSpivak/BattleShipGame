package engine.player;

import engine.battleShip.BattleShip;
import engine.enums.HitBoardType;
import exceptions.AddingShipOutOfBoardBounds;
import exceptions.AddingShipToNoneEmptyBoardTile;

import java.util.List;

/**
 * Created by OFer.Spivak on 12/04/2017.
 */
public interface Player {

    void addShip(BattleShip ship) throws AddingShipOutOfBoardBounds, AddingShipToNoneEmptyBoardTile;

    void addShips(List<BattleShip> battleShipList) throws AddingShipOutOfBoardBounds, AddingShipToNoneEmptyBoardTile;

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
