import java.util.*;

public class Demo {

    // static class Edge {
    // int u, v, w;

    // Edge(int u, int v, int w) {
    // this.u = u;
    // this.v = v;
    // this.w = w;
    // }
    // }

    static List<List<Integer>> adj;
    static int start = -1;

    public static boolean dfs(int node, boolean[] vis, boolean[] rec, int[] parent) {

        rec[node] = true;
        vis[node] = true;
        boolean isCycle = false;
        List<Integer> destinations = adj.get(node);
        for (int child : destinations) {

            if (rec[child]) {
                start = child;
                parent[child] = node;
                isCycle = true;
                break;
            } else {
                if (!vis[child]) {
                    parent[child] = node;
                    if (dfs(child, vis, rec, parent)) {
                        return true;
                    }

                }
            }
        }

        rec[node] = false;
        return isCycle;
    }

    public static void solve(Scanner sc) {

        int n = sc.nextInt();
        int m = sc.nextInt();

        adj = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj.get(u).add(v);
        }

        boolean[] vis = new boolean[n + 1];
        boolean[] rec = new boolean[n + 1];
        int[] parent = new int[n + 1];
        boolean isCycle = false;
        for (int i = 1; i <= n; i++) {
            if (!vis[i]) {
                boolean status = dfs(i, vis, rec, parent);

                if (status) {
                    isCycle = true;
                    break;
                }
            }
        }

        if (!isCycle) {
            System.out.println("IMPOSSIBLE");
            return;
        }

        StringBuilder sb = new StringBuilder("");

        int size = 0;
        for (int curr = start;; curr = parent[curr]) {

            if (curr == start && sb.length() > 1) {
                break;
            }
            size++;
            sb.append(curr).append(" ");
        }

        sb.append(start);
        size++;

        System.out.println(size);
        String[] arr = sb.toString().trim().split(" ");
        for (int i = arr.length - 1; i >= 0; i--) {
            System.out.print(arr[i] + " ");
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        solve(sc);
        sc.close();
    }
}
