#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;
const int maxn = 1e6, maxs = 1e6;
int n, s;
long long t[maxn], st[maxs];

int main(void){
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);

    while (scanf("%d%d", &n, &s) == 2){
        memset(st, 0, sizeof(st));
        for (int i=0; i<n; i++) scanf("%lld", &t[i]);
        sort(t, t+n);

        int idx=0;
        long long ans=0;
        while (idx != n){
            for (int i=0; i<s; i++){
                st[i] += t[idx++];
                ans += st[i];
            }
        }

        printf("%0.0lf\n", ans / (double) n);
    }


    return 0;
}
