package vincent.adventofcode.aoc2017.day2;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String... args) {
        List<String> inputStrings = InputDataUtil.getInputStrings(Day.AOC_2017_DAY_2);
        System.out.println(checkSum(inputStrings, Main::difference));
        System.out.println(checkSum(inputStrings, Main::sumDivisions));
    }

    private static int checkSum(List<String> input, Function<List<Integer>, Integer> function) {
        return input.stream()
                .map(
                        inputRow -> Arrays.stream(
                                inputRow.split("\t"))
                                .map(Integer::parseInt)
                                .collect(Collectors.toList())
                )
                .mapToInt(function::apply)
                .sum();

    }

    private static int difference(List<Integer> row) {
        int max = row.stream().max(Integer::compareTo).get();
        int min = row.stream().min(Integer::compareTo).get();
        return max - min;
    }

    private static int sumDivisions(List<Integer> row) {
        int sum = 0;
        for(int i=0; i<row.size()-1; i++) {
            for(int j=i+1; j<row.size(); j++) {
                if(row.get(i)%row.get(j) == 0) {
                    sum += (row.get(i) / row.get(j));
                } else if(row.get(j)%row.get(i) == 0) {
                    sum += (row.get(j) / row.get(i));
                }
            }
        }
        return sum;
    }
}
