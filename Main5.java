import java.util.*;

public class Main5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] params = scanner.nextLine().split(" ");
        int size = Integer.parseInt(params[0]);
        int k = Integer.parseInt(params[1]);

        int res = 0;

        for (List<Integer> innerList: permutations(size)) {
            if (getBeauty(innerList) % k == 0) {
                res++;
            }
        }

        System.out.println(res);
    }

    private static int getBeauty(List<Integer> permutation) {
        int res = 0;

        for (int i = 0; i < permutation.size(); i++) {
            res += permutation.get(i) * (i+1);
        }

        return res;
    }
    
    private static List<List<Integer>> permutations(int size) {
        List<List<Integer>> permutationsList = new ArrayList<>();
        List<Integer> permutation = new ArrayList<>();

        if (size == 1) {
            permutation.add(1);
            permutationsList.add(permutation);

            return permutationsList;
        }

        for (List<Integer> innerList: permutations(size-1)) {
            for (int i = 0; i < size; i++) {
                permutation = new ArrayList<>(innerList);
                permutation.add(i, size);
                permutationsList.add(permutation);
            }
        }
        return permutationsList;
    }
}
