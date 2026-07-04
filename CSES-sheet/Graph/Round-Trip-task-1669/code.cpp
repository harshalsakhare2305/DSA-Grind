#include <bits/stdc++.h>
using namespace std;

vector<vector<int>> adj;
vector<int> cycle;

bool dfs(int node, int par, vector<bool> &vis, vector<int> &parent) {
    vis[node] = true;
    parent[node] = par;

    for (int child : adj[node]) {

        if (child == par)
            continue;

        if (!vis[child]) {

            if (dfs(child, node, vis, parent))
                return true;

        } else {

            // Back edge found => cycle
            cycle.clear();

            cycle.push_back(child);

            int cur = node;
            while (cur != child) {
                cycle.push_back(cur);
                cur = parent[cur];
            }

            cycle.push_back(child);
            reverse(cycle.begin(), cycle.end());

            return true;
        }
    }

    return false;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, m;
    cin >> n >> m;

    adj.assign(n + 1, vector<int>());

    for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    vector<bool> vis(n + 1, false);
    vector<int> parent(n + 1, -1);

    bool found = false;

    for (int i = 1; i <= n; i++) {
        if (!vis[i]) {
            if (dfs(i, -1, vis, parent)) {
                found = true;
                break;
            }
        }
    }

    if (!found) {
        cout << "IMPOSSIBLE\n";
    } else {
        cout << cycle.size() << '\n';
        for (int x : cycle)
            cout << x << " ";
        cout << '\n';
    }

    return 0;
}
