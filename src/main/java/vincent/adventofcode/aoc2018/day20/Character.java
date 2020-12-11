package vincent.adventofcode.aoc2018.day20;

import lombok.Getter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
class Character {
    private char c;
    private List<Character> nextCharacters;

    Character() {
        this(1);
    }

    private Character(int index) {
        c = Day20.INPUT_STRING.charAt(index);
        if(isDirection(c)) {
            nextCharacters = Collections.singletonList(new Character(index+1));
        } else if(c == '(') {
            BracketPattern bracketPattern = new BracketPattern(index);
            List<Integer> indices = bracketPattern.getIndices();
            nextCharacters = indices.stream().map(Character::new).collect(Collectors.toList());
        } else if(c != '$') {
            throw new IllegalStateException(String.format("The character %s is not expected at index %d.", c, index));
        }
    }

    private boolean isDirection(char c) {
        return c == 'S' || c == 'N' || c == 'W' || c == 'E';
    }
}
