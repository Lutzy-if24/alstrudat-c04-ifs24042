package del.alstrudat;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Baris 1: N (persimpangan) dan M (jalan)
        int N = Integer.parseInt(scanner.nextLine().trim());
        int M = Integer.parseInt(scanner.nextLine().trim());
        // Baris 2: titik awal dan tujuan
        int start = Integer.parseInt(scanner.nextLine().trim());
        int end = Integer.parseInt(scanner.nextLine().trim());

        // Baris 3: jumlah blokir dan daftar persimpangan diblokir
        int B = Integer.parseInt(scanner.nextLine().trim());
        int[] blocked = new int[B];
        for (int i = 0; i < B; i++) {
            blocked[i] = Integer.parseInt(scanner.nextLine().trim());
        }

        // Baris berikutnya: M jalan (dari, ke, bobot)
        int[][] edges = new int[M][3];
        for (int i = 0; i < M; i++) {
            String[] parts = scanner.nextLine().trim().split(" ");
            edges[i][0] = Integer.parseInt(parts[0]);
            edges[i][1] = Integer.parseInt(parts[1]);
            edges[i][2] = Integer.parseInt(parts[2]);
        }
        scanner.close();

        Program.solve(N, M, start, end, blocked, edges);
    }
}