package vincent.adventofcode.aoc2019.day12;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@EqualsAndHashCode
class Moons {
    private List<Moon> moonList;
    private int steps;

    Moons(List<Moon> moonList) {
        this.moonList = moonList;
        this.steps = 0;
    }

    void run(int numberOfSteps) {
        while(steps < numberOfSteps) {
            runOnce();
        }
    }

    void runOnce() {
        moonList.forEach(moon -> moonList.forEach(moon::adjustDeltaWithRespectTo));
        moonList.forEach(Moon::handle);
        steps++;
    }

    long totalEnergy() {
        return moonList.stream()
                .mapToLong(Moon::totalEnergy)
                .sum();
    }

}
