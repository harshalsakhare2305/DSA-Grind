//https://cses.fi/problemset/task/1673/
import java.io.*;
import java.util.*;

public class Demo {

    static List<List<Integer>> adj ;

    public static void solve(Scanner sc) {

        int n = sc.nextInt();
        int m = sc.nextInt();
       
        List<int[]> edges =new ArrayList<>();
        adj=new ArrayList<>();
        
        for(int i=0;i<=n;i++){
            adj.add(new ArrayList<>());
        }



        for(int i=0;i<m;i++){
            int u=sc.nextInt();
            int v=sc.nextInt();
            int wt=sc.nextInt();

           edges.add(new int[]{u,v,wt});
           adj.get(u).add(v);

        }
        long INF=-(long)1e18;
        long[] distance=new long[n+1];
        Arrays.fill(distance, INF);

        distance[1]=0;

        for(int i=0;i<n-1;i++){
            for(int[] e:edges){

                int u=e[0];
                int v=e[1];
                int wt=e[2];
                
                if(distance[u]!=INF && distance[u]+wt>distance[v]){
                    distance[v]=distance[u]+wt;
                }
               
            }
        }
      

        // We have check for the positive cycle beause it lead to infinity as the solution

        //One more time relaxing 
        HashSet<Integer> cycle_nodes=new HashSet<>();

        for(int[]e:edges){

              int u=e[0];
                int v=e[1];
                int wt=e[2];
                if(distance[u]!=INF && distance[u]+wt>distance[v]){
                   cycle_nodes.add(v);
                }
        }

        if(cycle_nodes.isEmpty()){
            System.out.println(distance[n]);
            return;
        }

        Queue<Integer> bfs_queue=new LinkedList<>();
        Queue<Integer> cycle_queue=new LinkedList<>();

        boolean[] vis=new boolean[n+1];

        bfs_queue.add(1);
        vis[1]=true;

        while(!bfs_queue.isEmpty()){
            int currnode=bfs_queue.poll();
            
            if(cycle_nodes.contains(currnode)){
                cycle_queue.add(currnode);
            }

            for(int child:adj.get(currnode)){
                
                if(!vis[child]){
                    bfs_queue.add(child);
                    vis[child]=true;
                }
            }

        }

        boolean is_cycle_part=false;

        vis=new boolean[n+1];


          while(!cycle_queue.isEmpty()){

            int currnode=cycle_queue.poll();
            
             if(currnode==n){
                is_cycle_part=true;
                break;
             }

            for(int child:adj.get(currnode)){
                
                if(!vis[child]){
                    cycle_queue.add(child);
                    vis[child]=true;
                }
            }

        }


        if(is_cycle_part){
            System.out.println(-1);
        }else{
            System.out.println(distance[n]);
        }


    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        solve(sc);
        sc.close();
    }
}
