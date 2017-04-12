package engine;

/**
 * Created by OFer.Spivak on 12/04/2017.
 */
public class PlayerImpl implements Player {
    HitBoardType hitBoard[][];
    BattleShip ownBoard[][];
    String name;
    int score = 0;
    int missCount = 0;
    int hitCount = 0;

    private void initBoards(int boardsSize) {
        hitBoard = new HitBoardType[boardsSize][boardsSize];
        ownBoard = new BattleShip[boardsSize][boardsSize];

        for (int i = 0; i < boardsSize; i++) {
            for (int j = 0; j < boardsSize; j++) {
                hitBoard[i][j] = HitBoardType.EMPTY;
            }
        }

        for (int i = 0; i < boardsSize; i++) {
            for (int j = 0; j < boardsSize; j++) {
                ownBoard[i][j] = null;
            }
        }
    }

    public PlayerImpl(int boardSize, String name) {
        this.name = name;
        initBoards(boardSize);
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
}
