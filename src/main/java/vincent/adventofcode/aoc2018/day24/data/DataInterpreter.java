package vincent.adventofcode.aoc2018.day24.data;

import lombok.AllArgsConstructor;
import vincent.adventofcode.aoc2018.day24.groups.Group;
import vincent.adventofcode.aoc2018.day24.groups.Groups;
import vincent.adventofcode.util.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
class DataInterpreter {
    private final Words words;
    private final List<Group> immunes = new ArrayList<>();
    private final List<Group> infections = new ArrayList<>();

    DataInterpreter(List<String> input) {
        this.words = new Words(input);
    }

    Groups getImmunes() {
        initiate();
        throw new NotImplementedException();
    }

    Groups getInfections() {
        initiate();
        throw new NotImplementedException();
    }

    private void initiate() {
        throw new NotImplementedException();
    }

}
