package vincent.adventofcode.aoc2018.day14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Recipes {
    private List<Integer> recipes;
    private int pos1;
    private int pos2;

    Recipes() {
        this.recipes = new ArrayList<>();
        recipes.add(3);
        recipes.add(7);
        pos1 = 0;
        pos2 = 1;
    }

    int indexOf(int number) {
        List<Integer> list = Arrays.stream(String.valueOf(number).split("")).map(Integer::valueOf).collect(Collectors.toList());
        while(list.size() > recipes.size()) {
            next();
        }
        wile:
        while(true) {
            next();
            for(int i=0; i<list.size(); i++) {
                if(list.get(i) != recipes.get(recipes.size()-list.size()-1+i)) {
                    continue wile;
                }
            }
            break;
        }
        return recipes.size()-list.size()-1;
    }

    void next() {
        int newInt = recipes.get(pos1) + recipes.get(pos2);
        if(newInt >= 10) {
            recipes.add(newInt/10);
            recipes.add(newInt%10);
        } else {
            recipes.add(newInt);
        }
        pos1 += (recipes.get(pos1)+1);
        pos1 %= recipes.size();
        pos2 += (recipes.get(pos2)+1);
        pos2 %= recipes.size();
    }

    int size() {
        return recipes.size();
    }

    String nextTen(int after) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=after; i<after+10; i++) {
            stringBuilder.append(recipes.get(i));
        }
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return recipes.stream().collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
    }
}
