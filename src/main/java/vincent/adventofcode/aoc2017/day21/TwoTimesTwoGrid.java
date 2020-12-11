package vincent.adventofcode.aoc2017.day21;

class TwoTimesTwoGrid extends Grid {
    private static final int SIZE = 2;

    TwoTimesTwoGrid(boolean[][] grid) {
        super(grid, SIZE);
    }

    @Override
    public Grid rotate() {
        boolean[][] newGrid = new boolean[SIZE][SIZE];
        newGrid[0][0] = getBooleanGrid()[1][0];
        newGrid[0][1] = getBooleanGrid()[0][0];
        newGrid[1][0] = getBooleanGrid()[1][1];
        newGrid[1][1] = getBooleanGrid()[0][1];
        return new TwoTimesTwoGrid(newGrid);
    }
}
