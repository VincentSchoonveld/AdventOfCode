package vincent.adventofcode.aoc2015.day21;

import lombok.Value;

import java.util.List;

@Value
public class Player {

    int hitPoints;
    int damage;
    int armor;
    int cost;

    static final Player BOSS = new Player(109, 8, 2, -1);

    static Player forItems(List<Item> items) {
        int hitPoints = items.stream().mapToInt(Item::getArmor).sum()+100;
        int damage = items.stream().mapToInt(Item::getDamage).sum();
        int armor = items.stream().mapToInt(Item::getArmor).sum();
        int costs = items.stream().mapToInt(Item::getCost).sum();
        return new Player(hitPoints, damage, armor, costs);
    }

    boolean winsAgainstBoss() {
        boolean playerTurn = true;
        int hitPointsPlayer = this.hitPoints;
        int hitPointsBoss = BOSS.hitPoints;
        while(hitPointsPlayer > 0 && hitPointsBoss > 0) {
            if(playerTurn) {
                hitPointsBoss = hitPointsBoss - Math.max(1, damage-BOSS.armor);
            } else {
                hitPointsPlayer = hitPointsPlayer - Math.max(1, BOSS.damage-armor);
            }
            playerTurn = !playerTurn;
        }
        return hitPointsPlayer > 0;
    }
}
