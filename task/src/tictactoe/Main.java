package tictactoe;

import java.util.Scanner;

public class Main {

    private static boolean xWon = false;
    private static boolean oWon = false;
    private static char[][] array;
    private static boolean isSymbolX = true;

    public static void main(String[] args) {
        array = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                array[i][j] = '_';
            }
        }
        printGrid();
        while(!analyzeGameStateEnds()) {
            getUserInput();
        }
        printGameState();
    }

    private static void getUserInput() {
        Scanner scanner = new Scanner(System.in);
        boolean inputIsRight = false;
        while (!inputIsRight) {
            System.out.print("Enter the coordinates: ");
            String input = scanner.nextLine();
            int firstCoordinate;
            int secondCoordinate;
            try {
                String [] pieces = input.split(" ");
                firstCoordinate = Integer.parseInt(pieces[0]);
                secondCoordinate = Integer.parseInt(pieces[1]);

            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                continue;
            }
            if (firstCoordinate < 1 || firstCoordinate > 3 || secondCoordinate < 1 || secondCoordinate > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else if (array[firstCoordinate - 1][secondCoordinate - 1] != '_') {
                System.out.println("This cell is occupied! Choose another one!");
            } else {
                inputIsRight = true;
                if (isSymbolX) {
                    array[firstCoordinate - 1][secondCoordinate - 1] = 'X';
                    isSymbolX = false;
                } else {
                    array[firstCoordinate - 1][secondCoordinate - 1] = 'O';
                    isSymbolX = true;
                }
                printGrid();
            }
        }
    }

    private static void printGrid() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for(int j = 0; j < 3; j++) {
                System.out.printf("%c ", array[i][j]);
            }
            System.out.print("|\n");
        }
        System.out.println("---------");
    }

    private static boolean analyzeGameStateEnds() {
        for (int i = 0; i < 3; i++) {
            boolean horizontalWin = array[i][0] == array[i][1] && array[i][1] == array[i][2];
            boolean verticalWin = array[0][i] == array[1][i] && array[1][i] == array[2][i];
            if (horizontalWin) {
                if(array[i][0] == 'X') {
                    xWon = true;
                } else if (array[i][0] == 'O') {
                    oWon = true;
                }
            }
            if (verticalWin) {
                if(array[0][i] == 'X') {
                    xWon = true;
                } else if (array[0][i] == 'O') {
                    oWon = true;
                }
            }
        }
        boolean diagonalWin1 = array[0][0] == array[1][1] && array[1][1] == array[2][2];
        boolean diagonalWin2 = array[2][0] == array[1][1] && array[1][1] == array[0][2];
        if (diagonalWin1 || diagonalWin2) {
            if(array[1][1] == 'X') {
                xWon = true;
            } else if (array[1][1] == 'O') {
                oWon = true;
            }
        }
        return xWon || oWon;
    }

    private static void printGameState() {
        int counterX = 0;
        int counterO = 0;
        int counterSpace = 0;

        for (char[] cArray : array) {
            for (char c : cArray) {
                switch (c) {
                    case 'X':
                        counterX++;
                        break;
                    case 'O':
                        counterO++;
                        break;
                    case '_':
                        counterSpace++;
                        break;
                    default:
                        break;
                }
            }
        }

        if (xWon && oWon || Math.abs(counterX - counterO) >= 2) {
            System.out.println("Impossible");
        } else if (!xWon && ! oWon && counterSpace > 0) {
            System.out.println("Game not finished");
        } else if (!xWon && ! oWon && counterSpace == 0) {
            System.out.println("Draw");
        } else if (xWon) {
            System.out.println("X wins");
        } else if (oWon) {
            System.out.println("O wins");
        }
    }
}
