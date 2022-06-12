import java.util.*;

public class Main6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] inputLine = scanner.nextLine().split(" ");
        int n = Integer.parseInt(inputLine[0]);
        int h = Integer.parseInt(inputLine[1]); // actually is not needed
        int m = Integer.parseInt(inputLine[2]);
        int k = Integer.parseInt(inputLine[3]);

        int[][] trains = new int[n][2];

        for (int i = 0; i < n; i++) {
            inputLine = scanner.nextLine().split(" ");
            trains[i][0] = Integer.parseInt(inputLine[0]);
            trains[i][1] = Integer.parseInt(inputLine[1]);
        }

        int t = -1;
        int minN = n;
        List<Integer> inconvenientTrains = new ArrayList<>();
        List<Integer> minInconvenientTrains = new ArrayList<>();

        for (int work1 = (m / 2) - 1; work1 >= 0; work1--) { //  for each t from (m/2)-1 to 0
            int countN = 0;
            int work2 = work1 + m / 2;
            int difference1;
            int difference2;

            inconvenientTrains.clear();

            for (int i = 0; i < trains.length; i++) { // for each train look if it is inconvenient with current t
                difference1 = work1 - trains[i][1] >= 0 ? work1 - trains[i][1] : work1 - trains[i][1] + m;
                difference2 = work2 - trains[i][1] >= 0 ? work2 - trains[i][1] : work2 - trains[i][1] + m;

                if ((difference1 < k && difference1 != 0) || (difference2 < k && difference2 != 0)) {
                    inconvenientTrains.add(i+1);
                    countN++;
                }
            }

            if (countN <= minN) {
                minN = countN;
                t = work1;
                minInconvenientTrains = List.copyOf(inconvenientTrains);
            }
        }

        System.out.println(minN + " " + t);
        for (Integer train : minInconvenientTrains) {
            System.out.print(train + " ");
        }
    }
}
