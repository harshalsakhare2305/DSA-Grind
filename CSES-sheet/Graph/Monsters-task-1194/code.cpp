#include <bits/stdc++.h>
using namespace std;

const int INF = 1e9;

int n, m;
vector<string> grid;

int dr[] = {-1, 0, 1, 0};
int dc[] = {0, 1, 0, -1};
char dir[] = {'U', 'R', 'D', 'L'};

struct Point {
    int r, c;
};

bool isValid(int r, int c) {
    return r >= 0 && r < n && c >= 0 && c < m;
}

bool isBoundary(int r, int c) {
    return r == 0 || c == 0 || r == n - 1 || c == m - 1;
}

vector<vector<int>> bfsMonsters() {

    vector<vector<int>> monsterDist(n, vector<int>(m, INF));
    vector<vector<bool>> vis(n, vector<bool>(m, false));

    queue<Point> q;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {

            if (grid[i][j] == 'M') {
                q.push({i, j});
                vis[i][j] = true;
                monsterDist[i][j] = 0;
            }
            else if (grid[i][j] == '#') {
                vis[i][j] = true;
            }
        }
    }

    while (!q.empty()) {

        Point cur = q.front();
        q.pop();

        for (int k = 0; k < 4; k++) {

            int nr = cur.r + dr[k];
            int nc = cur.c + dc[k];

            if (!isValid(nr, nc) || vis[nr][nc])
                continue;

            vis[nr][nc] = true;
            monsterDist[nr][nc] = monsterDist[cur.r][cur.c] + 1;
            q.push({nr, nc});
        }
    }

    return monsterDist;
}

void bfsPlayer(Point start, vector<vector<int>>& monsterDist) {

    queue<Point> q;

    vector<vector<bool>> vis(n, vector<bool>(m, false));
    vector<vector<int>> dist(n, vector<int>(m, 0));
    vector<vector<char>> parent(n, vector<char>(m));

    q.push(start);
    vis[start.r][start.c] = true;

    Point end = {-1, -1};

    while (!q.empty()) {

        Point cur = q.front();
        q.pop();

        if (isBoundary(cur.r, cur.c)) {
            end = cur;
            break;
        }

        for (int k = 0; k < 4; k++) {

            int nr = cur.r + dr[k];
            int nc = cur.c + dc[k];

            if (!isValid(nr, nc))
                continue;

            if (vis[nr][nc] || grid[nr][nc] == '#')
                continue;

            if (dist[cur.r][cur.c] + 1 >= monsterDist[nr][nc])
                continue;

            vis[nr][nc] = true;
            dist[nr][nc] = dist[cur.r][cur.c] + 1;
            parent[nr][nc] = dir[k];

            q.push({nr, nc});
        }
    }

    if (end.r == -1) {
        cout << "NO\n";
        return;
    }

    string path;

    int r = end.r;
    int c = end.c;

    while (!(r == start.r && c == start.c)) {

        char ch = parent[r][c];
        path += ch;

        if (ch == 'U')
            r++;
        else if (ch == 'D')
            r--;
        else if (ch == 'L')
            c++;
        else
            c--;
    }

    reverse(path.begin(), path.end());

    cout << "YES\n";
    cout << path.size() << '\n';
    cout << path << '\n';
}

int main() {

    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> n >> m;

    grid.resize(n);

    Point start;

    for (int i = 0; i < n; i++) {
        cin >> grid[i];

        for (int j = 0; j < m; j++) {
            if (grid[i][j] == 'A')
                start = {i, j};
        }
    }

    vector<vector<int>> monsterDist = bfsMonsters();

    bfsPlayer(start, monsterDist);

    return 0;
}
