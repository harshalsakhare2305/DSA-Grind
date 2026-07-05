//https://cses.fi/problemset/task/1671/
#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n, m;
    cin >> n >> m;

    vector<vector<pair<int, int>>> adj(n + 1);

    for (int i = 0; i < m; i++) {
        int u, v, wt;
        cin >> u >> v >> wt;
        adj[u].push_back({v, wt});
    }

    const long long INF = 1e17;
    vector<long long> distance(n + 1, INF);

    priority_queue<
        pair<long long, int>,
        vector<pair<long long, int>>,
        greater<pair<long long, int>>
    > pq;

    distance[1] = 0;
    pq.push({0, 1}); // {distance, node}

    while (!pq.empty()) {
        auto [currwt, node] = pq.top();
        pq.pop();

        if (currwt > distance[node])
            continue;

        // Cache adjacency list
        const auto &edges = adj[node];

        for (const auto &[dest, wt] : edges) {

            if (dest == node)
                continue;

            if (currwt + wt < distance[dest]) {
                distance[dest] = currwt + wt;
                pq.push({distance[dest], dest});
            }
        }
    }

    for (int i = 1; i <= n; i++) {
        cout << distance[i] << " ";
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    solve();

    return 0;
}
