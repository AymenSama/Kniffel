package kniffel.states;

import kniffel.Utils;

import java.util.Arrays;
import java.util.InputMismatchException;

public class GameState implements State {
    int[] fields = new int[13];

    // int[] feldges = new int[13];

    int[] dice = new int [5];
    int[] wurfels = new int [5];
    int nRolls = 3;
    char[] delimiters = {'|', '|', '|', '|', '|'};

    public void handleInput() {
        printGameBlock();
        printFooter();
        char input = Utils.readChar();
        switch (input) {
            case 'w', 'W' -> {
                roll();
                handleInput();
            }
            case 'b', 'B' -> {
                pickAndKeep();
                handleInput();
            }
            case 'e', 'E' -> {
                makeEntry();
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

    private static void printFooter() {
        System.out.print("W: Würfeln  B: Würfel behalten  E: Eintragen  N: Neues Spiel  Q: Beenden ");
    }


    private void printGameBlock() {
        int upperTotal = Arrays.stream(fields)
                .limit(6)
                .sum();

        int bonus = 0;
        if(upperTotal >= 64) {
            bonus=35;
        }

        int bottomTotal = Arrays.stream(fields)
                .skip(6)
                .sum();

        int upperTotalWithBonus = upperTotal + bonus;
        int total = bottomTotal + upperTotalWithBonus;

        System.out.print("\n\n\n\n\n");
        System.out.print("----------------------------------- Kniffel ------------------------------------\n");
        System.out.print("1:  1er            | " + fields[0] + "\n" +
                "2:  2er            | " + fields[1] + "\n" +
                "3:  3er            | " + fields[2] + "\n" +
                "4:  4er            | " + fields[3] + "\n" +
                "5:  5er            | " + fields[4] + "\n" +
                "6:  6er            | " + fields[5] + "\n" +
                "    Gesamt         | " + upperTotal + "\n" +
                "    Bonus          | " + bonus + "\n" +
                "    Gesamt + Bonus | " + upperTotalWithBonus + "\n\n" +
                "7:  Dreierpasch    | " + fields[6] + "\n" +
                "8:  Viererpasch    | " + fields[7] + "\n" +
                "9:  Full House     | " + fields[8] + "\n" +
                "10: Kleine Straße  | " + fields[9] + "\n" +
                "11: Große Straße   | " + fields[10] + "\n" +
                "12: Kniffel        | " + fields[11] + "\n" +
                "13: Chance         | " + fields[12] + "\n" +
                "    Gesamt (unten) | " + bottomTotal + "\n" +
                "    Gesamt (oben)  | " + upperTotalWithBonus + "\n" +
                "    Gesamt         | " + total + "\n\n"
                + delimiters[0] + dice[0] + delimiters[0] + "  " + delimiters[1] + dice[1] + delimiters[1] + "  "
                + delimiters[2] + dice[2] + delimiters[2] + "  " + delimiters[3] + dice[3] + delimiters[3] + "  "
                + delimiters[4] + dice[4] + delimiters[4] + "  noch " + nRolls + " mal\n\n");
    }

    private void roll() {
        if(nRolls == 0) {
            return;
        }

        for (int i = 0; i < dice.length; i++) {
            if (delimiters[i]=='|') {
                dice[i]=(int)(Math.random() * 6 + 1);
            }
        }
        nRolls--;
    }

    private void pickAndKeep() {
        if (nRolls ==3) {
            return;
        }

        System.out.print("Zu behaltener Würfel: ");
        int input;

        try {
            input = Utils.readInt();
        } catch (InputMismatchException e) {
            Utils.invalidInputMessage();
            return;
        }

        if (input < 1 || input > 5) {
            Utils.invalidInputMessage();
            return;
        }

        delimiters[input-1] = delimiters[input-1]=='|' ? '*' : '|';
    }

    private void makeEntry() {
        if (nRolls ==3) {
            return;
        }

        System.out.print("Eintragung bei: ");
        int input;

        try {
            input = Utils.readInt();
        } catch (InputMismatchException e) {
            Utils.invalidInputMessage();
            return;
        }

        if (input > 13 || input < 1) {
            Utils.invalidInputMessage();
            return;
        }

        calculateField(input);
    }

    private void calculateField(int input) {
        if (fields[input-1] != 0) {
            System.out.print(input + " schon besetzt");
            return;
        }

        if (input < 7) {
            fields[input - 1] = sumOfAKind(input);
        }

        sort();

        if (input == 7 || input == 8) {
            int n=0;
            for (int i=0; i<5; i++) {
                if (wurfels[i]==wurfels[2]) { n++; }
            }
            if (n> input -5) {
                for (int i=0; i<5; i++) { fields[input -1]+=wurfels[i]; }
            }
        } else if (input == 9) {
            if(wurfels[0]==wurfels[1] && wurfels[3]==wurfels[4] && (wurfels[2]==wurfels[1] || wurfels[2]==wurfels[3])) {
                fields[input -1]=25;
            }
        } else if (input ==10 || input ==11) {
            int m=0;
            if (input ==10) {
                for(int o=0; o<3; o++) {
                    for(int n=1+o; n<5+o; n++){
                        for (int i=0; i<5; i++) {
                            if (wurfels[i]==n) { m++; break; }
                        }
                    }
                    if (m>3) {break;} else {m=0;}
                }
            }
            if (input ==11) {
                for(int o=0; o<2; o++) {
                    for(int n=1+o; n<6+o; n++){
                        for (int i=0; i<5; i++) {
                            if (wurfels[i]==n) { m++; break; }
                        }
                    }
                    if (m>4) {break;} else {m=0;}
                }
            }
            if (m> input -7) { fields[input -1]=30+(input -10)*10; }
        } else if (input == 12) {
            int n=0;
            for(int i=1; i<5; i++) {
                if (wurfels[i]==wurfels[0]) { n++; }
            }
            if (n==5) { fields[11]=50; }
        } else if (input == 13) {
            for (int i=0; i<5; i++) {
                fields[12]+=wurfels[i];
            }
        }

        // feldges[input -1]=1;
        nRolls =3;
        for(int i=0; i<5; i++) {
            delimiters[i]='|';}
    }

    private int sumOfAKind(int input) {
        return Arrays.stream(dice)
                .filter(d -> d == input)
                .sum();
    }

    int sort(){
        for (int i=0; i<5; i++) { wurfels[i]= dice[i]; }
        for(int grenze=4;grenze>0;--grenze) {
            for(int pos=0, c1;pos<grenze;++pos) {
                if(wurfels[pos]>wurfels[pos+1]){
                    c1=wurfels[pos];
                    wurfels[pos]=wurfels[pos+1];
                    wurfels[pos+1]=c1;
                }
            }
        }
        return 0;
    }
}
