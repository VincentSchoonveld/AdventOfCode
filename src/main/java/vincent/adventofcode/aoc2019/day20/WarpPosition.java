package vincent.adventofcode.aoc2019.day20;

enum WarpPosition {
    INSIDE, OUTSIDE, NONE;

    static WarpPosition forBoolean(boolean isInside) {
        if(isInside) {
            return INSIDE;
        } else {
            return OUTSIDE;
        }
    }

    public WarpPosition opposite() {
        if(this == INSIDE) {
            return OUTSIDE;
        } else if(this == OUTSIDE) {
            return INSIDE;
        }
        return NONE;
    }
}
