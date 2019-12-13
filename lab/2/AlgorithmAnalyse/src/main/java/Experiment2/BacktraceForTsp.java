package Experiment2;

import java.util.Arrays;

public class BacktraceForTsp {

	int NoEdge = -1;
	int bigInt = Integer.MAX_VALUE;
	int[][] a; // 邻接矩阵
	int cc = 0; // 存储当前代价
	int bestc = bigInt;// 当前最优代价
	int[] x; // 当前解
	int[] bestx;// 当前最优解
	int n = 0; // 顶点个数
	
	private void backtrack(int i) {//i为初始深度
		if (i > n) {
			cc += a[x[n]][1];
			// System.out.print(Arrays.toString(x));
			// System.out.println(" " + cc);
		    if (cc < bestc){
		    	bestc = cc;
		    	bestx = x.clone();
			}
			cc -= a[x[n]][1];
		} else {
			for (int j = i; j <= n; j++){
				swap(i, j);
				if (check(i)) { swap(i, j); continue; }
				if (cutting(i)) { swap(i, j); continue; }

				cc += a[x[i-1]][x[i]];
				backtrack(i+1);
				cc -= a[x[i-1]][x[i]];

				swap(i, j);
			}
		}
	}
	
	private void swap(int i, int j) {
		int temp = x[i];
		x[i] = x[j];
		x[j] = temp;
	}
	
	public boolean check(int pos) {
		if (pos < 2) return false;

		// edge exists checking
		if (a[x[pos - 1]][x[pos]] == NoEdge) return true;
		if (pos == n && a[x[pos]][1] == NoEdge) return true;

		return false;
	}

	private boolean cutting(int pos) {
		// simple branch cutting
		if (cc + a[x[pos-1]][x[pos]] >= bestc) return true;
		return false;
	}
	
	public void backtrack4TSP(int[][] b, int num) {
		n = num;
		x = new int[n + 1];
		for (int i = 0; i <= n; i++)
			x[i] = i;
		bestx = new int[n + 1];
		a = b;
		backtrack(2);
	}

}
