package vincent.adventofcode.aoc2015.day21;

import java.util.Comparator;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Optional<Player> asInt = Item.allPermutations()
                .stream()
                .map(Player::forItems)
                .filter(i -> !i.winsAgainstBoss())
                .max(Comparator.comparing(Player::getCost));
//                Optional<List<Item>> asInt = Item.allPermutations()
//                .stream()
//                .filter(items -> !Player.forItems(items).winsAgainstBoss())
//                .max(Comparator.comparing(items -> Player.forItems(items).getCost()));

        System.out.println(asInt);
    }
}
