package del.alstrudat;

import java.util.*;

public class Program {
    // Inner class untuk merepresentasikan edge dalam graf
    static class Edge {
        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    // Inner class untuk PriorityQueue Dijkstra
    static class Node implements Comparable<Node> {
        int id;
        int distance;

        Node(int id, int distance) {
            this.id = id;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    public static void solve(int N, int M, int start, int end, int[] blocked, int[][] edges) {
        // 1. Tandai node yang diblokir dalam boolean array untuk akses O(1)
        // Gunakan N+1 karena node biasanya dimulai dari 1
        boolean[] isBlocked = new boolean[N + 1];
        for (int b : blocked) {
            if (b >= 0 && b <= N) {
                isBlocked[b] = true;
            }
        }

        // 2. Validasi awal: jika start atau end diblokir, jalur tidak mungkin ada
        if (isBlocked[start] || (end <= N && isBlocked[end])) {
            System.out.println("TIDAK ADA JALUR");
            return;
        }

        // 3. Bangun Adjacency List
        List<Edge>[] adj = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            // Tambahkan edge hanya jika node tujuan tidak diblokir 
            // (Node asal dicek saat proses Dijkstra)
            adj[u].add(new Edge(v, w));
        }

        // 4. Algoritma Dijkstra
        int[] dist = new int[N + 1];
        int[] parent = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.id;

            if (current.distance > dist[u]) continue;
            if (u == end) break; // Sudah sampai tujuan terpendek

            for (Edge edge : adj[u]) {
                int v = edge.to;
                // Jangan lewat node yang diblokir
                if (isBlocked[v]) continue;

                if (dist[u] + edge.weight < dist[v]) {
                    dist[v] = dist[u] + edge.weight;
                    parent[v] = u;
                    pq.add(new Node(v, dist[v]));
                }
            }
        }

        // 5. Output Hasil
        if (dist[end] == Integer.MAX_VALUE) {
            System.out.println("TIDAK ADA JALUR");
        } else {
            System.out.println("JALUR TERPENDEK: " + dist[end]);
            
            // Rekonstruksi Rute
            List<Integer> path = new ArrayList<>();
            for (int at = end; at != -1; at = parent[at]) {
                path.add(at);
            }
            Collections.reverse(path);

            System.out.print("RUTE: ");
            for (int i = 0; i < path.size(); i++) {
                System.out.print(path.get(i));
                if (i < path.size() - 1) {
                    System.out.print(" -> ");
                }
            }
            System.out.println();
        }
    }
}
