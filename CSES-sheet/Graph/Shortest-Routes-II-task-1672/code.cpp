//https://cses.fi/problemset/task/1672/
#include <bits/stdc++.h>
using namespace std;

using ll = long long;

void solve() {
    int n, m, q;
    cin >> n >> m >> q;

    const ll INF = (ll)1e16;

    vector<vector<ll>> distance(n + 1, vector<ll>(n + 1, INF));

    for (int i = 1; i <= n; i++) {
        distance[i][i] = 0;
    }

    for (int i = 0; i < m; i++) {
        int u, v, wt;
        cin >> u >> v >> wt;

        // Keep the minimum weight in case of multiple edges
        distance[u][v] = min(distance[u][v], (ll)wt);
        distance[v][u] = min(distance[v][u], (ll)wt);
    }

    // Floyd-Warshall
    for (int k = 1; k <= n; k++) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (distance[i][k] != INF && distance[k][j] != INF) {
                    distance[i][j] = min(distance[i][j],
                                         distance[i][k] + distance[k][j]);
                }
            }
        }
    }

    while (q--) {
        int a, b;
        cin >> a >> b;

        if (distance[a][b] == INF)
            cout << -1 << '\n';
        else
            cout << distance[a][b] << '\n';
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    solve();

    return 0;
}
