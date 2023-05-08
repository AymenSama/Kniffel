/*
Kniffel in c++ (basierend auf Kniffel in StarBasic)
Copyright (C) 2009 Markus Brenneis
(2023 nach Java portiert)

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation; either version 3 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program; if not, see <http://www.gnu.org/licenses/>.
*/
package kniffel;

public class Launcher {
    static String version="v1.0a1-090714", lang="lang_en";

    public static void main(String[] args){
        if(args.length>0) {
            String par=args[0];
            handlePar(par);
            return;
        }

        mainMenu();
    }

    private static void handlePar(String par) {
        if (par.equals("--help") || par.equals("/?") || par.equals("-h")){
            System.out.print("Kniffel in c++ (basierend auf Kniffel in StarBasic) " + version + "\n\n");
            System.out.print("-c -no-gui    Ohne grafische Benutzeroberfläche starten\n");
            System.out.print("-l --lizenz   Lizenz anzeigen\n");
            System.out.print("-h --help /?  Hilfe anzeigen\n");
        } else if (par.equals("-l") || par.equals("--lizenz")) {
            Utils.printLizenz();
        } else {
            System.out.print("Ungültige(r) Parameter ignoriert.\nVersuchen Sie --help für mehr Informationen.\n");
        }
    }

    static void mainMenu(){
        Utils.printMenuMessage();
        System.out.print("L: Lizenz anzeigen  N: Neues Spiel ");
        char menu = Utils.readChar();
        switch (menu) {
            case 'l', 'L' -> licenseMenu();
            case 'n', 'N' -> Engine.reset();
            default -> {
                System.out.print("Ungültige Eingabe\n\n");
                mainMenu();
            }
        }
    }

    static void licenseMenu() {
        Utils.printLizenz();
        char menu;
        System.out.print("N: Neues Spiel ");
        menu = Utils.readChar();
        if (menu == 'n' || menu == 'N') {
            Engine.reset();
        } else {
            System.out.print("Ungültige Eingabe\n\n");
            licenseMenu();
        }

    }
}