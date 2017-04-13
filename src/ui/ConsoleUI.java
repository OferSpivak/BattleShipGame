package ui;

import engine.enums.HitBoardType;
import engine.enums.TileState;
import engine.player.Tile;

/**
 * Created by OFer.Spivak on 13/04/2017.
 */
public class ConsoleUI {

    public void drawBoards(Tile[][] ownBoard, HitBoardType[][] hitBoard, String userName){
        System.out.println();
        System.out.println("Currently its "+ userName + " turn");
        drawOwnBoard(ownBoard);
        drawHitBoard(hitBoard);
    }

    public void drawOwnBoard(Tile[][] board) {
        System.out.println();
        drawBoardHeader(board.length);

        for (int i = 0; i < board.length; i++) {
            System.out.println();
            if (board.length > 9 && i < 9) {
                System.out.print(" ");
            }
            System.out.print(" " + (i + 1));
            Tile[] row = board[i];
            for (Tile tile : row) {
                TileState tileState = tile.getState();
                switch (tileState) {
                    case EMPTY: {
                        System.out.print("|   ");
                        break;
                    }
                    case FULL: {
                        System.out.print("| O ");
                        break;
                    }
                    case EMPTY_BOMBED: {
                        System.out.print("| . ");
                    }
                    case SHIP_BOMBED: {
                        System.out.print("| X ");
                    }
                }
            }
            System.out.print("|");
        }
        System.out.println();
    }

    public void drawHitBoard(HitBoardType[][] board) {
        System.out.println();
        drawBoardHeader(board.length);


        for (int i = 0; i < board.length; i++) {
            System.out.println();
            if (board.length > 9 && i < 9) {
                System.out.print(" ");
            }
            System.out.print(" " + (i + 1));
            HitBoardType[] row = board[i];
            for (HitBoardType tile : row) {
                switch (tile) {
                    case EMPTY: {
                        System.out.print("|   ");
                        break;
                    }
                    case MISS: {
                        System.out.print("| . ");
                        break;
                    }
                    case HIT: {
                        System.out.print("| X ");
                    }
                }
            }
            System.out.print("|");
        }
        System.out.println();
    }

    private void drawBoardHeader(int boardSize) {
        addPrefixSpaces(boardSize);
        for (int i = 1; i <= boardSize; i++) {
            System.out.print("| " + i);
            if (i < 10) {
                System.out.print(" ");
            }
        }
        System.out.print("|");
        System.out.println();
        addPrefixSpaces(boardSize);
        for (int i = 1; i <= boardSize; i++) {
            System.out.print("----");
        }
        System.out.print("-");
    }

    private void addPrefixSpaces(int boardSize) {
        System.out.print("  ");
        if (boardSize > 9) {
            System.out.print(" ");
        }
    }
}
