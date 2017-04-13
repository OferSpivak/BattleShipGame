package engine.player;

import engine.battleShip.BattleShip;
import engine.enums.Direction;
import engine.enums.HitBoardType;

import java.util.ArrayList;

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

    public void addShip(BattleShip ship) {
        int positionX = ship.getPositionX();
        int positionY = ship.getPositionY();
        Direction direction = ship.getDirection();

        try {
            if (direction == Direction.ROW) {
                for (int i = 0; i < ship.getOriginalSize(); i++) {
                    ownBoard[positionX][positionY + i] = new TileImpl(ship);
                }
            } else if (direction == Direction.COLUMN) {
                for (int i = 0; i < ship.getOriginalSize(); i++) {
                    ownBoard[positionX + i][positionY] = new TileImpl(ship);
                }
            }
            ships.add(ship);
        } catch (ArrayIndexOutOfBoundsException e) {
            //todo something.
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

    public HitBoardType tryToHitMyShip(int x, int y) throws Exception {
        try {
            Tile tile = ownBoard[x][y];
            switch (tile.getState()) {
                case FULL: {
                    tile.getBattleShip().reduceCurrentSize();
                    tile.markBombed();
                    return HitBoardType.HIT;
                }
                case EMPTY: {
                    return HitBoardType.MISS;
                }
                case BOMBED: {
                    throw new Exception("already bombed");
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
