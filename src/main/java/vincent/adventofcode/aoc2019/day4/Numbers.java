package vincent.adventofcode.aoc2019.day4;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class Numbers {

    static List<Integer> numbersWithTwoAdjacentAndNonDecreasing(int min, int max) {
        return IntStream.rangeClosed(min, max)
                .filter(Numbers::hasTwoAdjacentDigitsAndIsNonDecreasing)
                .boxed()
                .collect(Collectors.toList());
    }

    private static boolean hasTwoAdjacentDigitsAndIsNonDecreasing(int i) {
        char[] chars = String.valueOf(i).toCharArray();
        boolean hasTwoAdjacentCharacters = false;
        boolean isNonDecreasing = true;
        for(int j=0; j<5; j++) {
            char first = chars[j];
            char second = chars[j + 1];
            if(first == second) {
                hasTwoAdjacentCharacters = true;
            } else if(first > second) {
                isNonDecreasing = false;
            }
        }
        return hasTwoAdjacentCharacters && isNonDecreasing;
    }

    static List<Integer> numbersContainingMultiplicityTwo(List<Integer> list) {
        return list.stream()
                .filter(Numbers::hasMultiplicityNotPartOfBiggerRecurrence)
                .collect(Collectors.toList());
    }

    private static boolean hasMultiplicityNotPartOfBiggerRecurrence(int i) {
        return String.valueOf(i).chars()
                .boxed()
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                Collectors.counting()
                        ))
                .containsValue(2L);
    }
}
