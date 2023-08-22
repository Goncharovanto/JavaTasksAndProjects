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

    public void game() {
        Scanner sc = new Scanner(System.in);
        Player player = new Player();
        Bot bot = new Bot();

        drawingField(getField());
        printField(getField());

        gameState(getField(), player, bot, sc);

    }

    public void gameState(char[][] chars, Player player, Bot bot, Scanner sc) {
        while (true) {
            player.testingAndSettingSymbols(chars, sc);
            chars[player.getActCordRow()][player.getActCordColumn()] = player.getAccessingChar();
            setSignsCounter(getSignsCounter() + 1);
            setField(chars);
            printField(getField());
            if (winingTest(chars, player.getActCordRow(), player.getActCordColumn(), player.getAccessingChar())) {
                System.out.println("X wins");
                break;
            } else if (getSignsCounter() == 9) {
                System.out.println("Draw");
                break;
            } else {
                System.out.println("Making move level \"easy\"");
                bot.cordGenerator(chars);
                chars[bot.getActCordRow()][bot.getActCordColumn()] = bot.getAccessingChar();
                setSignsCounter(getSignsCounter() + 1);
                setField(chars);
                printField(getField());
                if (winingTest(chars, bot.getActCordRow(), bot.getActCordColumn(), bot.getAccessingChar())) {
                    System.out.println("O wins");
                    break;
                }
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