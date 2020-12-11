package vincent.adventofcode.aoc2019.day5.opcode;

import vincent.adventofcode.aoc2019.day5.ProgramState;

import java.util.List;

public class OpCodeInput implements OpCodeApplier {
    @Override
    public ProgramState apply(ProgramState programState, int input) {
        List<Integer> state = programState.getState();
        int index = programState.getIndex();
        state.set(state.get(index+1), input);
        return new ProgramState(state, index + 2);
    }
}
