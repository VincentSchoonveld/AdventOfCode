package vincent.adventofcode.aoc2017.day6;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
class Memory {
    @Getter
    private List<Integer> memory;
    private final Set<List<Integer>> states = new HashSet<>();

    int recurrence() {
        int i=0;
        while (states.add(memory)) {
            next();
            i++;
        }
        return i;
    }

    int firstSeen(List<Integer> memory) {
        int i = 0;
        while(!this.memory.equals(memory)) {
            next();
            i++;
        }
        return i;
    }

    private void next() {
        int index = nextIndex();
        int blocks = memory.get(index);
        memory.set(index, 0);
        while (blocks > 0) {
            index = (index+1)%16;
            memory.set(index, memory.get(index)+1);
            blocks--;
        }
    }

    private int nextIndex() {
        int index=-1;
        int blocks = -1;
        for(int i=0; i<16; i++) {
            if(blocks < memory.get(i)) {
                index = i;
                blocks = memory.get(i);
            }
        }
        return index;
    }
}
