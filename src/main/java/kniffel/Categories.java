package kniffel;

import java.util.Arrays;

public class Categories {
    public static int sumOfAKind(int[] dice, int n) {
        return Arrays.stream(dice)
                .filter(d -> d == n)
                .sum();
    }

    public static int threeOfAKind(int[] dice) {
        return ofAKind(dice, 3);
    }

    private static int ofAKind(int[] dice, int x) {
        int[] copy = sortedCopy(dice);
        long n = Arrays.stream(copy)
                .filter(e -> e == copy[2])
                .count();

        if (n >= x) {
            return sumDice(dice);
        }
        return 0;
    }

    private static int sumDice(int[] dice) {
        return Arrays.stream(dice).sum();
    }

    public static int fourOfAKind(int[] dice) {
        return ofAKind(dice, 4);
    }

    public static int fullHouse(int[] dice) {
        int[] copy = sortedCopy(dice);

        if (copy[0] == copy[1] && copy[3] == copy[4] && (copy[2] == copy[1] || copy[2] == copy[3])) {
            return 25;
        }
        return 0;
    }

    public static int smallStraight(int[] dice) {
        long x = distinctSequenceCount(dice, 1, 4);

        long y = distinctSequenceCount(dice, 2, 5);

        long z = distinctSequenceCount(dice, 3, 6);

        if(x == 4 || y == 4 || z == 4) {
            return 30;
        }
        return 0;
    }

    private static long distinctSequenceCount(int[] dice, int lowerBound, int upperBound) {
        return Arrays.stream(dice)
                .filter(e -> e >= lowerBound && e <= upperBound)
                .distinct()
                .count();
    }

    public static int largeStraight(int[] dice) {
        long x = distinctSequenceCount(dice, 1, 5);

        long y = distinctSequenceCount(dice, 2, 6);

        if (x == 5 || y == 5) {
            return 40;
        }
        return 0;
    }

    public static int kniffel(int[] dice) {
        boolean allEqual = Arrays.stream(dice).allMatch(e -> e == dice[0]);

        if (allEqual) {
            return 50;
        }
        return 0;
    }

    public static int chance(int[] dice) {
        return sumDice(dice);
    }

    private static int[] sortedCopy(int[] dice) {
        int[] copy = Arrays.copyOf(dice, dice.length);
        Arrays.sort(copy);
        return copy;
    }

}
