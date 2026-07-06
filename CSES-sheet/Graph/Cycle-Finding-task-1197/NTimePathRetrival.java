import java.util.*;

public class Demo {

    static class Edge {
        int u, v, w;

        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    public static void solve(Scanner sc) {

        int n = sc.nextInt();
        int m = sc.nextInt();

        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            edges.add(new Edge(u, v, w));
        }

        // Initialize all distances to 0 so every component is checked
        long[] dist = new long[n + 1];
        int[] parent = new int[n + 1];
        Arrays.fill(parent, -1);

        int x = -1;

        // Bellman-Ford
        for (int i = 1; i <= n; i++) {
            x = -1;

            for (Edge e : edges) {
                if (dist[e.u] + e.w < dist[e.v]) {
                    dist[e.v] = dist[e.u] + e.w;
                    parent[e.v] = e.u;
                    x = e.v;
                }
            }
        }

        if (x == -1) {
            System.out.println("NO");
            return;
        }

        // Move inside the cycle
        for (int i = 0; i < n; i++) {
            x = parent[x];
        }

        // Recover the cycle
        List<Integer> cycle = new ArrayList<>();
        int cur = x;

        do {
            cycle.add(cur);
            cur = parent[cur];
        } while (cur != x);

        cycle.add(x);
        Collections.reverse(cycle);

        System.out.println("YES");
        for (int node : cycle) {
            System.out.print(node + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        solve(sc);
        sc.close();
    }
}
