package org.tanglizi.algo;

import java.util.PriorityQueue;

public class MaxClique {

    private PriorityQueue<CliqueNode> que = new PriorityQueue<>();
    private int n, bestn=0;
    private int[][] a;

    public MaxClique(int n, int[][] a){
        this.n = n;
        this.a = a;
    }

    int solve() {
        BBNode e = new BBNode(null, false);
        int i = 1, cn = 0;
        BBNode bbNode;
        CliqueNode cliqueNode;

        while (i != n+1) {
            boolean ok = true;
            BBNode b = e;

            System.out.println(b);

            for (int j = i-1; j>0; b=b.parent, j--) {
                if (b.lChild && a[i][j] == 0) {
                    ok = false;
                    break;
                }

                if (ok) {
                    if (cn + 1 > bestn)
                        bestn = cn + 1;
                    bbNode = new BBNode(e, true);
                    cliqueNode = new CliqueNode(cn + 1, cn + n - i + 1, i + 1, bbNode);
                    que.add(cliqueNode);
                }

                if (cn + n - i >= bestn) {
                    bbNode = new BBNode(e, false);
                    cliqueNode = new CliqueNode(cn, cn + n - i, i + 1, bbNode);
                    que.add(cliqueNode);
                }

                CliqueNode node = que.poll();
                e = node.ptr;
                cn = node.cn;
                i = node.level;

                System.out.println(node);
            }
        }

        return bestn;
    }

    class BBNode {
        BBNode parent;
        boolean lChild;

        public BBNode(BBNode parent, boolean lChild) {
            this.parent = parent;
            this.lChild = lChild;
        }
    }

    class CliqueNode {
        private int cn, un, level;
        private BBNode ptr;

        public CliqueNode(int cn, int un, int level, BBNode ptr) {
            this.cn = cn;
            this.un = un;
            this.level = level;
            this.ptr = ptr;
        }

        public String toString() {
            return "cn: " + cn + " un: " + un + " level: " + level + " ptr: " + ptr;
        }
    }
}
