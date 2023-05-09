package kniffel.states;

import kniffel.Launcher;
import kniffel.Utils;

public class MenuState implements State {
    public void handleInput(){
        printMenuMessage();
        System.out.print("L: Lizenz anzeigen  N: Neues Spiel ");
        char menu = Utils.readChar();
        switch (menu) {
            case 'l', 'L' -> licenseMenu();
            case 'n', 'N' -> new GameState().handleInput();
            default -> {
                System.out.print("Ungültige Eingabe\n\n");
                handleInput();
            }
        }
    }

    private void licenseMenu() {
        Utils.printLicense();
        System.out.print("N: Neues Spiel ");
        char menu = Utils.readChar();
        if (menu == 'n' || menu == 'N') {
            new GameState().handleInput();
        } else {
            System.out.print("Ungültige Eingabe\n\n");
            licenseMenu();
        }
    }
    private static void printMenuMessage() {
        System.out.print("\n\nKniffel in c++ " + Launcher.version + "\nCopyright (C) 2009 Markus Brenneis\n\nFür dieses Programm besteht KEINERLEI GARANTIE.\nDies ist freie Software, die Sie unter bestimmten Bedingungen weitergeben dürfen.\n\nGeben Sie L für Details ein.\n\n\n\n");
    }
}
