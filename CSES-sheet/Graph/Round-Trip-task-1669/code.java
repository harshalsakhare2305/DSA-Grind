//https://cses.fi/problemset/task/1669/
import java.io.*;
import java.util.*;

public class Demo {

    static List<List<Integer>> adj;
    static List<Integer> cycle;

    public static boolean dfs(int node, int par, boolean[] vis, int[] parent) {

        vis[node] = true;
        parent[node] = par;

        for (int child : adj.get(node)) {

            if (child == par)
                continue;

            if (!vis[child]) {

                if (dfs(child, node, vis, parent))
                    return true;

            } else {

                // Back edge found => cycle
                cycle = new ArrayList<>();

                cycle.add(child);

                int cur = node;
                while (cur != child) {
                    cycle.add(cur);
                    cur = parent[cur];
                }

                cycle.add(child);
                Collections.reverse(cycle);

                return true;
            }
        }

        return false;
    }

    public static void solve(Scanner sc) {
        adj = new ArrayList<>();

        int n = sc.nextInt();
        int m = sc.nextInt();

        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        boolean[] vis = new boolean[n + 1];
        int[] parent = new int[n + 1];
        Arrays.fill(parent, -1);

        cycle = null;

        boolean found = false;

        for (int i = 1; i <= n; i++) {
            if (!vis[i]) {
                if (dfs(i, -1, vis, parent)) {
                    found = true;
                    break;
                }
            }
        }

        if (!found) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(cycle.size());
            for (int x : cycle)
                System.out.print(x + " ");
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        solve(sc);
        sc.close();
    }
}
