#include <bits/stdc++.h>
using namespace std;

using ll = long long;

vector<vector<int>> adj;

void solve() {
    int n, m;
    cin >> n >> m;

    vector<array<int, 3>> edges;
    adj.assign(n + 1, {});

    for (int i = 0; i < m; i++) {
        int u, v, wt;
        cin >> u >> v >> wt;

        edges.push_back({u, v, wt});
        adj[u].push_back(v);
    }

    const ll INF = -(ll)1e18;
    vector<ll> distance(n + 1, INF);

    distance[1] = 0;

    // Bellman-Ford for maximum path
    for (int i = 0; i < n - 1; i++) {
        for (auto &e : edges) {
            int u = e[0];
            int v = e[1];
            int wt = e[2];

            if (distance[u] != INF && distance[u] + wt > distance[v]) {
                distance[v] = distance[u] + wt;
            }
        }
    }

    // Find nodes that are part of or affected by a positive cycle
    unordered_set<int> cycle_nodes;

    for (auto &e : edges) {
        int u = e[0];
        int v = e[1];
        int wt = e[2];

        if (distance[u] != INF && distance[u] + wt > distance[v]) {
            cycle_nodes.insert(v);
        }
    }

    if (cycle_nodes.empty()) {
        cout << distance[n] << '\n';
        return;
    }

    queue<int> bfs_queue;
    queue<int> cycle_queue;
    vector<bool> vis(n + 1, false);

    bfs_queue.push(1);
    vis[1] = true;

    // Find cycle nodes reachable from source
    while (!bfs_queue.empty()) {
        int curr = bfs_queue.front();
        bfs_queue.pop();

        if (cycle_nodes.count(curr))
            cycle_queue.push(curr);

        for (int child : adj[curr]) {
            if (!vis[child]) {
                vis[child] = true;
                bfs_queue.push(child);
            }
        }
    }

    bool is_cycle_part = false;
    vis.assign(n + 1, false);

    while (!cycle_queue.empty()) {
        int curr = cycle_queue.front();
        cycle_queue.pop();

        if (curr == n) {
            is_cycle_part = true;
            break;
        }

        for (int child : adj[curr]) {
            if (!vis[child]) {
                vis[child] = true;
                cycle_queue.push(child);
            }
        }
    }

    if (is_cycle_part)
        cout << -1 << '\n';
    else
        cout << distance[n] << '\n';
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    solve();

    return 0;
}
