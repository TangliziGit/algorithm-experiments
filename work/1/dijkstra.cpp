#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;
const int maxn=1e2+5;
const long long INF=0x3f3f3f3f;
int n, m;
long long edges[maxn][maxn], dist[maxn];
bool vis[maxn];

long long dijkstra(int start, int end){
    memset(vis, false, sizeof(vis));
    for (int i=1; i<=n; i++)
        dist[i]=edges[start][i];
    dist[start]=0;
    vis[start]=true;

    for (int i=1; i<n; i++){
        int idx=0, mindis=INF;
        for (int j=1; j<=n; j++) if (!vis[j] && dist[j]<mindis){
            idx=j; mindis=dist[j];
        }

        vis[idx]=true;
        for (int j=1; j<=n; j++) if (!vis[j])
            dist[j]=min(dist[j], dist[idx]+edges[idx][j]);
    }

    return dist[end];
}

int main(void){
    freopen("dijkstra.txt", "r", stdin);
    int from, to;

    while (scanf("%d%d", &m, &n)==2){
        for (int i=1; i<=n; i++){
            for (int j=1; j<=n; j++)
                edges[i][j]=INF;
            edges[i][i]=0;
        }

        for (int i=0; i<m; i++){
            scanf("%d%d", &from, &to);
            scanf("%lld", &edges[from][to]);
        }

        printf("%lld\n", dijkstra(1, n));
    }

    return 0;
}
