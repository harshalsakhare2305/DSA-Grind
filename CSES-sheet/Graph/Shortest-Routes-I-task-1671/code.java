import java.io.*;
import java.util.*;

public class Demo {

   // static char[][] adj;
  ///  static List<Integer> cycle;

    static char[] dir = { 'U', 'R', 'D', 'L' };
    static int[] drow = { -1, 0, 1, 0 };
    static int[] dcol = { 0, 1, 0, -1 };

    // public static boolean isBoundary(int row, int col, int n, int m) {
    //     return (row == n - 1 || row == 0 || col == 0 || col == m - 1);
    // }

    // public static boolean isValid(int r, int c, int n, int m) {
    //     return (r >= 0 && r < n && c >= 0 && c < m);
    // }

    static List<List<int[]>> adj;

    public static void solve(Scanner sc) {

        int n = sc.nextInt();
        int m = sc.nextInt();

        // adj = new char[n][m];

        // for (int i = 0; i < n; i++) {
        //     String line = sc.next();

        //     for (int j = 0; j < line.length(); j++) {
        //         adj[i][j] = line.charAt(j);
        //     }

        // }

        adj=new ArrayList<>();

        for(int i=0;i<=n;i++){
            adj.add(new ArrayList<>());
        }

        for(int i=0;i<m;i++){
            int u=sc.nextInt();
            int v=sc.nextInt();
            int wt=sc.nextInt();

            adj.get(u).add(new int[]{v,wt});
        }


        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
        long[] distance = new long[n+1];
        long INF = (long)1e17;
        Arrays.fill(distance, INF);

        distance[1] = 0;
        pq.add(new long[]{1, 0});

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();

            int node = (int) curr[0];
            long currwt = curr[1];

            if(currwt>distance[node])continue;

            //caching the adj list to reduce number of get calls

            List<int[]> edges = adj.get(node);

            for (int[] neigh : edges) {
                
                int dest = neigh[0];

                if(dest==node)continue;
                int wt = neigh[1];

                if (currwt + wt < distance[dest]) {
                    distance[dest] = currwt + wt;
                    pq.add(new long[]{dest, distance[dest]});
                }
            }
        }

        for(int i=1;i<=n;i++){
            System.out.print(distance[i]+" ");
        }




    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        solve(sc);
        sc.close();
    }
}
