#include <bits/stdc++.h>
using namespace std;

using ll = long long;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, m, k;
    cin >> n >> m >> k;

    vector<vector<pair<int, int>>> adj(n + 1);
    vector<vector<ll>> k_shortest(n + 1);

    for (int i = 0; i < m; i++) {
        int u, v, w;
        cin >> u >> v >> w;
        adj[u].push_back({v, w});
    }

    priority_queue<
        pair<ll, int>,
        vector<pair<ll, int>>,
        greater<pair<ll, int>>
    > pq;

    pq.push({0, 1}); // {distance, node}

    while (!pq.empty()) {
        auto [wt, u] = pq.top();
        pq.pop();

        if (k_shortest[u].size() >= k)
            continue;

        k_shortest[u].push_back(wt);

        for (auto [v, w] : adj[u]) {
            pq.push({wt + w, v});
        }
    }

    for (ll d : k_shortest[n]) {
        cout << d << " ";
    }

    return 0;
}
