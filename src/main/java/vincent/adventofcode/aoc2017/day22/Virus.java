package vincent.adventofcode.aoc2017.day22;

import lombok.Getter;

class Virus {
    private Direction direction = Direction.NORTH;
    private Index index;
    @Getter
    private int countInfections = 0;

    Virus(Index index) {
        this.index = index;
    }

    void next(Nodes nodes) {
        turn(nodes);
        infectNode(nodes);
        moveForward();
    }

    private void turn(Nodes nodes) {
        InfectionState infectionState = nodes.getInfectionState(index);
        direction = direction.next(infectionState);
    }

    private void infectNode(Nodes nodes) {
        boolean infected = nodes.changeInfection(index);
        if(infected) {
            countInfections++;
        }
    }

    private void moveForward() {
        index = index.next(direction);
    }
}
