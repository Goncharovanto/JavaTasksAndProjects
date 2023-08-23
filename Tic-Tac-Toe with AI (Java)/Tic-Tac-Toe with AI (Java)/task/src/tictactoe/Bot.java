package tictactoe;

import java.util.Random;

public class Bot {

    int actCordColumn;
    int actCordRow;
    char accessingChar;

    public Bot(char accessingChar) {
        this.accessingChar = accessingChar;
    }

    public int getActCordRow() {
        return actCordRow;
    }

    public int getActCordColumn() {
        return actCordColumn;
    }

    public void setActCordRow(int actCordRow) {
        this.actCordRow = actCordRow;
    }

    public void setActCordColumn(int actCordColumn) {
        this.actCordColumn = actCordColumn;
    }

    public char getAccessingChar() {
        return accessingChar;
    }

    public boolean botEasyMove(char[][] gameField, GameCore object) {
        boolean gameEnded = false;

        System.out.println("Making move level \"easy\"");
        cordGenerator(gameField);

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