package vincent.adventofcode.aoc2018.day20;

import lombok.Data;

@Data
public class Door {
    private final Coordinate coordinate1;
    private final Coordinate coordinate2;

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Door)) {
            return false;
        }
        Door door = (Door) o;
        return (this.coordinate1.equals(door.coordinate1) && this.coordinate2.equals(door.coordinate2))
                || (this.coordinate2.equals(door.coordinate1) && this.coordinate1.equals(door.coordinate2));
    }
}
