package vincent.adventofcode.aoc2019.day14;

import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;

@AllArgsConstructor
class Reactions {
    private Map<String, Set<Resource>> map;
    private Map<Resource, Long> productionMultiplicityMap = new HashMap<>();

    static Reactions forInput(List<String> ls) {
        Map<Resource, Set<Resource>> collect = ls.stream()
                .collect(Collectors.toMap(
                        a -> {
                            String[] s = a.split(" ");
                            return new Resource(s[s.length - 1], Long.parseLong(s[s.length - 2]));
                        },
                        Reactions::forInput
                ));
        Map<Resource, Long> productionMultiplicity = collect.keySet().stream()
                .collect(Collectors.toMap(a -> a, Resource::getAmount));

        Map<String, Set<Resource>> map2 = collect.entrySet().stream()
                .collect(Collectors.toMap(a -> a.getKey().getName(), Map.Entry::getValue));

        return new Reactions(map2, productionMultiplicity);
    }

    private static Set<Resource> forInput(String string) {
        String[] s = string.split(" ");
        Set<Resource> resourceList = new HashSet<>();
        int i=0;
        while(!s[i].equals("=>")) {
            String s1 = s[i + 1];
            String s2 = s1.replaceAll(",", "");
            Resource resource = new Resource(s2, Integer.parseInt(s[i]));
            resourceList.add(resource);
            i=i+2;
        }
        return resourceList;
    }

    Long getAmount(String name, int amount) {
        return map.get(name).stream()
                .mapToLong(getORENeed(map, new HashMap<>(), amount))
                .sum();
    }

    int getAmountForAmountOfOre(String name, long amountOfOre) {
        int low = 0;
        int high = Integer.MAX_VALUE;
        while(low < high) {
            int amountOfFuel = (high + low)/2;
            long amountOfOreTemp = getAmount(name, amountOfFuel);
            if(amountOfOreTemp < amountOfOre) {
                low = amountOfFuel+1;
            } else {
                high = amountOfFuel-1;
            }
        }
        return high;
    }

    private ToLongFunction<Resource> getORENeed(Map<String, Set<Resource>> map, Map<String, Long> leftOvers, int produceCount) {
        return resource -> {
            if("ORE".equals(resource.getName())) {
                return produceCount * resource.getAmount();
            } else {
                long quantityNeed = produceCount * resource.getAmount() - leftOvers.getOrDefault(resource.getName(), 0L);
                long productionMultiplicity = productionMultiplicityMap.get(resource);
                int create = (int) Math.ceil(quantityNeed / (double) productionMultiplicity);
                long leftOver = productionMultiplicity * create - quantityNeed;
                leftOvers.compute(resource.getName(), (key, value) -> leftOver == 0 ? null : leftOver);
                return map.get(resource.getName()).stream().mapToLong(getORENeed(map, leftOvers, create)).sum();
            }
        };
    }
}
