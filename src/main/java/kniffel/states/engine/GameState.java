package kniffel.states.engine;

import kniffel.Utils;
import kniffel.states.State;


public class GameState implements State {
    private final Engine engine = new Engine();

    public void handleInput() {
        engine.printGameBlock();
        char input = Utils.readChar();
        switch (input) {
            case 'w', 'W' -> {
                engine.roll();
                handleInput();
            }
            case 'b', 'B' -> {
                engine.pickAndKeep();
                handleInput();
            }
            case 'e', 'E' -> {
                engine.makeEntry();
                handleInput();
            }
            case 'n', 'N' -> restart();
            case 'q', 'Q' -> exit();
            default -> {
                Utils.invalidInputMessage();
                handleInput();
            }
        }
    }


    private void restart() {
        System.out.print("Fortfahren? Alle Punkte gehen dabei verloren. (j/n) ");
        char input = Utils.readChar();
        switch (input) {
            case 'j', 'J' -> new GameState().handleInput();
            default -> handleInput();
        }
    }

    private void exit() {
        System.out.print("Wirklich beenden? Alle Punkte gehen dabei verloren. (j/n) ");
        char input = Utils.readChar();
        switch (input) {
            case 'j', 'J' -> {}
            default -> handleInput();
        }
    }
}
