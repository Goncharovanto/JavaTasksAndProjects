package tictactoe;

import java.util.Scanner;

public class Player {

    int actCordRow;
    int actCordColumn;
    char accessingChar;

    public Player(char accessingChar) {
        this.accessingChar = accessingChar;
    }

    public int getActCordColumn() {
        return actCordColumn;
    }

    public int getActCordRow() {
        return actCordRow;
    }

    public void setActCordColumn(int acrCordColumn) {
        this.actCordColumn = acrCordColumn;
    }

    public void setActCordRow(int actCordRow) {
        this.actCordRow = actCordRow;
    }

    public char getAccessingChar() {
        return accessingChar;
    }

    public boolean playerMove(char[][] gameField, GameCore object, Scanner sc) {
        boolean gameEnded = false;

        testingAndSettingSymbols(gameField, sc);

        gameField[getActCordRow()][getActCordColumn()] = getAccessingChar();
        object.setSignsCounter(object.getSignsCounter() + 1);
        object.setField(gameField);
        GameCore.printField(object.getField());

        if (GameCore.winingTest(gameField, getActCordRow(), getActCordColumn(), getAccessingChar())) {
            gameEnded = true;
            System.out.printf("%c wins" + '\n', getAccessingChar());

        } else if (object.getSignsCounter() == 9) {
            gameEnded = true;
            System.out.println("Draw" + '\n');
        }
        return gameEnded;
    }

    public void testingAndSettingSymbols(char[][] gameField, Scanner scanner) {
        boolean validInput = false;
        int cordColumn;
        int cordRow;
        String regexForDigits = "\\d\\s\\d";
        String regexForRange = "[1-3]\\s[1-3]";

        while (!validInput) {
            System.out.print("Enter the coordinates: ");
            String input = scanner.nextLine();

            if (!input.matches(regexForDigits)) {
                System.out.println("You should enter numbers!");

            } else if (!input.matches(regexForRange)) {
                System.out.println("Coordinates should be from 1 to 3!");

            } else if (input.matches(regexForDigits) && input.matches(regexForRange)) {
                String[] cordArr = input.split(" ");

                cordRow = Integer.parseInt(cordArr[0]);
                cordColumn = Integer.parseInt(cordArr[1]);

                if (gameField[cordRow - 1][cordColumn - 1] == 'X' ^ gameField[cordRow - 1][cordColumn - 1] == 'O') {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    validInput = true;
                    setActCordRow(cordRow - 1);
                    setActCordColumn(cordColumn - 1);
                }
            }
        }
    }
}