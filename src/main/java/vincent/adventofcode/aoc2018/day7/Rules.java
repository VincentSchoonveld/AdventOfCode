package vincent.adventofcode.aoc2018.day7;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Rules {

    private List<Rule> ruleList;

    static Rules forInput(List<String> input) {
        return new Rules(
                input.stream().map(Rule::forInput).collect(Collectors.toList())
        );
    }

    Set<Character> getAllParents(char c) {
        Set<Character> parents = getParents(c);
        Set<Character> nextParents = parents.stream().flatMap(a -> getParents(a).stream()).collect(Collectors.toSet());
        while(!parents.containsAll(nextParents)) {
            parents.addAll(nextParents);
            nextParents = parents.stream().flatMap(a -> getParents(a).stream()).collect(Collectors.toSet());
        }
        return parents;
    }

    private Set<Character> getParents(char c) {
        return ruleList.stream()
                .filter(a -> a.child == c)
                .map(a -> a.parent)
                .collect(Collectors.toSet());
    }

    @AllArgsConstructor
    private static class Rule {
        static Rule forInput(String string) {
            char child = string.charAt(36);
            char parent = string.charAt(5);
            return new Rule(child, parent);
        }

        private char child;
        private char parent;
    }
}
