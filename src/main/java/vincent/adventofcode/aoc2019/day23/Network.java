package vincent.adventofcode.aoc2019.day23;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class Network {
    public static void apply(List<Long> intcodeProgram, int size, boolean hasResetMechanism) {
        try {
            ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(size);
            InputOutputHandler inputOutputHandler = new InputOutputHandler(size, hasResetMechanism);
            for (int i = 0; i < size; i++) {
                executor.execute(new NetworkInterfaceController(new ArrayList<>(intcodeProgram), inputOutputHandler, i));
            }
            executor.shutdown();
            executor.awaitTermination(1L, TimeUnit.DAYS);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
