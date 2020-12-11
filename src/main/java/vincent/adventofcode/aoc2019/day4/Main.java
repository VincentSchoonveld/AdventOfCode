package vincent.adventofcode.aoc2019.day4;

import java.util.List;

import static vincent.adventofcode.aoc2019.day4.Numbers.numbersContainingMultiplicityTwo;
import static vincent.adventofcode.aoc2019.day4.Numbers.numbersWithTwoAdjacentAndNonDecreasing;

public class Main {

    private static final int MIN_RANGE = 193651;
    private static final int MAX_RANGE = 649729;

    public static void main(String... args) {
        List<Integer> numbersWithTwoAdjacentAndNonDecreasing = numbersWithTwoAdjacentAndNonDecreasing(MIN_RANGE, MAX_RANGE);
        System.out.println("FIRST PART: ");
        System.out.println(numbersWithTwoAdjacentAndNonDecreasing.size()); //1605
        System.out.println("SECOND PART: ");
        List<Integer> numbersNonDecreasingContainingMultiplicityTwo
                = numbersContainingMultiplicityTwo(numbersWithTwoAdjacentAndNonDecreasing);
        System.out.println(numbersNonDecreasingContainingMultiplicityTwo.size()); //1102
    }
}
