#include <bits/stdc++.h>
using namespace std;

vector<vector<int>> adj;
int start = -1;

bool dfs(int node, vector<bool> &vis, vector<bool> &rec, vector<int> &parent) {

    rec[node] = true;
    vis[node] = true;

    bool isCycle = false;

    for (int child : adj[node]) {

        if (rec[child]) {
            start = child;
            parent[child] = node;
            isCycle = true;
            break;
        } else {
            if (!vis[child]) {
                parent[child] = node;
                if (dfs(child, vis, rec, parent))
                    return true;
            }
        }
    }

    rec[node] = false;
    return isCycle;
}

void solve() {

    int n, m;
    cin >> n >> m;

    adj.assign(n + 1, {});

    for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;
        adj[u].push_back(v);
    }

    vector<bool> vis(n + 1, false);
    vector<bool> rec(n + 1, false);
    vector<int> parent(n + 1, 0);

    bool isCycle = false;

    for (int i = 1; i <= n; i++) {
        if (!vis[i]) {
            if (dfs(i, vis, rec, parent)) {
                isCycle = true;
                break;
            }
        }
    }

    if (!isCycle) {
        cout << "IMPOSSIBLE\n";
        return;
    }

    vector<int> cycle;

    int size = 0;

    for (int curr = start;; curr = parent[curr]) {

        if (curr == start && cycle.size() > 0)
            break;

        size++;
        cycle.push_back(curr);
    }

    cycle.push_back(start);
    size++;

    cout << size << "\n";

    reverse(cycle.begin(), cycle.end());

    for (int x : cycle)
        cout << x << " ";
    cout << "\n";
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    solve();
    return 0;
}
