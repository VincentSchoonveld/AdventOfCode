package vincent.adventofcode.aoc2017.day21;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@EqualsAndHashCode
abstract class Grid {

    @Getter
    private boolean[][] booleanGrid;
    private int size;

    static Set<Grid> domainForInput(String input) {
        Set<Grid> gridSet = new HashSet<>();
        Grid grid = forInput(input.split(" ")[0]);
        for(int i=0; i<2; i++) {
            for(int j=0; j<4; j++) {
                gridSet.add(grid);
                grid = grid.rotate();
            }
            grid =grid.flip();
        }
        return gridSet;
    }

    static Grid resultForInput(String input) {
        return forInput(input.split(" ")[2]);
    }

    private static Grid forInput(String input) {
        String[] split = input.split("/");
        int size = split[0].length();
        boolean[][] grid = new boolean[size][size];
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                grid[i][j] = split[i].charAt(j) == '#';
            }
        }
        if(size == 2) {
            return new TwoTimesTwoGrid(grid);
        } else if(size == 3) {
            return new ThreeTimesThreeGrid(grid);
        } else if(size == 4) {
            return new FourTimesFourGrid(grid);
        } else {
            throw new IllegalArgumentException("There is no grid of size: " + size);
        }
    }

    private Grid flip() {
        boolean[][] newGrid = new boolean[size][size];
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                newGrid[i][j] = booleanGrid[i][size-j-1];
            }
        }
        if(size == 2) {
            return new TwoTimesTwoGrid(newGrid);
        } else if(size == 3) {
            return new ThreeTimesThreeGrid(newGrid);
        } else {
            throw new IllegalArgumentException("The following size cannot be flipped: " + size);
        }
    }

    abstract Grid rotate();
}
