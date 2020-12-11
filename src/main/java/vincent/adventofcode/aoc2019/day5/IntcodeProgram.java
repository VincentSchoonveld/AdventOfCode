package vincent.adventofcode.aoc2019.day5;

import java.util.ArrayList;
import java.util.List;

class IntcodeProgram {
    private ProgramState programState;
    
    IntcodeProgram(List<Integer> state) {
        this.programState = new ProgramState(new ArrayList<>(state), 0);
    }

    void execute(int input) {
        while(!programState.isFinished()) {
            playOnce(input);
        }
    }

    private void playOnce(int input) {
        programState = programState.getOpCode()
                .apply(programState, input);
    }
}
