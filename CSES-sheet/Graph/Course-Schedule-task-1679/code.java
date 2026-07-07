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

    public static boolean dfs(int node, boolean[] vis, boolean[] rec,Stack<Integer> topo) {

        rec[node] = true;
        vis[node] = true;
        boolean isCycle = false;
        List<Integer> destinations = adj.get(node);
        for (int child : destinations) {

            if (rec[child]) {
                isCycle = true;
                break;
            } else {
                if (!vis[child]) {
                    if (dfs(child, vis, rec,topo)) {
                        return true;
                    }

                }
            }
        }
           
        rec[node] = false;
        topo.add(node);
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
        boolean isCycle = false;

        Stack<Integer> topo=new Stack<>();
        for (int i = 1; i <= n; i++) {
            if (!vis[i]) {
                boolean status = dfs(i, vis, rec,topo);

                if (status) {
                    isCycle = true;
                    break;
                }
            }
        }

        if (isCycle) {
            System.out.println("IMPOSSIBLE");
            return;
        }

     List<Integer> ans =new ArrayList<>();

     while(!topo.isEmpty()){
        ans.add(topo.pop());
     }

   
     for(int i=0;i<ans.size();i++){
        System.out.print(ans.get(i)+" ");
     }

        

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        solve(sc);
        sc.close();
    }
}
