import java.util.*;

public class Demo {

 

    public static void solve(Scanner sc) {

        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        List<List<int[]>> adj = new ArrayList<>();
        List<List<Long>> k_shortest = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
            k_shortest.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();

            adj.get(u).add(new int[] { v, w });
        }

        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));

        pq.add(new long[] { 1, 0 });

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();

            int u = (int) curr[0];
            long wt = curr[1];

            if(k_shortest.get(u).size()>=k)continue;

            k_shortest.get(u).add(wt);

            List<int[]> edges = adj.get(u);
            for (int[] child : edges) {
                int v = child[0];
                int w = child[1];
            pq.add(new long[]{v,wt+w});
            }

        }

        List<Long> dis =k_shortest.get(n);
        for(int i=0;i<k;i++){
           
        System.out.print(dis.get(i)+" ");

            
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        solve(sc);
        sc.close();
    }
}
