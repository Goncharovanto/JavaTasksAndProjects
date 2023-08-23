package tictactoe;

import java.util.Scanner;

public class GameCore {
    char[][] Field = new char[3][3];

    int signsCounter;

    public int getSignsCounter() {
        return signsCounter;
    }

    public void setSignsCounter(int signsCounter) {
        this.signsCounter = signsCounter;
    }

    public char[][] getField() {
        return Field;
    }

    public void setField(char[][] Field) {
        this.Field = Field;
    }

    @SuppressWarnings("all")
    public void drawingField(char[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int k = 0; k < array[i].length; k++) {
                array[i][k] = ' ';
            }
        }
        setField(array);
    }

    @SuppressWarnings("ForLoopReplaceableByForEach")
    public static void printField(char[][] chars) {
        System.out.print("---------");
        for (int i = 0; i < chars.length; i++) {
            System.out.println();
            for (int k = 0; k < chars[i].length; k++) {
                if (k == 0) {
                    System.out.print("| ");
                }
                System.out.print(chars[i][k] + " ");
                if (k == 2) {
                    System.out.print("|");
                }
            }
        }
        System.out.println('\n' + "---------");
    }

    @SuppressWarnings("all")
    public void game(GameCore object) {
        Scanner sc = new Scanner(System.in);

        Bot botX = new Bot('X');
        Bot botO = new Bot('O');
        Player playerX = new Player('X');
        Player playerO = new Player('O');

        boolean exit = false;

        while (!exit) {
            System.out.print("Input command: ");

            switch (sc.nextLine()) {
                case "start easy easy":

                    setSignsCounter(0);
                    drawingField(getField());
                    printField(getField());
                    while (true) {
                        if (botX.botEasyMove(getField(), object)) {
                            break;
                        } else if (botO.botEasyMove(getField(), object)) {
                            break;
                        }
                    }
                    break;
                case "start user user":

                    setSignsCounter(0);
                    drawingField(getField());
                    printField(getField());
                    while (true) {
                        if (playerX.playerMove(getField(), object, sc)) {
                            break;
                        } else if (playerO.playerMove(getField(), object, sc)) {
                            break;
                        }
                    }
                    break;
                case "start user easy":

                    setSignsCounter(0);
                    drawingField(getField());
                    printField(getField());
                    while (true) {
                        if (playerX.playerMove(getField(), object, sc)) {
                            break;
                        } else if (botO.botEasyMove(getField(), object)) {
                            break;
                        }
                    }
                    break;
                case "start easy user":

                    setSignsCounter(0);
                    drawingField(getField());
                    printField(getField());
                    while (true) {
                        if (botX.botEasyMove(getField(), object)) {
                            break;
                        } else if (playerO.playerMove(getField(), object, sc)) {
                            break;
                        }
                    }
                    break;
                case "exit":
                    exit = true;
                    break;
                default:
                    System.out.println("Bad parameters!");
                    break;
            }
        }
    }


    public static boolean winingTest(char[][] chars, int actCordRow, int actCordColumn, char testingChar) {
        boolean win = false;

        if (actCordRow == actCordColumn && actCordRow != 1) {
            if (horizontalTest(chars, actCordRow, testingChar) || verticalTest(chars, actCordColumn, testingChar) ||
                    leftToRightDiagonalTest(chars, testingChar)) {
                win = true;
            }
        } else if (actCordColumn != actCordRow && actCordColumn != 1 && actCordRow != 1) {
            if (horizontalTest(chars, actCordRow, testingChar) || verticalTest(chars, actCordColumn, testingChar) ||
                    rightToLeftDiagonalTest(chars, testingChar)) {
                win = true;
            }
        } else if (actCordRow == 1 && actCordColumn == 1) {
            if (horizontalTest(chars, actCordRow, testingChar) || verticalTest(chars, actCordColumn, testingChar) ||
                    rightToLeftDiagonalTest(chars, testingChar) || leftToRightDiagonalTest(chars, testingChar)) {
                win = true;
            }
        } else {
            if (horizontalTest(chars, actCordRow, testingChar) || verticalTest(chars, actCordColumn, testingChar)) {
                win = true;
            }
        }
        return win;
    }

    public static boolean horizontalTest(char[][] chars, int actCordRow, char testingChar) {
        boolean win = false;
        for (int i = 0; i < chars.length; i++) {
            if (chars[actCordRow][i] != testingChar) {
                win = false;
                break;
            } else {
                win = true;
            }
        }
        return win;
    }

    @SuppressWarnings("ForLoopReplaceableByForEach")
    public static boolean verticalTest(char[][] chars, int actCordColum, char testingChar) {
        boolean win = false;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i][actCordColum] != testingChar) {
                win = false;
                break;
            } else {
                win = true;
            }
        }
        return win;
    }

    public static boolean leftToRightDiagonalTest(char[][] chars, char testingChar) {
        boolean win = false;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i][i] != testingChar) {
                win = false;
                break;
            } else {
                win = true;
            }
        }
        return win;
    }

    @SuppressWarnings("ForLoopReplaceableByForEach")
    public static boolean rightToLeftDiagonalTest(char[][] chars, char testingChar) {
        boolean win = false;
        int index = chars.length - 1;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i][index--] != testingChar) {
                win = false;
                break;
            } else {
                win = true;
            }
        }
        return win;
    }
}