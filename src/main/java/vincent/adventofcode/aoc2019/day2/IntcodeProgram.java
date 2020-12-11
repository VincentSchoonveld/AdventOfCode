package vincent.adventofcode.aoc2019.day2;

import lombok.ToString;

import java.util.List;

@ToString
class IntcodeProgram {
    private List<Integer> state;

    IntcodeProgram(List<Integer> state, int noun, int verb) {
        this.state = state;
        this.state.set(1, noun);
        this.state.set(2, verb);
    }

    List<Integer> getFinishedState() {
        runProgram();
        return state;
    }

    private void runProgram() {
        int i=0;
        while(!isFinished(i)) {
            playOnce(i);
            i=i+4;
        }
    }

    private boolean isFinished(int i) {
        return i>= state.size()
                || state.get(i) == 99;
    }

    private void playOnce(int index) {
        switch(state.get(index)) {
            case 1: add(index); return;
            case 2: multiply(index); return;
            default: throw new IllegalArgumentException("The number is not known: "+ state.get(index));
        }
    }

    private void add(int index) {
        int sum = get(get(index + 1)) + get(get(index + 2));
        state.set(get(index+3), sum);
    }

    private void multiply(int index) {
        int product = get(get(index + 1)) * get(get(index + 2));
        state.set(get(index+3), product);
    }

    private Integer get(int index) {
        return state.get(index);
    }
}
