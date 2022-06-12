import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] params = scanner.nextLine().split(" ");
        int n = Integer.parseInt(params[0]);
        int k = Integer.parseInt(params[1]);

        String[] permutationString = scanner.nextLine().split(" ");
        List<Integer> permutation = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            permutation.add(Integer.parseInt(permutationString[i]));
        }

        Tree tree = new Tree();

        // uncomment to see the tree constructed: (also uncomment the block of code below)
        /*tree.getSmaller(tree.root, permutation);
        System.out.println(tree);*/

        tree.getNumberOfGroups(permutation, k);
    }
}

class Tree {
    public final Node root = new Node(100_001); // this element is needed to combine subtrees into one tree
                                                     // and is not taken into account when counting
    private int numberOfGroups = 0;
    private int prevChild = 0;
    private int groupLength;
    private int k;
    private boolean continueBranch = true;

    // construct the tree
    public void getSmaller(Node parent, List<Integer> children) {
        for (Integer child: children) {
            if (child < parent.data) {
                Node childNode = new Node(child);

                if (prevChild == 0 || !childNode.repeats || child > prevChild) {
                    continueBranch = true;
                }

                if (continueBranch) {
                    parent.addChild(childNode);

                    List<Integer> grandChildren = new ArrayList<>(children);

                    while (childNode.data != grandChildren.get(0)) {
                        grandChildren.remove(0);
                    }
                    grandChildren.remove(0);

                    getSmaller(childNode, grandChildren);
                }
            }

            if (children.indexOf(child) == children.size()-1) {
                continueBranch = false;
                prevChild = parent.data;
            }
        }
    }

    // count all required groups
    public void getNumberOfGroups(List<Integer> children, int k) {
        getSmaller(root, children);
        this.k = k;

        for (Node child: root.children) {
            countGroups(child);
        }

        System.out.println(numberOfGroups);
    }

    // count all required groups in the tree, the root of which is the incoming node
    private void countGroups(Node node) {
        if (node.parent == root) {
            groupLength = 0;
        }

        groupLength++;

        if (node != node.parent.children.get(0)) {
            k--;
            groupLength--;
        }

        if (!node.children.isEmpty()) {
            for (Node child : node.children) {
                countGroups(child);
            }
        }
        else {
            numberOfGroups += combinations(groupLength, k);
        }

        groupLength--;

        if (node != node.parent.children.get(0)) {
            k++;
            groupLength++;
        }
    }

    // count the number of combinations of tree branch elements
    private int combinations(int groupLength, int k) {
        if (groupLength == 0) {
            return 0;
        }

        return (int) Math.floor( getFactorial(groupLength) / ( getFactorial(k) * getFactorial(groupLength - k) ) );
    }

    // count the factorial of int n
    private double getFactorial(int n) {
        int res = 1;

        for (int i = 1; i <= n; i++) {
            res *= i;
        }

        return res;
    }

    // uncomment to see the tree constructed:
    /*public String toString() {
        StringBuilder builder = new StringBuilder();
        dumpNodeStructure(builder, root, "- ");
        return builder.toString();
    }

    private void dumpNodeStructure(StringBuilder builder, Node node, String prefix) {
        if (node == root){
            builder.append(prefix);
            builder.append("root\n");
            prefix = "  " + prefix;
        }
        else {
            builder.append(prefix);
            builder.append(node.data);
            builder.append('\n');
            prefix = "  " + prefix;
        }

        for (Node child : node.children) {
            dumpNodeStructure(builder, child, prefix);
        }
    }*/

    class Node {
        private Node parent;
        private List<Node> children = new ArrayList<>();
        private int data;
        public final boolean repeats;

        private static List<Integer> usedNumbers = new ArrayList<>();

        public Node(int data) {
            this.data = data;

            repeats = usedNumbers.contains(data);

            if (!this.repeats) {
                usedNumbers.add(data);
            }
        }

        public void addChild(Node child) {
            this.children.add(child);
            child.parent = this;
        }
    }
}
