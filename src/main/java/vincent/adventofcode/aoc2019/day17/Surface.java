package vincent.adventofcode.aoc2019.day17;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
class Surface {
    private PathMap pathMap;
    private VacuumRobot vacuumRobot;

    static Surface forIntcodeprogram(IntcodeProgram intcodeProgram) {
        Map<Index, Type> map = new HashMap<>();
        VacuumRobot vacuumRobot = null;

        long output = intcodeProgram.execute(null);
        int x = 0;
        int y = 0;
        while(output != -1L) {
            switch ((char) output) {
                case '.':
                    map.put(new Index(x,y), Type.OPEN_SPACE);
                    x++;
                    break;
                case 'v':
                case '^':
                case '<':
                case '>':
                    vacuumRobot = new VacuumRobot(new Index(x,y), Direction.forChar((char) output));
                case '#':
                    map.put(new Index(x,y), Type.SCAFFELD);
                    x++;
                    break;
                case '\n':
                    y++;
                    x = 0;
                    break;
                default: throw  new IllegalArgumentException("The output " + output + " is not known");
            }
            output = intcodeProgram.execute(null);
        }
        PathMap pathMap = new PathMap(map);
        return new Surface(pathMap, vacuumRobot);
    }

    long sumAlignmentParameters() {
        return pathMap.sumAlignmentParameters();
    }

    String naive() {
        vacuumRobot.turnLeft();
        while(vacuumRobotHasNext()) {
            int before = 0;
            Index index = vacuumRobot.getIndex().next(vacuumRobot.getDirection());
            while (pathMap.get(index) == Type.SCAFFELD) {
                before++;
                index = index.next(vacuumRobot.getDirection());
            }
            vacuumRobot.turnForward(before);
            if(isLeftScaffeld()) {
                vacuumRobot.turnLeft();
            } else if(isRightScaffeld()) {
                vacuumRobot.turnRight();
            }
        }
        return vacuumRobot.getCommands();
    }

    private boolean vacuumRobotHasNext() {
        boolean beforeScaffeld = isBeforeScaffeld();
        boolean leftScaffeld = isLeftScaffeld();
        boolean rightScaffeld = isRightScaffeld();
        return beforeScaffeld || leftScaffeld || rightScaffeld;
    }

    private boolean isRightScaffeld() {
        return pathMap.get(vacuumRobot.getIndex().next(vacuumRobot.getDirection().right())) == Type.SCAFFELD;
    }

    private boolean isLeftScaffeld() {
        return pathMap.get(vacuumRobot.getIndex().next(vacuumRobot.getDirection().left())) == Type.SCAFFELD;
    }

    private boolean isBeforeScaffeld() {
        return pathMap.get(vacuumRobot.getIndex().next(vacuumRobot.getDirection())) == Type.SCAFFELD;
    }

    @Override
    public String toString() {
        String s = pathMap.toString();
        int lineWidth = s.indexOf('\n')+1;
        Index vacuumRobotIndex = vacuumRobot.getIndex();
        int positionVacuumRobot = vacuumRobotIndex.getX() + lineWidth*vacuumRobotIndex.getY();
        return s.substring(0, positionVacuumRobot) + vacuumRobot.getDirection().asChar() + s.substring(positionVacuumRobot+1);
    }
}
