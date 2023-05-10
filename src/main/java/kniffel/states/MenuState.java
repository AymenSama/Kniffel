package kniffel.states;

import kniffel.Launcher;
import kniffel.Utils;

public class MenuState implements State {
    public void handleInput(){
        printMenuMessage();
        System.out.print("L: Lizenz anzeigen  N: Neues Spiel ");
        char input = Utils.readChar();
        switch (input) {
            case 'l', 'L' -> licenseMenu();
            case 'n', 'N' -> new GameState().handleInput();
            default -> {
                Utils.invalidInputMessage();
                handleInput();
            }
        }
    }

    private void licenseMenu() {
        Utils.printLicense();
        System.out.print("N: Neues Spiel ");
        char input = Utils.readChar();
        if (input == 'n' || input == 'N') {
            new GameState().handleInput();
        } else {
            Utils.invalidInputMessage();
            licenseMenu();
        }
    }
    private static void printMenuMessage() {
        System.out.print("\n\nKniffel in c++ " + Launcher.version + "\n" +
                "Copyright (C) 2009 Markus Brenneis\n" +
                "\n" +
                "Für dieses Programm besteht KEINERLEI GARANTIE.\n" +
                "Dies ist freie Software, die Sie unter bestimmten Bedingungen weitergeben dürfen.\n" +
                "\n" +
                "Geben Sie L für Details ein.\n" +
                "\n" +
                "\n" +
                "\n");
    }
}
