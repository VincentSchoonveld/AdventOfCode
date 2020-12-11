package vincent.adventofcode.aoc2017.day24;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class Main {
    public static void main(String... args) {
        List<String> inputStrings = InputDataUtil.getInputStrings(Day.AOC_2017_DAY_24);
        List<Pin> pins = inputStrings.stream()
                .map(Pin::forInput)
                .collect(Collectors.toList());
        Map<Integer, Integer> strengths = strengths(new Pins(pins));
        System.out.println("FIRST PART:");
        System.out.println(strengths.values().stream().mapToInt(a -> a).max().getAsInt());
        System.out.println("SECOND PART:");
        int max = strengths.keySet().stream().mapToInt(a -> a).max().getAsInt();
        System.out.println(strengths.entrySet().stream().filter(a -> a.getKey() == max).mapToInt(Map.Entry::getValue).max().getAsInt());
    }

    private static Map<Integer, Integer> strengths(Pins pins) {
        Map<Integer, Integer> strengths = new ConcurrentHashMap<>();
        pins.getPinsForNumber(0).parallelStream()
                .forEach(pin -> getStrengths(new Pins().add(pin), pins.remove(pin), strengths));
        return strengths;
    }

    private static void getStrengths(Pins bridge,
                                     Pins pinsRemaining,
                                     Map<Integer, Integer> strengths) {
        int lastNumber = bridge.getLastNumber();
        List<Pin> pinsForNumber = pinsRemaining.getPinsForNumber(lastNumber);
        if(pinsForNumber.isEmpty()) {
            int sum = bridge.sum();
            strengths.compute(bridge.size(), (key, value) -> isNull(value) || value < sum ? sum : value);
        } else {
            pinsForNumber.parallelStream()
                    .forEach(pin -> getStrengths(bridge.add(pin), pinsRemaining.remove(pin), strengths));
        }
    }
}
