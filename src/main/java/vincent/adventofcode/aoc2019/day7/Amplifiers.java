package vincent.adventofcode.aoc2019.day7;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Amplifiers {
    public static int apply(List<Integer> intcodeProgram, List<Integer> amplitudes, int input) {
        try {
            ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
            List<BlockingQueue<Integer>> wires = IntStream.range(0, amplitudes.size())
                    .mapToObj(c -> new ArrayBlockingQueue<Integer>(2))
                    .collect(toList());
            wires.add(wires.size(), wires.get(0));
            for (int i = 0; i < 5; i++) {
                wires.get(i).put(amplitudes.get(i));
                executor.execute(new Amplifier(new ArrayList<>(intcodeProgram), wires.get(i), wires.get(i + 1)));
            }
            wires.get(0).put(input);
            executor.shutdown();
            executor.awaitTermination(1L, TimeUnit.DAYS);
            return wires.get(wires.size() - 1).poll(10L, TimeUnit.DAYS);
        } catch (InterruptedException t) {
            throw new RuntimeException(t);
        }
    }
}
