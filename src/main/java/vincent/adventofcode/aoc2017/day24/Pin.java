package vincent.adventofcode.aoc2017.day24;

import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
class Pin {
    private int first;
    private int second;

    static Pin forInput(String input) {
        String[] split = input.split("/");
        return new Pin(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Pin)) {
            return false;
        }
        Pin pin = (Pin) o;
        return (pin.first == first && pin.second == second)
                || (pin.first == second && pin.second == first);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second) + Objects.hash(second, first);
    }

    int strength() {
        return first+second;
    }

    int getLast(Pin before) {
        return before.contains(first) ? second : first;
    }

    int getLastIfFirst() {
        return first == 0 ? second : first;
    }

    boolean contains(int number) {
        return first == number || second == number;
    }
}
