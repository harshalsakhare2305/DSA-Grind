import java.io.*;
import java.util.*;

public class Demo {

    static List<List<Integer>> adj;

    public static boolean dfs(int node, int par, boolean[] vis, boolean isTeam1, int[] team) {
        vis[node] = true;
        team[node] = isTeam1 ? 1 : 2;

        for (int child : adj.get(node)) {
            if (child == par)
                continue;
            if (!vis[child]) {
                if (!vis[child]) {
                    if (!dfs(child, node, vis, !isTeam1, team)) {
                        return false;
                    }
                }
            } else if (vis[child] && team[child] != -1 && team[node] == team[child]) {
                return false;
            }
        }

        return true;
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
        int[] team = new int[n + 1];

        Arrays.fill(team, -1);

        for (int i = 1; i <= n; i++) {
            if (!vis[i]) {
                boolean isBipartite = dfs(i, -1, vis, true, team);

                if (!isBipartite) {
                    System.out.println("IMPOSSIBLE");
                    return;
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            System.out.print(team[i] + " ");
        }

    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        solve(sc);
        sc.close();
    }
}
