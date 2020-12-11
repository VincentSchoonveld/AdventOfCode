package vincent.adventofcode.aoc2017.day21;

class ThreeTimesThreeGrid extends Grid {
    private static final int SIZE = 3;

    ThreeTimesThreeGrid(boolean[][] grid) {
        super(grid, SIZE);
    }

    @Override
    public Grid rotate() {
        boolean[][] newGrid = new boolean[SIZE][SIZE];
        newGrid[0][0] = getBooleanGrid()[2][0];
        newGrid[0][1] = getBooleanGrid()[1][0];
        newGrid[0][2] = getBooleanGrid()[0][0];
        newGrid[1][0] = getBooleanGrid()[2][1];
        newGrid[1][1] = getBooleanGrid()[1][1];
        newGrid[1][2] = getBooleanGrid()[0][1];
        newGrid[2][0] = getBooleanGrid()[2][2];
        newGrid[2][1] = getBooleanGrid()[1][2];
        newGrid[2][2] = getBooleanGrid()[0][2];
        return new ThreeTimesThreeGrid(newGrid);
    }
}
