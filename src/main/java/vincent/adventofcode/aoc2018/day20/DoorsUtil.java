package vincent.adventofcode.aoc2018.day20;

public class DoorsUtil {
    static Doors toDoors(String s) {
        Doors doors = new Doors();
        Coordinate coordinate1 = new Coordinate();
        Coordinate coordinate2 = new Coordinate();
        for(int i=0; i<s.length(); i++) {
            coordinate1 = coordinate2;
            coordinate2 = coordinate2.nextForChar(s.charAt(i));
            doors.add(new Door(coordinate1, coordinate2));
        }
        return doors;
    }

    static Doors add(Doors doors1, Doors doors2) {
        Doors doors = new Doors();
        doors.add(doors1);
        doors.add(doors2);
        return doors;
    }
}
