package vincent.adventofcode.aoc2019.day7;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public class Amplifier implements Runnable {
    private final List<Integer> state;
    private int index = 0;
    private final BlockingQueue<Integer> inputs;
    private final BlockingQueue<Integer> outputs;

    @Override
    public void run() {
        try {
            while (state.get(index) != 99) {
                switch (OpCode.forRawCode(state.get(index))) {
                    case INPUT:
                        state.set(state.get(index + 1), inputs.poll(10L, TimeUnit.DAYS));
                        index += 2;
                        break;
                    case OUTPUT:
                        int outVal = state.get(state.get(index + 1));
                        outputs.put(outVal);
                        index += 2;
                        break;
                    case ADD:
                        state.set(state.get(index + 3), param1() + param2());
                        index += 4;
                        break;
                    case MULTIPLY:
                        state.set(state.get(index + 3), param1() * param2());
                        index += 4;
                        break;
                    case JUMP_IF_TRUE:
                        index = param1() > 0 ? param2() : index + 3;
                        break;
                    case JUMP_IF_FALSE:
                        index = param1() == 0 ? param2() : index + 3;
                        break;
                    case LESS_THAN:
                        int param1LessThanParam2 = param1() < param2() ? 1 : 0;
                        state.set(state.get(index + 3), param1LessThanParam2);
                        index += 4;
                        break;
                    case EQUALS:
                        int param1EqualsParam2 = param1() == param2() ? 1 : 0;
                        state.set(state.get(index + 3), param1EqualsParam2);
                        index += 4;
                        break;
                }
            }
        } catch (InterruptedException ie) {
            throw new RuntimeException(ie);
        }
    }

    private int param1() {
        String cmd = String.format("%05d", state.get(index));
        return cmd.charAt(2) == '0' ? state.get(state.get(index + 1)) : state.get(index + 1);
    }

    private int param2() {
        String cmd = String.format("%05d", state.get(index));
        return cmd.charAt(1) == '0' ? state.get(state.get(index + 2)) : state.get(index + 2);
    }
}
