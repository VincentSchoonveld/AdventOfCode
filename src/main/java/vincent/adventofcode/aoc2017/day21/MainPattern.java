package vincent.adventofcode.aoc2017.day21;

import java.util.Arrays;
import java.util.stream.IntStream;

class MainPattern {
    boolean[][] pattern = {{ false, true, false}, {false, false, true}, {true, true, true}};//initial pattern

    int amountOfPixelsOn() {
        return Arrays.stream(pattern)
                .mapToInt(arr -> IntStream.range(0, arr.length).map(i -> arr[i] ? 1 : 0).sum())
                .sum();
    }

    void next(Rules rules) {
        if(pattern.length%2 == 0) {
            boolean[][] nextPattern = new boolean[(pattern.length/2) *3][(pattern.length/2) *3];
            for(int i=0; i<pattern.length/2; i++) {
                for(int j=0; j<pattern.length/2; j++) {
                    boolean[][] booleans = {
                            {pattern[2 * i][2*j], pattern[2 * i][2*j + 1]},
                            {pattern[2 * i + 1][2*j], pattern[2 * i + 1][2*j + 1]}
                    };
                    TwoTimesTwoGrid twoTimesTwoGrid = new TwoTimesTwoGrid(booleans);
                    Grid grid = rules.get(twoTimesTwoGrid);
                    boolean[][] booleanGrid = grid.getBooleanGrid();
                    for(int n=0; n<3; n++) {
                        for(int m=0; m<3; m++) {
                            nextPattern[i*3+n][j*3+m] = booleanGrid[n][m];
                        }
                    }
                }
            }
            pattern = nextPattern;
        } else if(pattern.length%3 == 0) {
            boolean[][] nextPattern = new boolean[(pattern.length/3) *4][(pattern.length/3) *4];
            for(int i=0; i<pattern.length/3; i++) {
                for(int j=0; j<pattern.length/3; j++) {
                    boolean[][] booleans = {
                            {pattern[3 * i][3*j], pattern[3 * i][3*j + 1], pattern[3 * i][3*j + 2]},
                            {pattern[3 * i + 1][3*j], pattern[3 * i + 1][3*j + 1], pattern[3 * i + 1][3*j + 2]},
                            {pattern[3 * i + 2][3*j], pattern[3 * i + 2][3*j + 1], pattern[3 * i + 2][3*j + 2]}
                    };
                    ThreeTimesThreeGrid threeTimesThreeGrid = new ThreeTimesThreeGrid(booleans);
                    Grid grid = rules.get(threeTimesThreeGrid);
                    boolean[][] booleanGrid = grid.getBooleanGrid();
                    for(int n=0; n<4; n++) {
                        for(int m=0; m<4; m++) {
                            nextPattern[i*4+n][j*4+m] = booleanGrid[n][m];
                        }
                    }
                }
            }
            pattern = nextPattern;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (boolean[] booleans : pattern) {
            for (boolean aBoolean : booleans) {
                stringBuilder.append(aBoolean ? "#" : ".");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
