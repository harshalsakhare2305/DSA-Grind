//https://cses.fi/problemset/task/1668/
#include <bits/stdc++.h>
using namespace std;

vector<vector<int>> adj;

bool dfs(int node, int par, vector<bool> &vis, bool isTeam1, vector<int> &team) {
    vis[node] = true;
    team[node] = isTeam1 ? 1 : 2;

    for (int child : adj[node]) {
        if (child == par)
            continue;

        if (!vis[child]) {
            if (!dfs(child, node, vis, !isTeam1, team))
                return false;
        } else if (team[child] != -1 && team[node] == team[child]) {
            return false;
        }
    }

    return true;
}

void solve() {
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
    vector<int> team(n + 1, -1);

    for (int i = 1; i <= n; i++) {
        if (!vis[i]) {
            if (!dfs(i, -1, vis, true, team)) {
                cout << "IMPOSSIBLE\n";
                return;
            }
        }
    }

    for (int i = 1; i <= n; i++) {
        cout << team[i] << " ";
    }
    cout << '\n';
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    solve();
    return 0;
}
