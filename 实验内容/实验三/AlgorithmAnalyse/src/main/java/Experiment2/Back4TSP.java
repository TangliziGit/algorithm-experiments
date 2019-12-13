package Experiment2;

public class Back4TSP {

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
			//TODO
		} else {
			//TODO
			
		}

	}
	
	private void swap(int i, int j) {
		int temp = x[i];
		x[i] = x[j];
		x[j] = temp;
	}
	
	public boolean check(int pos) {
		//TODO
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
