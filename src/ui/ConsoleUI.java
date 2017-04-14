package ui;

import engine.EngineUIInterface;
import engine.enums.HitBoardType;
import engine.enums.TileState;
import engine.player.Tile;

import java.util.Scanner;

/**
 * Created by OFer.Spivak on 13/04/2017.
 */
public class ConsoleUI {
    private EngineUIInterface gameEngine;
    private Scanner scanner;

    public ConsoleUI(EngineUIInterface gameEngine) {
        this.gameEngine = gameEngine;
        scanner = new Scanner(System.in);
    }

    public void startGAme() {
        int i =0;
        while (i<20) {
            printCurrentPlayerState();
            bombTarget();
            i++;
        }
    }

    private void bombTarget() {
        boolean validInput = false;
        do {
            try {
                System.out.println("Please select your target (in x,y format)");
                String input = scanner.nextLine();
                String[] values = input.split(",");
                if (values.length == 2) {
                    int positionX = convertPositionToArrayIndex(Integer.parseInt(values[0]));
                    int positionY = convertPositionToArrayIndex(Integer.parseInt(values[1]));
                    validInput = true;
                    HitBoardType bombResults = gameEngine.bombPoint(positionX, positionY);
                    switch (bombResults){
                        case HIT:{
                            System.out.println("You HIT Your target :-)");
                            System.out.println("Please Play Again");
                            System.out.println();
                            break;
                        }
                        case MISS: {
                            System.out.println("You MISSED your target :-(");
                            System.out.println("Your turn is over");
                            System.out.println();
                        }
                    }
                }
            } catch (NumberFormatException e) {
                validInput = false;
            } catch (Exception e) {
                //todo - deal with exceptions
            }
        } while (!validInput);
    }

    private void getMenuSelection() {
        //todo - add menu options
    }

    private void printCurrentPlayerState() {
        System.out.println("Its " + gameEngine.getCurrentUserName() + " turn");
        System.out.println();
        drawBoards(gameEngine.getCurrentPlayerOwnBoard(), gameEngine.getCurrentPlayerHitBoard());
    }

    private void drawBoards(Tile[][] ownBoard, HitBoardType[][] hitBoard) {
        System.out.println("your Battle Ships board:");
        drawOwnBoard(ownBoard);
        System.out.println();
        System.out.println("your Hit board:");
        drawHitBoard(hitBoard);
        System.out.println();
    }

    private void drawOwnBoard(Tile[][] board) {
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

    private void drawHitBoard(HitBoardType[][] board) {
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

    private int convertPositionToArrayIndex(int position){
        return position - 1;
    }
}
