package vincent.adventofcode.aoc2019.day17;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

class IntcodeProgram {

    private Map<Long, Long> state;
    private long index;
    private long relativeBase;

    private static final int EXIT_VALUE = 99;

    IntcodeProgram(List<Long> state) {
        this.state = IntStream.range(0, state.size())
                .boxed()
                .collect(Collectors.toMap(Long::valueOf, state::get));
        this.index = 0;
        this.relativeBase = 0;
    }

    long execute(List<Long> input) {
        while (state.get(index) != EXIT_VALUE) {
            switch (OpCode.forRawCode(state.get(index))) {
                case INPUT:
                    state.put(referenceToValue(1), input.remove(0));
                    index += 2;
                    break;
                case OUTPUT:
                    long output = param(1);
                    index += 2;
                    return output;
                case ADJUST_RELATIVE_BASE:
                    relativeBase = relativeBase + param(1);
                    index += 2;
                    break;
                case ADD:
                    state.put(referenceToValue(3), param(1) + param(2));
                    index += 4;
                    break;
                case MULTIPLY:
                    state.put(referenceToValue(3), param(1) * param(2));
                    index += 4;
                    break;
                case JUMP_IF_TRUE:
                    index = param(1) > 0 ? param(2) : index + 3;
                    break;
                case JUMP_IF_FALSE:
                    index = param(1) == 0 ? param(2) : index + 3;
                    break;
                case LESS_THAN:
                    long param1LessThanParam2 = param(1) < param(2) ? 1 : 0;
                    state.put(referenceToValue(3), param1LessThanParam2);
                    index += 4;
                    break;
                case EQUALS:
                    long param1EqualsParam2 = param(1) == param(2) ? 1 : 0;
                    state.put(referenceToValue(3), param1EqualsParam2);
                    index += 4;
                    break;
            }
        }
        return -1;
    }

    private long param(int number) {
        return state.getOrDefault(referenceToValue(number), 0L);
    }

    private long referenceToValue(int number) {
        String cmd = String.format("%05d", state.get(index));
        char referenceChar = cmd.charAt(3 - number);
        switch (referenceChar) {
            case '0': return state.getOrDefault(index + number, 0L);
            case '1': return index + number;
            case '2': return state.getOrDefault(index + number, 0L)+relativeBase;
            default: throw new IllegalStateException("The character is not known: " + referenceChar);
        }
    }

    @Override
    public String toString() {
        Long integer = state.keySet().stream().max(Long::compareTo).orElse(-1L);
        return LongStream.rangeClosed(0, integer)
                .map(a -> state.getOrDefault(a, 0L))
                .mapToObj(a -> new StringBuilder().append(a).append(", "))
                .reduce(new StringBuilder(), StringBuilder::append)
                .toString();
    }
}
