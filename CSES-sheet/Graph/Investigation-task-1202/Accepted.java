import java.util.*;

public class Demo {

    static class Edge {
        int to, wt;
        Edge(int to, int wt) {
            this.to = to;
            this.wt = wt;
        }
    }

    static class Node implements Comparable<Node> {
        int node;
        long dist;

        Node(int node, long dist) {
            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node other) {
            return Long.compare(this.dist, other.dist);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        List<List<Edge>> adj = new ArrayList<>();

        for(int i = 0; i <= n; i++)
            adj.add(new ArrayList<>());

        for(int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();

            adj.get(u).add(new Edge(v, w));
        }

        long INF = (long)1e18;
        int MOD = (int)1e9 + 7;

        long[] dist = new long[n + 1];
        long[] ways = new long[n + 1];
        int[] minFlight = new int[n + 1];
        int[] maxFlight = new int[n + 1];

        Arrays.fill(dist, INF);
        Arrays.fill(minFlight, Integer.MAX_VALUE);
        Arrays.fill(maxFlight, Integer.MIN_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>();

        dist[1] = 0;
        ways[1] = 1;
        minFlight[1] = 0;
        maxFlight[1] = 0;

        pq.offer(new Node(1, 0));

        while(!pq.isEmpty()) {

            Node curr = pq.poll();

            int u = curr.node;

            if(curr.dist > dist[u])
                continue;

            for(Edge e : adj.get(u)) {

                int v = e.to;
                int w = e.wt;

                if(dist[u] + w < dist[v]) {

                    dist[v] = dist[u] + w;

                    ways[v] = ways[u];

                    minFlight[v] = minFlight[u] + 1;

                    maxFlight[v] = maxFlight[u] + 1;

                    pq.offer(new Node(v, dist[v]));
                }
                else if(dist[u] + w == dist[v]) {

                    ways[v] = (ways[v] + ways[u]) % MOD;

                    minFlight[v] = Math.min(minFlight[v], minFlight[u] + 1);

                    maxFlight[v] = Math.max(maxFlight[v], maxFlight[u] + 1);
                }
            }
        }

        System.out.println(dist[n] + " " +
                           ways[n] + " " +
                           minFlight[n] + " " +
                           maxFlight[n]);

        sc.close();
    }
}
