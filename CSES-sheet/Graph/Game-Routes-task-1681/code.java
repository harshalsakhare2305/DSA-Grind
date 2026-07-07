import java.util.*;

public class Demo {

   

    static List<List<Integer>> adj;

    public static boolean dfs(int node, boolean[] vis, boolean[] rec,Stack<Integer> topo) {

        rec[node] = true;
        vis[node] = true;
     
        List<Integer> destinations = adj.get(node);
        for (int child : destinations) {

            if (rec[child]) {
               return true;
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
        return false;
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

        if (isCycle) { // No pssible for this question
            System.out.println("IMPOSSIBLE");
            return;
        }

     List<Integer> topoList =new ArrayList<>();

     while(!topo.isEmpty()){
        topoList.add(topo.pop());
     }

    long[] dp =new long[n+1];

  
    Arrays.fill(dp,0);

    int MOD=(int)1e9+7;
   

    Arrays.fill(dp,0);
    dp[1]=1;

  
    for(int u:topoList){
        
        if(dp[u]==0)continue;

        List<Integer> edges=adj.get(u);
        for(int v:edges){
            
            dp[v]=(dp[v]+dp[u])%MOD;
            dp[v]%=MOD;
        }
    }

  

    System.out.println(dp[n]);

   
    
  



    
     

        

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        solve(sc);
        sc.close();
    }
}
