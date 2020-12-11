package vincent.adventofcode.aoc2019.day22;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
class Motion {
    private final Type type;
    private final Integer number;

    static Motion forString(String s) {
        if (s.equals("deal into new stack")) {
            return new Motion(Type.DEAL_INTO_STACK, null);
        } else if (s.startsWith("cut")) {
            int n = Integer.parseInt(s.split(" ")[1]);
            return new Motion(Type.CUT, n);
        } else if (s.startsWith("deal with increment")) {
            int n = Integer.parseInt(s.split(" ")[3]);
            return new Motion(Type.INCREMENT_WITH, n);
        }
        throw new IllegalArgumentException("There is no motion for input: " + s);
    }

    enum Type {
        DEAL_INTO_STACK, CUT, INCREMENT_WITH
    }
}
