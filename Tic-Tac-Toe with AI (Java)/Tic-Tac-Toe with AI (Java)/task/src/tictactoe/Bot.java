package tictactoe;

import java.util.Random;

public class Bot {

    int actCordColumn;
    int actCordRow;

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
        return 'O';
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