package ui;

import engine.EngineUIInterface;
import engine.enums.HitBoardType;
import engine.enums.TileState;
import engine.player.Tile;
import exceptions.HittingTargetOutsideTheBoardException;
import exceptions.TileAlreadyBombedException;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by OFer.Spivak on 13/04/2017.
 */
public class ConsoleUI {
    private EngineUIInterface gameEngine;
    private Scanner scanner;
    private boolean shouldRunGame;

    public ConsoleUI(EngineUIInterface gameEngine) {
        this.gameEngine = gameEngine;
        scanner = new Scanner(System.in);
        shouldRunGame = true;
    }

    public void startGAme() {
        while (shouldRunGame) {
            printCurrentPlayerState();
            getMenuSelection();
            isPlayerWon();
        }
        printEndGame();
    }

    private void printCurrentPlayerState() {
        System.out.println("Its " + gameEngine.getCurrentPlayerName() + " turn");
        System.out.println();
        drawBoards(gameEngine.getCurrentPlayerOwnBoard(), gameEngine.getCurrentPlayerHitBoard());
    }

    private void getMenuSelection() {
        boolean validInput = false;
        do {
            try {
                System.out.println("Menu:");
                System.out.println("1: Bomb Target");
                System.out.println("2: Game Statistics");
                System.out.println("3: Quit Game");
                System.out.println("Enter your selection (1 / 2 / 3)");
                int input = scanner.nextInt();
                switch (input){
                    case 1:{
                        validInput = true;
                        scanner.nextLine();
                        bombTarget();
                        break;
                    }
                    case 2:{
                        printGameStatistics();
                        break;
                    }
                    case 3: {
                        shouldRunGame = false;
                        validInput = true;
                        break;
                    }
                    default: {
                        validInput = false;
                    }
                }
            }catch (InputMismatchException e){
                validInput = false;
                scanner.nextLine();
            }
        }while(!validInput);
        //todo - add menu options
    }

    private void printEndGame() {
        System.out.println("Thank you for playing");
        int currentPlayerScore = gameEngine.getCurrentPlayerScore();
        int opponentPlayerScore = gameEngine.getOpponentPlayerScore();
        if (currentPlayerScore > opponentPlayerScore) {
            System.out.println(gameEngine.getCurrentPlayerName() + " Won!");
        } else if(currentPlayerScore < opponentPlayerScore) {
            System.out.println(gameEngine.getOpponentPlayerName() + " Won!");
        } else {
            System.out.println("We have a Draw");
        }

    }

    private void printGameStatistics() {
        int currentPlayerHitCount = gameEngine.getCurrentPlayerHitCount();
        int currentPlayerMissCount = gameEngine.getCurrentPlayerMissCount();
        int opponentPlayerHitCount = gameEngine.getOpponentPlayerHitCount();
        int opponentPlayerMissCount = gameEngine.getOpponentPlayerMissCount();

        System.out.println("Statistics:");
        System.out.println(gameEngine.getCurrentPlayerName() + " Hits: " + currentPlayerHitCount + ", Misses: "+ currentPlayerMissCount);
        System.out.println(gameEngine.getOpponentPlayerName() + " Hits: " + opponentPlayerHitCount+ ", Misses: "+ opponentPlayerMissCount);
    }

    private void bombTarget() {
        boolean validInput = false;
        int positionX = 1;
        int positionY = 1;
        do {
            try {
                System.out.println("Select your target (in x,y format)");
                String input = scanner.nextLine();
                String[] values = input.split(",");
                if (values.length == 2) {
                    positionX = convertPositionToArrayIndex(Integer.parseInt(values[0]));
                    positionY = convertPositionToArrayIndex(Integer.parseInt(values[1]));
                    validInput = true;
                    HitBoardType bombResults = gameEngine.bombPoint(positionX, positionY);
                    switch (bombResults){
                        case HIT:{
                            System.out.println("You HIT Your target :-)");
                            if (!gameEngine.isCurrentPlayerWon()){
                                System.out.println("Please Play Again");
                            }
                            System.out.println();
                            break;
                        }
                        case MISS: {
                            System.out.println("You MISSED your target :-(");
                            System.out.println("Your turn is over");
                            System.out.println();
                        }
                    }
                } else {
                    System.out.println("Please enter your selection in the correct format");
                }
            } catch (NumberFormatException e) {
                validInput = false;
                System.out.println("Please enter your selection in the correct format");
            } catch (TileAlreadyBombedException e) {
                validInput = false;
                System.out.println("Tile at position: " + (e.getX() + 1) + ", " + (e.getY() + 1) + " already bombed.");
                System.out.println("Please try again");
            } catch (HittingTargetOutsideTheBoardException e) {
                validInput = false;
                System.out.println("The position: " + (e.getX() + 1) + ", " + (e.getY() + 1) + " is outside the board");
                System.out.println("Please try again");
            }
        } while (!validInput);
    }

    private void isPlayerWon() {
        if (gameEngine.isCurrentPlayerWon()){
            shouldRunGame = false;
        }
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
                System.out.print(tileState.toString());
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
                System.out.print(tile.toString());
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
