package tictactoe;

import java.util.Scanner;

public class GameCore {
    char[][] Field = new char[3][3];
    String[] input;
    GameState state;

    String parameter;

    int index;

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public void setInput(String[] input) {
        this.input = input;
    }

    public String[] getInput() {
        return input;
    }

    public void setState(GameState state) {
        this.state = state;
    }

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

    @SuppressWarnings("unused")
    public enum GameState {
        USER,
        EASY_BOT,
        MEDIUM_BOT,
        HARD_BOT,
        EXIT,
        WAITING_FOR_INPUT,
        GAMING_PROCESS
    }

    @SuppressWarnings("all")
    public void gettingInput(Scanner sc) {
        System.out.print("Input command: ");
        String input = sc.nextLine();
        String inputReg = "\\w*\\s\\w*\\s\\w*";

        if (input.matches("exit")) {
            setState(GameState.EXIT);
        } else if (input.matches(inputReg)) {
            String[] array = input.split(" ");
            setInput(array);
            setParameter(array[1]);
            setIndex(1);
            setState(GameState.GAMING_PROCESS);
            setSignsCounter(0);
            drawingField(getField());
            printField(getField());
        } else {
            System.out.println("Bad parameters!");
        }
    }

    public void switchingMove(String str, String[] array, int index, GameCore object, Scanner sc, Player player) {


        switch (index) {
            case 1 -> player.setAccessingChar('X');
            case 2 -> player.setAccessingChar('O');
        }

        switch (str) {
            case "user":
                if (player.playerMove(getField(), object, sc)) {
                    setState(GameState.WAITING_FOR_INPUT);
                }
                break;
            case "easy":
                if (player.botEasyMove(getField(), object)) {
                    setState(GameState.WAITING_FOR_INPUT);
                }
                break;
            case "medium":
                if (player.botMediumMove(getField(), object)) {
                    setState(GameState.WAITING_FOR_INPUT);
                }
        }
        if (getIndex() == 1) {
            setParameter(array[2]);
            setIndex(2);
        } else {
            setParameter(array[1]);
            setIndex(1);
        }
    }


    @SuppressWarnings("all")
    public void game(GameCore object) {
        Scanner sc = new Scanner(System.in);
        Player player = new Player();

        boolean exit = false;
        setState(GameState.WAITING_FOR_INPUT);

        while (!exit) {

            switch (state) {
                case WAITING_FOR_INPUT:
                    gettingInput(sc);
                    break;
                case GAMING_PROCESS:
                    switchingMove(getParameter(), getInput(), getIndex(), object, sc, player);
                    break;
                case EXIT:
                    exit = true;
                    break;

            }
        }
    }
}