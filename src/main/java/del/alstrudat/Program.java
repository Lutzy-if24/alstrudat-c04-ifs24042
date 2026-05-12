package del.alstrudat;

import java.util.*;

public class Program {
    public static void solve(int N, int M, int start, int end, int[] blocked, int[][] edges) {
        Set<Integer> blockedSet = new HashSet<>();
        for (int b : blocked) blockedSet.add(b);

        if (blockedSet.contains(start) || blockedSet.contains(end)) {
            System.out.println("TIDAK ADA JALUR");
            return;
        }

        if (start == end) {
            System.out.println("JALUR TERPENDEK: 0");
            System.out.println("RUTE: " + start);
            return;
        }

        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int i = 1; i <= N; i++)
            if (!blockedSet.contains(i)) graph.put(i, new ArrayList<>());

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            if (!blockedSet.contains(u) && !blockedSet.contains(v))
                graph.get(u).add(new int[]{v, w});
        }

        int[] dist = new int[N + 1];
        int[] prev = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);
        dist[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, start});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            if (curr[0] > dist[curr[1]]) continue;
            if (graph.containsKey(curr[1])) {
                for (int[] neighbor : graph.get(curr[1])) {
                    if (dist[curr[1]] + neighbor[1] < dist[neighbor[0]]) {
                        dist[neighbor[0]] = dist[curr[1]] + neighbor[1];
                        prev[neighbor[0]] = curr[1];
                        pq.offer(new int[]{dist[neighbor[0]], neighbor[0]});
                    }
                }
            }
        }

        if (dist[end] == Integer.MAX_VALUE) {
            System.out.println("TIDAK ADA JALUR");
            return;
        }

        List<Integer> path = new ArrayList<>();
        int curr = end;
        while (curr != -1) { path.add(curr); curr = prev[curr]; }
        Collections.reverse(path);

        System.out.println("JALUR TERPENDEK: " + dist[end]);
        StringBuilder route = new StringBuilder("RUTE: ");
        for (int i = 0; i < path.size(); i++) {
            route.append(path.get(i));
            if (i < path.size() - 1) route.append(" -> ");
        }
        System.out.println(route);
    }
}
