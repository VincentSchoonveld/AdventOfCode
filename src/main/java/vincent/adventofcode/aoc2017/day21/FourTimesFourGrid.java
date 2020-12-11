package vincent.adventofcode.aoc2017.day21;

import vincent.adventofcode.util.NotImplementedException;

class FourTimesFourGrid extends Grid {
    private static final int SIZE = 4;

    FourTimesFourGrid(boolean[][] grid) {
        super(grid, SIZE);
    }


    @Override
    public Grid rotate() {
        throw new NotImplementedException();
    }
}
