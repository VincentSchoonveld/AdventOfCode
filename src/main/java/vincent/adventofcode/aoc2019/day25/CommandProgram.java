package vincent.adventofcode.aoc2019.day25;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
class CommandProgram {
    private final AsciiProgram asciiProgram;

    private static final String INITIAL_INPUT = "north\n";

    private static final List<String> list = Arrays.asList("asterisk", "pointer", "mutex", "space law space brochure", "monolith", "mouse", "food ration");
    private int numberDropped = 0;
    private List<List<Integer>> combinations = new ArrayList<>();

    void run() {
        String input = INITIAL_INPUT;
        List<String> commands = retrieveAllNecessaryItems();
        while(!runUntilPassword(input)) {
            if(commands.isEmpty()) {
                commands = commands();
            }
            input = commands.remove(0);
        }
    }

    private List<String> commands() {
        if(combinations.isEmpty()) {
            combinations = Combination.getCombinations(list.size(), ++numberDropped);
        }
        List<String> strings = new ArrayList<>();
        List<Integer> integers = combinations.remove(0);
        for (Integer integer : integers) {
            strings.add("drop " + list.get(integer) + "\n");
        }
        strings.add("east\n");
        for (Integer integer : integers) {
            strings.add("take " + list.get(integer) + "\n");
        }
        return strings;
    }

    private boolean runUntilPassword(String input) {
        String execute = asciiProgram.execute(input);
        while(!execute.contains("Command?")) {
            execute = asciiProgram.execute(input);
            if(!execute.matches("^[\n]*")) {
                System.out.println(execute);
                if(execute.contains("\"Oh, hello! You should be able to get in by typing 536904736 on the keypad at the main airlock.\"")) {
                    return true;
                }
            }
        }
        return false;
    }

    private static List<String> retrieveAllNecessaryItems() {
        return new ArrayList<>(Arrays.asList(
                "west\n",
                "take monolith\n",
                "north\n",
                "west\n",
                "take food ration\n",
                "south\n",
                "take space law space brochure\n",
                "north\n",
                "east\n",
                "south\n",
                "south\n",
                "south\n",
                "west\n",
                "take asterisk\n",
                "south\n",
                "take mutex\n",
                "north\n",
                "east\n",
                "north\n",
                "north\n",
                "east\n",
                "north\n",
                "take mouse\n",
                "north\n",
                "take pointer\n",
                "south\n",
                "south\n",
                "south\n",
                "south\n",
                "west\n",
                "south\n"));
    }
}
