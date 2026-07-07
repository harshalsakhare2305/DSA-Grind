#include <bits/stdc++.h>
using namespace std;

vector<vector<int>> adj;

bool dfs(int node, vector<bool> &vis, vector<bool> &rec, stack<int> &topo) {
    vis[node] = true;
    rec[node] = true;

    for (int child : adj[node]) {
        if (rec[child]) {
            return true;
        } else if (!vis[child]) {
            if (dfs(child, vis, rec, topo))
                return true;
        }
    }

    rec[node] = false;
    topo.push(node);
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
    }

    vector<bool> vis(n + 1, false);
    vector<bool> rec(n + 1, false);
    stack<int> topo;

    bool isCycle = false;

    for (int i = 1; i <= n; i++) {
        if (!vis[i]) {
            if (dfs(i, vis, rec, topo)) {
                isCycle = true;
                break;
            }
        }
    }

    if (isCycle) {
        cout << "IMPOSSIBLE\n";
        return 0;
    }

    while (!topo.empty()) {
        cout << topo.top() << " ";
        topo.pop();
    }

    return 0;
}
