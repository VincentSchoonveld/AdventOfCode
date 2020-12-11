package vincent.adventofcode.aoc2018.day12;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@AllArgsConstructor
class State {
    private List<Integer> list;

    static State forInput(String input) {
        String[] s = input.split(" ");
        input = s[s.length-1];
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<input.length(); i++) {
            if(input.charAt(i) == '#') {
                list.add(i);
            }
        }
        return new State(list);
    }

    void next(Rules rules) {
        List<Integer> newList = new ArrayList<>();
        int minValue = list.stream().mapToInt(a -> a).min().getAsInt() - 5;
        int maxValue = list.stream().mapToInt(a -> a).max().getAsInt() + 5;
        for(int i = minValue; i<= maxValue; i++) {
            boolean bool = rules.next(isPlant(i-2), isPlant(i-1), isPlant(i), isPlant(i+1), isPlant(i+2));
            if(bool) {
                newList.add(i);
            }
        }
        this.list = newList;
    }

    int count() {
        return list.stream().mapToInt(a-> a).sum();
    }

    private boolean isPlant(int i) {
        return list.contains(i);
    }

    @Override
    public String toString() {
        return IntStream.range(list.stream().mapToInt(a->a).min().getAsInt(), list.stream().mapToInt(a->a).max().getAsInt()+1)
                .mapToObj(a->list.contains(a) ? '#' : '.')
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }
}
