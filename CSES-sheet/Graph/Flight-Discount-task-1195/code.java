import java.io.*;
import java.util.*;

public class Demo {

   // static char[][] adj;
  ///  static List<Integer> cycle;

    static char[] dir = { 'U', 'R', 'D', 'L' };
    static int[] drow = { -1, 0, 1, 0 };
    static int[] dcol = { 0, 1, 0, -1 };

    static List<int[]> edges;
    static long INF=(long)1e18;

    public static long[] dijkstras(int start,int n,int m,List<List<int[]>> adj){
        long[] distance=new long[n+1];

        Arrays.fill(distance, INF);

        distance[start]=0;

        PriorityQueue<long[]> pq =new PriorityQueue<>((a,b)->Long.compare(a[1], b[1]));

        pq.add(new long[]{start,0,-1});

        while(!pq.isEmpty()){
            long[] curr=pq.poll();

            int u=(int)curr[0];
            long currwt=curr[1];
            int par =(int)curr[1];

            List<int[]> edges=adj.get(u);
            for(int[] e:edges){
             
                int v=e[0];

                if(v==par)continue;
                int wt=e[1];

                if(distance[u]<INF && currwt+wt <  distance[v]){
                    distance[v]=currwt+wt;
                    pq.add(new long[]{v,distance[v],u});
                }
            }
        }

        return distance;
    }

   

    public static void solve(Scanner sc) {

        int n = sc.nextInt();
        int m = sc.nextInt();
       
    
         List<List<int[]>> adj=new ArrayList<>();
         List<List<int[]>> revadj=new ArrayList<>();
        edges=new ArrayList<>();
        
        for(int i=0;i<=n;i++){
            adj.add(new ArrayList<>());
            revadj.add(new ArrayList<>());
        }



        for(int i=0;i<m;i++){
            int u=sc.nextInt();
            int v=sc.nextInt();
            int wt=sc.nextInt();

         
           adj.get(u).add(new int[]{v,wt});
           revadj.get(v).add(new int[]{u,wt});

           edges.add(new int[]{u,v,wt});

        }
        
        long[] distance_from_1=dijkstras(1, n, m,adj);
        long[] distance_from_n=dijkstras(n, n, m,revadj);
        
        long cheapest=INF;

        for(int[]e:edges){

             int u=e[0];
             int v=e[1];
             int wt=e[2];
             
             if(distance_from_1[u]<INF && distance_from_n[v]<INF){

                long res=(long)Math.floor((double)wt/2.0);
                cheapest=Math.min(cheapest,distance_from_1[u]+res+distance_from_n[v]);
             }

        }

        System.out.println(cheapest);


    


    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        solve(sc);
        sc.close();
    }
}
