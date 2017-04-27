package engine.player;

import engine.battleShip.BattleShip;
import engine.enums.HitBoardType;
import exceptions.*;

import java.util.List;

/**
 * Created by OFer.Spivak on 12/04/2017.
 */
public interface Player {

    void addShip(BattleShip ship) throws AddingShipOutOfBoardBoundsException, AddingShipToNoneEmptyBoardTileException, AddingShipAdjacentToAnotherException;

    void addShips(List<BattleShip> battleShipList) throws AddingShipOutOfBoardBoundsException, AddingShipToNoneEmptyBoardTileException, AddingShipAdjacentToAnotherException;

    void markHit(int x, int y, int shipScore) throws HittingTargetOutsideTheBoardException;

    void markMiss(int x, int y) throws HittingTargetOutsideTheBoardException;

    HitBoardType tryToHitMyShip(int x, int y) throws TileAlreadyBombedException, HittingTargetOutsideTheBoardException;

    String getName();

    int getScore();

    void setScore(int score);

    int getMissCount();

    int getHitCount();

    HitBoardType[][] getHitBoard();

    Tile[][] getOwnBoard();

    int getShipScore(int x, int y);
}
