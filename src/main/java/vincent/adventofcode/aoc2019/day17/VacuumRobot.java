package vincent.adventofcode.aoc2019.day17;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
class VacuumRobot {

    private Index index;
    private Direction direction;
    private String commands;

    VacuumRobot(Index index, Direction direction) {
        this.index = index;
        this.direction = direction;
        this.commands = "";
    }

    String getCommands() {
        return commands.substring(1);
    }

    void turnLeft() {
        direction = direction.left();
        commands = commands + ",L";
    }

    void turnRight() {
        direction = direction.right();
        commands = commands + ",R";
    }

    void turnForward(int amount) {
        for(int i=0; i<amount; i++) {
            index = index.next(direction);
        }
        commands = commands + "," + amount;
    }
}
