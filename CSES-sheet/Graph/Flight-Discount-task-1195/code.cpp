#include <bits/stdc++.h>
using namespace std;

using ll = long long;

const ll INF = 1e18;

struct Edge {
    int u, v, wt;
};

vector<ll> dijkstra(int start, int n, vector<vector<pair<int, int>>> &adj) {
    vector<ll> dist(n + 1, INF);

    priority_queue<pair<ll, int>, vector<pair<ll, int>>, greater<pair<ll, int>>> pq;

    dist[start] = 0;
    pq.push({0, start});

    while (!pq.empty()) {
        auto [currwt, u] = pq.top();
        pq.pop();

        if (currwt != dist[u])
            continue;

        for (auto &[v, wt] : adj[u]) {
            if (currwt + wt < dist[v]) {
                dist[v] = currwt + wt;
                pq.push({dist[v], v});
            }
        }
    }

    return dist;
}

void solve() {
    int n, m;
    cin >> n >> m;

    vector<vector<pair<int, int>>> adj(n + 1), revadj(n + 1);
    vector<Edge> edges;

    for (int i = 0; i < m; i++) {
        int u, v, wt;
        cin >> u >> v >> wt;

        adj[u].push_back({v, wt});
        revadj[v].push_back({u, wt});

        edges.push_back({u, v, wt});
    }

    vector<ll> dist1 = dijkstra(1, n, adj);
    vector<ll> distN = dijkstra(n, n, revadj);

    ll cheapest = INF;

    for (auto &e : edges) {
        if (dist1[e.u] != INF && distN[e.v] != INF) {
            ll cost = dist1[e.u] + e.wt / 2 + distN[e.v];
            cheapest = min(cheapest, cost);
        }
    }

    cout << cheapest << '\n';
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    solve();

    return 0;
}
