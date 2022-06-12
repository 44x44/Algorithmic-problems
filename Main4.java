import java.util.Scanner;

public class Main4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char[] alphabet = new char[26];
        int temp = 0;
        for (char i = 'a'; i <= 'z'; i++) {
            alphabet[temp] = i;
            temp++;
        }

        int n = scanner.nextInt();
        char[][] arr = new char[n][n];

        // top half of the table, including the middle line
        for (int i = 0; i < (n+1)/2; i++) {
            int k = 0;
            for (int j = i; j >= 0; j--) {
                arr[i][j] = alphabet[k % 26];
                k++;
            }

            k = 1;
            for (int j = i + 1; j < (n+1)/2; j++) {
                arr[i][j] = alphabet[k % 26];
                k++;
            }

            if (n % 2 == 1) {
                k--;
            }
            for (int j = (n+1)/2; j < n - i; j++) {
                k--;
                arr[i][j] = alphabet[k % 26];
            }

            k = 0;
            for (int j = n - i - 1; j < n; j++) {
                arr[i][j] = alphabet[k % 26];
                k++;
            }
        }

        // bottom half of the table
        for (int i = (n+1)/2; i < n; i++) {
            int k = 0;
            for (int j = n - i - 1; j >= 0; j--) {
                arr[i][j] = alphabet[k % 26];
                k++;
            }

            k = 1;
            for (int j = n - i; j < (n+1)/2; j++) {
                arr[i][j] = alphabet[k % 26];
                k++;
            }

            if (n % 2 == 1) {
                k--;
            }
            for (int j = (n+1)/2; j < i; j++) {
                k--;
                arr[i][j] = alphabet[k % 26];
            }

            k = 0;
            for (int j = i; j < n; j++) {
                arr[i][j] = alphabet[k % 26];
                k++;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.print("\n");
        }
    }
}
