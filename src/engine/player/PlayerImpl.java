package engine.player;

import engine.battleShip.BattleShip;
import engine.enums.Direction;
import engine.enums.HitBoardType;
import engine.enums.TileState;
import exceptions.AddingShipOutOfBoardBoundsException;
import exceptions.AddingShipToNoneEmptyBoardTileException;
import exceptions.TileAlreadyBombedException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OFer.Spivak on 12/04/2017.
 */
public class PlayerImpl implements Player {
    private HitBoardType hitBoard[][];
    private Tile ownBoard[][];
    private ArrayList<BattleShip> ships = new ArrayList<>();
    private String name;
    private int score = 0;
    private int missCount = 0;
    private int hitCount = 0;

    private void initBoards(int boardsSize) {
        hitBoard = new HitBoardType[boardsSize][boardsSize];
        ownBoard = new Tile[boardsSize][boardsSize];

        for (int i = 0; i < boardsSize; i++) {
            for (int j = 0; j < boardsSize; j++) {
                hitBoard[i][j] = HitBoardType.EMPTY;
            }
        }

        for (int i = 0; i < boardsSize; i++) {
            for (int j = 0; j < boardsSize; j++) {
                ownBoard[i][j] = new TileImpl();
            }
        }
    }

    public PlayerImpl(String name, int boardSize) {
        this.name = name;
        initBoards(boardSize);
    }

    public void addShip(BattleShip ship) throws AddingShipOutOfBoardBoundsException, AddingShipToNoneEmptyBoardTileException {
        int positionX = ship.getPositionX();
        int positionY = ship.getPositionY();
        Direction direction = ship.getDirection();

        try {
            if (direction == Direction.ROW) {
                for (int i = 0; i < ship.getOriginalSize(); i++) {
                    if (ownBoard[positionX][positionY + i].getState() != TileState.EMPTY) {
                        throw new AddingShipToNoneEmptyBoardTileException(ship, positionX, positionY + i);
                    }
                    ownBoard[positionX][positionY + i] = new TileImpl(ship);
                }
            } else if (direction == Direction.COLUMN) {
                for (int i = 0; i < ship.getOriginalSize(); i++) {
                    if (ownBoard[positionX + i][positionY].getState() != TileState.EMPTY) {
                        throw new AddingShipToNoneEmptyBoardTileException(ship, positionX + i, positionY);
                    }
                    ownBoard[positionX + i][positionY] = new TileImpl(ship);
                }
            }
            ships.add(ship);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new AddingShipOutOfBoardBoundsException(ship);
        }
    }

    public void addShips(List<BattleShip> battleShipList) throws AddingShipOutOfBoardBoundsException, AddingShipToNoneEmptyBoardTileException {
        for (BattleShip battleShip : battleShipList) {
            addShip(battleShip);
        }
    }

    public void markHit(int x, int y) { //user successfully hit the opponent ship
        try {
            hitBoard[x][y] = HitBoardType.HIT;
            hitCount++;
            score++; //todo check how to calc score;
        } catch (ArrayIndexOutOfBoundsException e) {
            //todo something
        }
    }

    public void markMiss(int x, int y) { //user missed the opponent ship
        try {
            hitBoard[x][y] = HitBoardType.MISS;
            missCount++;
        } catch (ArrayIndexOutOfBoundsException e) {
            //todo something
        }
    }

    public HitBoardType tryToHitMyShip(int x, int y) throws TileAlreadyBombedException {
        try {
            Tile tile = ownBoard[x][y];
            switch (tile.getState()) {
                case FULL: {
                    tile.getBattleShip().reduceCurrentSize();
                    tile.markBombed();
                    return HitBoardType.HIT;
                }
                case EMPTY: {
                    tile.markBombed();
                    return HitBoardType.MISS;
                }
                case EMPTY_BOMBED:
                case SHIP_BOMBED: {
                    throw new TileAlreadyBombedException(x, y);
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            //todo something
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getMissCount() {
        return missCount;
    }

    public int getHitCount() {
        return hitCount;
    }

    public HitBoardType[][] getHitBoard() {
        return hitBoard.clone();
    }

    public Tile[][] getOwnBoard() {
        return ownBoard.clone();
    }
}
