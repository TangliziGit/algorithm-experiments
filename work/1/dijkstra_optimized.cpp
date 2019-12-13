#include <queue>
#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;
const int maxn=1e2+5, maxm=maxn*maxn*2+5;
const long long INF=0x3f3f3f3f;
typedef pair<long long, int> Node;
struct Edge{
    int to, dis, next;
    Edge(){}
    Edge(int to, int dis, int next):
        to(to), dis(dis), next(next) {}
}edges[maxm];
int head[maxn], esiz;
long long dist[maxn];
int n, m;

void init(void){
    memset(head, -1, sizeof(head));
    esiz=0;
}

void addEdge(int from, int to, int dis){
    edges[esiz] = Edge(to, dis, head[from]);
    head[from] = esiz++;
}

long long dij(int start, int end){
    priority_queue<Node> que;
    
    for (int i=0; i<=n; i++) dist[i] = INF;
    dist[start] = 0;
    que.push(Node(0, start));

    while (!que.empty()){
        Node x = que.top(); que.pop();
        if (x.first != dist[x.second]) continue;

        int from = x.second;
        for (int i=head[from]; i!=-1; i=edges[i].next){
            Edge &e = edges[i];

            if (dist[e.to] < dist[from] + e.dis) continue;
            dist[e.to] = dist[from] + e.dis;
            que.push(Node(dist[e.to], e.to));
        }
    }
    return dist[end];
}

int main(void){
    freopen("dijkstra.txt", "r", stdin);
    int from, to;
    long long dis;

    while (scanf("%d%d", &m, &n) == 2){
        init();
        for (int i=0; i<m; i++){
            scanf("%d%d%lld", &from, &to, &dis);
            addEdge(from, to, dis);
        }

        printf("%lld\n", dij(1, n));
    }

    return 0;
}
