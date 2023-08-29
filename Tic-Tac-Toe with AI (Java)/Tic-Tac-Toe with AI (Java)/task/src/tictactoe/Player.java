package tictactoe;

import java.util.Random;
import java.util.Scanner;

public class Player {

    int actCordRow;
    int actCordColumn;
    char accessingChar;

    int counterX;
    int counterO;
    int counterSpace;

    int previousCordRow;
    int previousCordColumn;

    int potentialCordRow;
    int potentialCordColumn;
    public int getPotentialCordColumn() {
        return potentialCordColumn;
    }

    public int getPotentialCordRow() {
        return potentialCordRow;
    }

    public void setPotentialCordColumn(int potentialCordColumn) {
        this.potentialCordColumn = potentialCordColumn;
    }

    public void setPotentialCordRow(int potentialCordRow) {
        this.potentialCordRow = potentialCordRow;
    }

    public int getCounterO() {
        return counterO;
    }

    public int getCounterSpace() {
        return counterSpace;
    }

    public int getCounterX() {
        return counterX;
    }

    public void setCounterO(int counterO) {
        this.counterO = counterO;
    }

    public void setCounterX(int counterX) {
        this.counterX = counterX;
    }

    public void setCounterSpace(int counterSpace) {
        this.counterSpace = counterSpace;
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

    public void setAccessingChar(char accessingChar) {
        this.accessingChar = accessingChar;
    }

    public void setPreviousCordColumn(int previousCordColumn) {
        this.previousCordColumn = previousCordColumn;
    }

    public void setPreviousCordRow(int previousCordRow) {
        this.previousCordRow = previousCordRow;
    }

    public int getPreviousCordColumn() {
        return previousCordColumn;
    }

    public int getPreviousCordRow() {
        return previousCordRow;
    }

    public boolean playerMove(char[][] gameField, GameCore object, Scanner sc) {
        boolean gameEnded = false;

        testingAndSettingSymbols(gameField, sc);

        gameField[getActCordRow()][getActCordColumn()] = getAccessingChar();
        object.setSignsCounter(object.getSignsCounter() + 1);
        object.setField(gameField);
        GameCore.printField(object.getField());

        if (winingTest(gameField, getActCordRow(), getActCordColumn())) {
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

    public void horizontalTest(char[][] chars, int actCordRow) {
        setCounterSpace(0);
        setCounterX(0);
        setCounterO(0);
        for (int i = 0; i < chars.length; i++) {
            if (chars[actCordRow][i] == 'X') {
                setCounterX(getCounterX() + 1);
            } else if (chars[actCordRow][i] == 'O') {
                setCounterO(getCounterO() + 1);
            } else {
                setCounterSpace(getCounterSpace() + 1);
                setPotentialCordRow(actCordRow);
                setPotentialCordColumn(i);
            }
        }
    }

    public void verticalTest(char[][] chars, int actCordColumn) {
        setCounterSpace(0);
        setCounterX(0);
        setCounterO(0);
        for (int i = 0; i < chars.length; i++) {
            if (chars[i][actCordColumn] == 'X') {
                setCounterX(getCounterX() + 1);
            } else if (chars[i][actCordColumn] == 'O') {
                setCounterO(getCounterO() + 1);
            } else {
                setCounterSpace(getCounterSpace() + 1);
                setPotentialCordRow(i);
                setPotentialCordColumn(actCordColumn);
            }
        }
    }

    public void leftToRightDiagonalTest(char[][] chars) {
        setCounterSpace(0);
        setCounterX(0);
        setCounterO(0);
        for (int i = 0; i < chars.length; i++) {
            if (chars[i][i] == 'X') {
                setCounterX(getCounterX() + 1);
            } else if (chars[i][i] == 'O') {
                setCounterO(getCounterO() + 1);
            } else {
                setCounterSpace(getCounterSpace() + 1);
                setPotentialCordRow(i);
                setPotentialCordColumn(i);
            }
        }
    }


    public void rightToLeftDiagonalTest(char[][] chars) {
        setCounterSpace(0);
        setCounterX(0);
        setCounterO(0);
        int index = chars.length - 1;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i][index] == 'X') {
                setCounterX(getCounterX() + 1);
            } else if (chars[i][index] == 'O') {
                setCounterO(getCounterO() + 1);
            } else {
                setCounterSpace(getCounterSpace() + 1);
                setPotentialCordRow(i);
                setPotentialCordColumn(index);
            }
            index--;
        }
    }

    public boolean winingTest (char[][] chars, int actCordRow, int actCordColumn) {
        boolean win = false;

        horizontalTest(chars, actCordRow);
        if (getCounterX() == 3 || getCounterO() == 3) {
            win = true;
        }
        verticalTest(chars, actCordColumn);
        if (getCounterX() == 3 || getCounterO() == 3) {
            win = true;
        }

        if (actCordRow == actCordColumn && actCordRow != 1) {

            leftToRightDiagonalTest(chars);
            if (getCounterX() == 3 || getCounterO() == 3) {
                win = true;
            }
        } else if (actCordColumn != actCordRow && actCordColumn != 1 && actCordRow != 1) {

        rightToLeftDiagonalTest(chars);
            if (getCounterX() == 3 || getCounterO() == 3) {
                win = true;
            }

        } else if (actCordRow == 1 && actCordColumn == 1) {

            rightToLeftDiagonalTest(chars);
            if (getCounterX() == 3 || getCounterO() == 3) {
                win = true;
            }
            leftToRightDiagonalTest(chars);
            if (getCounterX() == 3 || getCounterO() == 3) {
                win = true;
            }
        }
        return win;
    }

    public boolean botMediumMove(char[][] gameField, GameCore object) {
        boolean gameEnded = false;

        System.out.println("Making move level \"medium\"");

        if (opportunityTest(gameField, getPreviousCordRow(), getPreviousCordColumn())) {
            setActCordRow(getPotentialCordRow());
            setActCordColumn(getPotentialCordColumn());


        } else if (opportunityTest(gameField, getActCordRow(), getActCordColumn())) {
            setActCordRow(getPotentialCordRow());
            setActCordColumn(getPotentialCordColumn());

        } else {
            cordGenerator(gameField);
        }
        gameField[getActCordRow()][getActCordColumn()] = getAccessingChar();

        setPreviousCordRow(getActCordRow());
        setPreviousCordColumn(getActCordColumn());

        object.setSignsCounter(object.getSignsCounter() + 1);
        object.setField(gameField);

        GameCore.printField(object.getField());

        if (winingTest(gameField, getActCordRow(), getActCordColumn())) {
            gameEnded = true;
            System.out.printf("%c wins" + '\n', getAccessingChar());
        } else if (object.getSignsCounter() == 9) {
            gameEnded = true;
            System.out.println("Draw" + '\n');
        }
        return gameEnded;
    }

    public boolean botEasyMove(char[][] gameField, GameCore object) {
        boolean gameEnded = false;

        System.out.println("Making move level \"easy\"");
        cordGenerator(gameField);

        gameField[getActCordRow()][getActCordColumn()] = getAccessingChar();
        object.setSignsCounter(object.getSignsCounter() + 1);
        object.setField(gameField);
        GameCore.printField(object.getField());

        if (winingTest(gameField, getActCordRow(), getActCordColumn())) {
            gameEnded = true;
            System.out.printf("%c wins" + '\n', getAccessingChar());

        } else if (object.getSignsCounter() == 9) {
            gameEnded = true;
            System.out.println("Draw" + '\n');
        }
        return gameEnded;
    }


    public boolean opportunityCheck() {
        boolean opportunity = false;

        if (getCounterX() == 2 && getCounterSpace() == 1 && getAccessingChar() == 'X') {
            opportunity = true;
        } else if (getCounterO() == 2 && getCounterSpace() == 1 && getAccessingChar() == 'O') {
            opportunity = true;
        } else if (getCounterX() == 2 && getCounterSpace() == 1 && getAccessingChar() != 'X') {
            opportunity = true;
        } else if (getCounterO() == 2 && getCounterSpace() == 1 && getAccessingChar() != 'O') {
            opportunity = true;
        }
        return opportunity;
    }

    public boolean opportunityTest(char[][] chars, int actCordRow, int actCordColumn) {
        boolean candidateFind = false;

        horizontalTest(chars, actCordRow);

        if (opportunityCheck()) {
            candidateFind = true;

        }
        if (!candidateFind) {
            verticalTest(chars, actCordColumn);
            if (opportunityCheck()) {
                candidateFind = true;

            }
        }

        if (!candidateFind && actCordRow == actCordColumn && actCordRow != 1) {

            leftToRightDiagonalTest(chars);
            if (opportunityCheck()) {
                candidateFind = true;

            }

        }
        if (!candidateFind && actCordColumn != actCordRow && actCordColumn != 1 && actCordRow != 1) {

            rightToLeftDiagonalTest(chars);
            if (opportunityCheck()) {
                candidateFind = true;

            }

        }
        if (!candidateFind && actCordRow == 1 && actCordColumn == 1) {

            leftToRightDiagonalTest(chars);
            if (opportunityCheck()) {
                candidateFind = true;

            }
            if (!candidateFind) {

                rightToLeftDiagonalTest(chars);
                if (opportunityCheck()) {
                    candidateFind = true;

                }
            }
        }
        return candidateFind;
    }

    public void cordGenerator(char[][] gameField) {
        Random rnd = new Random();
        boolean validCord = false;

        while (!validCord) {
            int cordRow = rnd.nextInt(3);
            int cordColumn = rnd.nextInt(3);

            if (gameField[cordRow][cordColumn] != 'X'&& gameField[cordRow][cordColumn] != 'O') {
                setActCordRow(cordRow);
                setActCordColumn(cordColumn);
                validCord = true;
            }
        }
    }
}
