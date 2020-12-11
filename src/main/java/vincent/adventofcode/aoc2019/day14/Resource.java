package vincent.adventofcode.aoc2019.day14;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode(of = "name")
class Resource implements Comparable<Resource> {
    private String name;
    private long amount;

    Resource(Resource resource) {
        this.name = resource.name;
        this.amount = resource.amount;
    }

    void setAmount(long amount) {
        this.amount = amount;
    }

    @Override
    public int compareTo(Resource o) {
        return name.compareTo(o.name);
    }
}
