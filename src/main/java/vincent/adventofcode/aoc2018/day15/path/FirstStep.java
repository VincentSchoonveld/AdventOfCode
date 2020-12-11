package vincent.adventofcode.aoc2018.day15.path;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import vincent.adventofcode.aoc2018.day15.Index;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class FirstStep {
    static Optional<Index> first(List<Index> openSpaces,
                                 Index start,
                                 Index finish) {
        Nodes nodes = new Nodes();
        nodes.add(new Node(start, null, 0));
        int steps = 0;
        while(!nodes.contains(finish)) {
            steps++;
            final int stepsFinal = steps;

            List<Node> nodeList = nodes.nodeList;

            List<Node> collect = nodeList.stream()
                    .flatMap(a -> a.index.neighbors().filter(openSpaces::contains).map(b -> new Node(b, a, stepsFinal)))
                    .collect(Collectors.toList());

            collect.forEach(nodes::add);
        }
        return nodes.getFirstStep(finish);
    }

    static class Nodes {
        private List<Node> nodeList = new ArrayList<>();

        Optional<Index> getFirstStep(Index index) {
            Index last = null;
            Node node = getNode(index);

            while(node.parent != null) {
                last = node.index;
                node = node.parent;
            }
            return Optional.ofNullable(last);
        }

        void add(Node node) {
            int index = nodeList.indexOf(node);
            if(index != -1) {
                Node node1 = nodeList.get(index);
                node1.setParentIfLess(node.parent);
            } else {
                nodeList.add(node);
            }
        }

        private Node getNode(Index index) {
            return nodeList.stream()
                    .filter(a -> a.index.equals(index))
                    .findAny()
                    .orElse(null);
        }

        public boolean contains(Index index) {
            return nodeList.stream().anyMatch(a -> a.index.equals(index));
        }
    }

    @EqualsAndHashCode
    @AllArgsConstructor
    static class Node {
        Index index;
        @EqualsAndHashCode.Exclude
        Node parent;
        @EqualsAndHashCode.Exclude
        int steps;

        void setParentIfLess(Node possibleParent) {
            if(parent != null && parent.parent != null && possibleParent.steps == parent.steps) {
                Node thisParent = parent;
                Node otherParent = possibleParent;
                while(thisParent.parent.parent != null) {
                    thisParent = thisParent.parent;
                    otherParent = otherParent.parent;
                }
                if(otherParent.index.compareTo(thisParent.index) < 0) {
                    parent = possibleParent;
                }
            }
        }
    }
}
