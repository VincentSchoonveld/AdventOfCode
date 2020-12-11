package vincent.adventofcode.aoc2017.day4;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;

public class Main {
    public static void main(String... args) {
        List<String> inputStrings = InputDataUtil.getInputStrings(Day.AOC_2017_DAY_4);
        System.out.println("FIRST PART");
        System.out.println(numberOfValidPassphrases(inputStrings, Main::containsNoDuplicateWords));
        System.out.println("SECOND PART");
        System.out.println(numberOfValidPassphrases(inputStrings, Main::containsNoAnagramWords));
    }

    private static long numberOfValidPassphrases(List<String> possiblePassphrases,
                                                 Predicate<String> isValidPassphrase) {
        return possiblePassphrases.stream()
                .filter(isValidPassphrase)
                .count();
    }

    private static boolean containsNoDuplicateWords(String possiblePassphrase) {
        List<String> ls = asList(possiblePassphrase.split(" "));
        return ls.size() == new HashSet<>(ls).size();
    }

    private static boolean containsNoAnagramWords(String possiblePassphrase) {
        List<Map<String, Long>> ls = stream(possiblePassphrase.split(" "))
                .map(s -> stream(s.split("")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())))
                .collect(Collectors.toList());
        return ls.size() == new HashSet<>(ls).size();
    }
}
