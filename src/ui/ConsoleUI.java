package ui;

import engine.enums.TileState;
import engine.player.Tile;

/**
 * Created by OFer.Spivak on 13/04/2017.
 */
public class ConsoleUI {

    public void drawPlayerOwnBoard(Tile[][] board) {
        System.out.print(" ");
        for (int i=1; i <= board.length; i++) {
            System.out.print(i);
        }
        for (int i=1; i <= board.length; i++) {
            System.out.print("-");
        }
        for (int i=0; i<=board.length; i++) {
            System.out.print(i + "|");
            Tile[] row = board[i];
            for (Tile tile : row) {
                TileState tileState = tile.getState();
                switch (tileState) {
                    case EMPTY: {
                        System.out.print(" ");
                        break;
                    }
                    case FULL: {
                        System.out.print("O");
                        break;
                    }
                    case EMPTY_BOMBED: {
                        System.out.print(".");
                    }
                    case SHIP_BOMBED: {
                        System.out.print("X");
                    }
                }
            }
        }
    }
}
