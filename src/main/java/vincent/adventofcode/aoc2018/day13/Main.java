package vincent.adventofcode.aoc2018.day13;

import vincent.adventofcode.util.Day;
import vincent.adventofcode.util.data.InputDataUtil;

import java.util.List;

public class Main {
    public static void main(String... args) {
        List<String> input = InputDataUtil.getInputStrings(Day.AOC_2018_DAY_13);
        TrackMap trackMap = TrackMap.forInput(input);
        Trains trains = Trains.forInput(input);
        final int originalNumberOfTrains = trains.getNumber();
        Index index = null;
        System.out.println("FIRST PART:");
        while(originalNumberOfTrains == trains.getNumber()) {
            index = trains.next(trackMap);
        }
        System.out.println(index);
        System.out.println("SECOND PART:");
        while(trains.getNumber() > 1) {
            trains.next(trackMap);
        }
        index = trains.next(trackMap);
        System.out.println(index);
    }
}
