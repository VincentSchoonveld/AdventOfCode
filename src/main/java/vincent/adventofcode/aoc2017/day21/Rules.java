package vincent.adventofcode.aoc2017.day21;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
class Rules {
    private Map<Grid, Grid> map;

    static Rules forInput(List<String> input) {
        Map<Grid, Grid> map = new HashMap<>();
        for(String string:input) {
            Set<Grid> gridSet = Grid.domainForInput(string);
            Grid result = Grid.resultForInput(string);
            gridSet.forEach(grid -> map.put(grid, result));
        }
        return new Rules(map);
    }

    Grid get(Grid grid) {
        return map.get(grid);
    }
}
