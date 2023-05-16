package kniffel.states.engine;

import kniffel.Utils;

import java.util.Arrays;
import java.util.InputMismatchException;

public class Engine {
    private final int[] fields = new int[13];
    private final int[] dice = new int [5];
    private int nRolls = 3;
    private final char[] delimiters = {'|', '|', '|', '|', '|'};

    void printGameBlock() {
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
        System.out.print("W: Würfeln  B: Würfel behalten  E: Eintragen  N: Neues Spiel  Q: Beenden ");
    }

    void roll() {
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

    void pickAndKeep() {
        if (nRolls == 3) {
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

    void makeEntry() {
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
            fields[input - 1] = Categories.sumOfAKind(dice, input);
        }

        switch (input) {
            case 7 -> fields[input - 1] = Categories.threeOfAKind(dice);
            case 8 -> fields[input - 1] = Categories.fourOfAKind(dice);
            case 9 -> fields[input - 1] = Categories.fullHouse(dice);
            case 10 -> fields[input - 1] = Categories.smallStraight(dice);
            case 11 -> fields[input - 1] = Categories.largeStraight(dice);
            case 12 -> fields[input - 1] = Categories.kniffel(dice);
            case 13 -> fields[input - 1] = Categories.chance(dice);
        }

        nRolls = 3;
        Arrays.fill(delimiters, '|');
    }
}
