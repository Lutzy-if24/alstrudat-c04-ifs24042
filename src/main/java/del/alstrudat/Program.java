package del.alstrudat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class Program {

  public static void solve(int n, int m, int start, int end,
      int[] blocked, int[][] edges) {

    Set<Integer> blockedSet = new HashSet<>();
    for (int b : blocked) {
      blockedSet.add(b);
    }

    // Jika start atau end diblokir, tidak ada jalur
    if (blockedSet.contains(start) || blockedSet.contains(end)) {
      System.out.print("TIDAK ADA JALUR");
      return;
    }

    // Kasus khusus: start == end
    if (start == end) {
      System.out.println("JALUR TERPENDEK: 0");
      System.out.print("RUTE: " + start);
      return;
    }

    // Bangun adjacency list (graf berarah / directed)
    List<List<int[]>> adj = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      adj.add(new ArrayList<>());
    }
    for (int[] e : edges) {
      adj.get(e[0]).add(new int[]{e[1], e[2]});
      // Tidak tambah arah balik karena graf BERARAH (directed)
    }

    long[] dist = new long[n + 1];
    int[] prev = new int[n + 1];
    Arrays.fill(dist, Long.MAX_VALUE);
    Arrays.fill(prev, -1);
    dist[start] = 0;

    PriorityQueue<long[]> pq = new PriorityQueue<>(
        (a, b) -> Long.compare(a[0], b[0]));
    pq.offer(new long[]{0, start});

    while (!pq.isEmpty()) {
      long[] curr = pq.poll();
      long d = curr[0];
      int u = (int) curr[1];

      if (d > dist[u]) {
        continue;
      }

      for (int[] next : adj.get(u)) {
        int v = next[0];
        int w = next[1];

        // Lewati node yang diblokir (tidak bisa dilewati sama sekali)
        if (blockedSet.contains(v)) {
          continue;
        }

        if (dist[u] + w < dist[v]) {
          dist[v] = dist[u] + w;
          prev[v] = u;
          pq.offer(new long[]{dist[v], v});
        }
      }
    }

    if (dist[end] == Long.MAX_VALUE) {
      System.out.print("TIDAK ADA JALUR");
    } else {
      System.out.println("JALUR TERPENDEK: " + dist[end]);

      // Rekonstruksi jalur
      List<Integer> path = new ArrayList<>();
      int cur = end;
      while (cur != -1) {
        path.add(cur);
        cur = prev[cur];
      }
      Collections.reverse(path);

      StringBuilder sb = new StringBuilder("RUTE: ");
      for (int i = 0; i < path.size(); i++) {
        if (i > 0) {
          sb.append(" -> ");
        }
        sb.append(path.get(i));
      }
      System.out.print(sb.toString());
    }
  }
}
