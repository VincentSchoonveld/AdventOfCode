package vincent.adventofcode.aoc2020.day8;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class Computer implements Runnable {
    private final List<String> program;
    @Getter
    private int accumulator = 0;
    private int executionPosition = 0;
    @Setter
    private int off = -1;

    @Override
    public void run() {
        accumulator = 0;
        executionPosition = 0;
        Set<Integer> executionPositions = new HashSet<>();
        while(executionPosition < program.size() && executionPositions.add(executionPosition)) {
            final String[] s = program.get(executionPosition).split(" ");
            if(executionPosition == off) {
                s[0] = s[0].equals("jmp") ? "nop" : (s[0].equals("nop") ? "jmp" : s[0]);
            }
            if("jmp".equals(s[0])) {
                executionPosition += Integer.parseInt(s[1]);
            } else {
                if("acc".equals(s[0])) {
                    accumulator += Integer.parseInt(s[1]);
                }
                executionPosition++;
            }
        }
    }

    boolean isFinished() {
        return executionPosition >= program.size();
    }
}
