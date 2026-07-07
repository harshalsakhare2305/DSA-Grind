#include <bits/stdc++.h>
using namespace std;

using ll = long long;

const ll INF = 1e18;
const int MOD = 1e9 + 7;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, m;
    cin >> n >> m;

    vector<vector<pair<int,int>>> adj(n + 1);

    for(int i = 0; i < m; i++) {
        int u, v, w;
        cin >> u >> v >> w;
        adj[u].push_back({v, w});
    }

    vector<ll> dist(n + 1, INF);
    vector<ll> ways(n + 1, 0);
    vector<int> minFlight(n + 1, INT_MAX);
    vector<int> maxFlight(n + 1, INT_MIN);

    priority_queue<
        pair<ll,int>,
        vector<pair<ll,int>>,
        greater<pair<ll,int>>
    > pq;

    dist[1] = 0;
    ways[1] = 1;
    minFlight[1] = 0;
    maxFlight[1] = 0;

    pq.push({0,1});

    while(!pq.empty()) {

        auto [d,u] = pq.top();
        pq.pop();

        if(d > dist[u]) continue;

        for(auto [v,w] : adj[u]) {

            if(dist[u] + w < dist[v]) {

                dist[v] = dist[u] + w;

                ways[v] = ways[u];

                minFlight[v] = minFlight[u] + 1;

                maxFlight[v] = maxFlight[u] + 1;

                pq.push({dist[v], v});
            }
            else if(dist[u] + w == dist[v]) {

                ways[v] = (ways[v] + ways[u]) % MOD;

                minFlight[v] = min(minFlight[v], minFlight[u] + 1);

                maxFlight[v] = max(maxFlight[v], maxFlight[u] + 1);
            }
        }
    }

    cout << dist[n] << " "
         << ways[n] << " "
         << minFlight[n] << " "
         << maxFlight[n] << "\n";

    return 0;
}
