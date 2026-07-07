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

    vector<bool> vis(n + 1, false), rec(n + 1, false);
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

    if (isCycle) {// Never get printed
        cout << "IMPOSSIBLE\n";
        return 0;
    }

    vector<int> topoList;
    while (!topo.empty()) {
        topoList.push_back(topo.top());
        topo.pop();
    }

    const int MOD = 1e9 + 7;
    vector<long long> dp(n + 1, 0);

    dp[1] = 1;

    for (int u : topoList) {
        if (dp[u] == 0)
            continue;

        for (int v : adj[u]) {
            dp[v] = (dp[v] + dp[u]) % MOD;
        }
    }

   

    cout << dp[n] << '\n';

    return 0;
}
