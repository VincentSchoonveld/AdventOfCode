package vincent.adventofcode.aoc2015.day21;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static vincent.adventofcode.aoc2015.day21.Item.Type.*;

@Value
public class Item {
    String name;
    int cost;
    int damage;
    int armor;
    Type type;

    static List<List<Item>> allPermutations() {
        List<Item> weapons = allWeapons();
        List<Item> armors = allArmor();
        List<Item> rings = allRings();
        List<List<Item>> permutations = new ArrayList<>();
        List<List<Item>> lists = CombinationUtil.subsetSets(weapons, 1, 1);
        List<List<Item>> lists1 = CombinationUtil.subsetSets(armors, 0, 1);
        List<List<Item>> lists2 = CombinationUtil.subsetSets(rings, 0, 2);
        for(List<Item> weapon : lists) {
            for(List<Item> armor : lists1) {
                for(List<Item> ring : lists2) {
                    List<Item> c = new ArrayList<>();
                    c.addAll(weapon);
                    c.addAll(armor);
                    c.addAll(ring);
                    permutations.add(c);
                }
            }
        }
        return permutations;
    }

    private static List<Item> allWeapons() {
        return Arrays.asList(
                new Item("Dagger", 8, 4, 0, WEAPON),
                new Item("Shortsword", 10, 5, 0, WEAPON),
                new Item("Warhammer", 25, 6, 0, WEAPON),
                new Item("Longsword", 40, 7, 0, WEAPON),
                new Item("Greataxe", 74, 8, 0, WEAPON)
        );
    }

    private static List<Item> allArmor() {
        return Arrays.asList(
                new Item("Leather", 13, 0, 1, ARMOR),
                new Item("Chainmail", 31, 0, 2, ARMOR),
                new Item("Splintmail", 53, 0, 3, ARMOR),
                new Item("Bandedmail", 75, 0, 4, ARMOR),
                new Item("Platemail", 102, 0, 5, ARMOR)
        );
    }

    private static List<Item> allRings() {
        return Arrays.asList(
                new Item("Damage +1", 25, 1, 0, RING),
                new Item("Damage +2", 50, 2, 0, RING),
                new Item("Damage +3", 100, 3, 0, RING),
                new Item("Defense +1", 20, 0, 1, RING),
                new Item("Defense +2", 40, 0, 2, RING),
                new Item("Defense +3", 80, 0, 3, RING)
        );
    }

    @AllArgsConstructor
    enum Type {
        WEAPON, ARMOR, RING;

    }
}
