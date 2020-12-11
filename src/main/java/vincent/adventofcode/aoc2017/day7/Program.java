package vincent.adventofcode.aoc2017.day7;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@ToString
class Program {
    @Getter
    private String name;
    @Getter
    private List<Program> to;
    private int weight;

    static List<Program> forInputRules(List<InputRule> inputRules) {
        List<Program> programs = new ArrayList<>();
        for(InputRule inputRule : inputRules) {
            Optional<Program> any = getAny(programs, inputRule.getFrom());
            Program program;
            if(any.isPresent()) {
                program = any.get();
                program.weight = inputRule.getWeight();
            } else {
                program = new Program(inputRule.getFrom(), new ArrayList<>(), inputRule.getWeight());
                programs.add(program);
            }
            for(String s : inputRule.getTo()) {
                Optional<Program> any2 = getAny(programs, s);
                if(any2.isPresent()) {
                    program.to.add(any2.get());
                } else {
                    Program program1 = new Program(s, new ArrayList<>(), 0);
                    program.to.add(program1);
                    programs.add(program1);
                }
            }
        }
        return programs;
    }

    int getWeight() {
        return weight + to.stream().mapToInt(a -> a.weight).sum();
    }

    int getPureWeight() {
        return weight;
    }

    boolean isOff() {
        return to.stream().mapToInt(Program::getWeight).distinct().count() > 1L;
    }

    List<Program> getDeep() {
        ArrayList<Program> programs = new ArrayList<>(to);
        programs.addAll(to.stream().flatMap(a -> a.getDeep().stream()).collect(Collectors.toList()));
        return programs;
    }

    private static Optional<Program> getAny(List<Program> programs, String name) {
        return programs.stream().filter(a -> a.name.equals(name)).findAny();
    }
}
