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

   // static List<List<int[]>> adj;

    public static void solve(Scanner sc) {

        int n = sc.nextInt();
        int m = sc.nextInt();
        int q=sc.nextInt();

        // adj = new char[n][m];

        // for (int i = 0; i < n; i++) {
        //     String line = sc.next();

        //     for (int j = 0; j < line.length(); j++) {
        //         adj[i][j] = line.charAt(j);
        //     }

        // }

      //  adj=new ArrayList<>();

        // for(int i=0;i<=n;i++){
        //     adj.add(new ArrayList<>());
        // }

         long[][] distance=new long[n+1][n+1];

         long INF=(long)1e16;

         for(long[] dis:distance){
            Arrays.fill(dis,INF);
         }

        for(int i=0;i<m;i++){
            int u=sc.nextInt();
            int v=sc.nextInt();
            int wt=sc.nextInt();
          distance[u][v]=wt;
          distance[v][u]=wt;
        }

        for(int i=1;i<=n;i++){
            distance[i][i]=0;
        }


        for(int k=1;k<=n;k++){
            for(int i=1;i<=n;i++){
                for(int j=1;j<=n;j++){

                    distance[i][j]=Math.min(distance[i][j], distance[i][k]+distance[k][j]);
                }
            }
        }

        while(q-->0){
            int a=sc.nextInt();
            int b=sc.nextInt();

            if(distance[a][b]==INF){
              System.out.println(-1);
            }else{
                System.out.println(distance[a][b]);
            }
        }



    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        solve(sc);
        sc.close();
    }
}
