package kniffel.states.engine;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoriesTest {
    @Test
    void sumOfAKind_01() {
        int[] dice = {1, 1, 1, 1, 2};
        int points = Categories.sumOfAKind(dice, 1);
        assertThat(points).isEqualTo(4);
    }

    @Test
    void sumOfAKind_02() {
        int[] dice = {6, 1, 3, 2, 5};
        int points = Categories.sumOfAKind(dice, 4);
        assertThat(points).isZero();
    }
    @Test
    void threeOfAKind_01() {
        int[] dice = {2, 3, 4, 2, 2};
        int points = Categories.threeOfAKind(dice);
        assertThat(points).isEqualTo(13);
    }

    @Test
    void threeOfAKind_02() {
        int[] dice = {2, 3, 4, 2, 6};
        int points = Categories.threeOfAKind(dice);
        assertThat(points).isZero();
    }

    @Test
    void fourOfAKind_01() {
        int[] dice = {4, 3, 4, 4, 4};
        int points = Categories.fourOfAKind(dice);
        assertThat(points).isEqualTo(19);
    }

    @Test
    void fourOfAKind_02() {
        int[] dice = {4, 3, 4, 4, 3};
        int points = Categories.fourOfAKind(dice);
        assertThat(points).isZero();
    }

    @Test
    void fullHouse_01() {
        int[] dice = {4, 3, 4, 4, 3};
        int points = Categories.fullHouse(dice);
        assertThat(points).isEqualTo(25);
    }

    @Test
    void fullHouse_02() {
        int[] dice = {4, 3, 4, 2, 3};
        int points = Categories.fullHouse(dice);
        assertThat(points).isZero();
    }


    @Test
    void smallStraight_01() {
        int[] dice = {6, 1, 3, 2, 5};
        int points = Categories.smallStraight(dice);
        assertThat(points).isZero();
    }

    @Test
    void smallStraight_02() {
        int[] dice = {2, 2, 3, 4, 5};
        int points = Categories.smallStraight(dice);
        assertThat(points).isEqualTo(30);
    }

    @Test
    void largeStraight_01() {
        int[] dice = {1, 2, 4, 3, 5};
        int points = Categories.largeStraight(dice);
        assertThat(points).isEqualTo(40);
    }
   @Test
    void largeStraight_02() {
       int[] dice = {1, 1, 4, 2, 3};
        int points = Categories.largeStraight(dice);
        assertThat(points).isZero();
    }

    @Test
    void kniffel_01() {
        int[] dice = {6, 6, 6, 6, 6};
        int points = Categories.kniffel(dice);
        assertThat(points).isEqualTo(50);
    }

    @Test
    void kniffel_02() {
        int[] dice = {1, 1, 1, 1, 2};
        int points = Categories.kniffel(dice);
        assertThat(points).isZero();
    }

    @Test
    void chance() {
        int[] dice = {6, 1, 3, 2, 5};
        int points = Categories.chance(dice);
        assertThat(points).isEqualTo(17);
    }

}
