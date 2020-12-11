package vincent.adventofcode.aoc2017.day7;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String... args) {
        List<String> inputStrings = InputDataUtil.getInputStrings(Day.AOC_2017_DAY_7);
        List<InputRule> collect = inputStrings.stream()
                .map(InputRule::forInput)
                .collect(Collectors.toList());
        List<Program> programs = Program.forInputRules(collect);
//        List<String> collect1 = collect.stream().flatMap(a -> a.getTo().stream()).collect(Collectors.toList());
//        List<String> collect2 = collect.stream().map(a -> a.getFrom()).filter(a -> !collect1.contains(a)).collect(Collectors.toList());
//        System.out.println(collect2);
        List<Program> collect1 = programs.stream().filter(Program::isOff).collect(Collectors.toList());
        List<Program> collect2 = collect1.stream().filter(a -> collect1.stream().noneMatch(b -> b.getDeep().contains(a))).collect(Collectors.toList());
        System.out.println(collect2.size());
        System.out.println(collect1.get(0).getTo().get(2).getTo().stream().map(Program::getWeight).collect(Collectors.toList()));


//        System.out.println(collect1);
//        Program collect2 = programs.stream().filter(Program::isOff).filter(a -> a.getWeight() == 3268).findAny().get();
//
//
//        List<Integer> collect3 = collect2.getTo().stream().map(Program::getWeight).collect(Collectors.toList());
//        System.out.println(collect3);
//        List<Program> programs1 = programs.stream().filter(a -> programs.stream().filter(b -> b.getTo().contains(a)).count() > 1).collect(Collectors.toList());
    }
}
