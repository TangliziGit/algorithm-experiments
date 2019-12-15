package Experiment3;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class GAOperations {

	private int popNum = 20;
	private int length = 5;
	private int codeNum = 5;
	private int[] codes = {1, 2, 3, 4, 5};
	private int[] codeCount = {1, 1, 1, 1, 1};
	private static Random random = new Random(System.currentTimeMillis());

	public static double pc = 0.6;
	public static double pm = 0.01;

	public int[][] props;
	public int[][] matrix;
	public double[] fitness;

	public GAOperations(){}

	public GAOperations(int[][] matrix, int length){
		this.matrix = matrix;
		this.props = new int[popNum][length];
		this.fitness = new double[popNum];

		this.length = length;
		this.codeNum = length;
		this.codes = IntStream.iterate(1, i -> i + 1).limit(length).toArray();
		this.codeCount = IntStream.iterate(1, i -> i).limit(length).toArray();
	}

	public int solve(int iterNum) {
		RandomInitialiation(popNum, length, codes, codeNum, codeCount, props);

		int iter = 0;
		while (iter++ < iterNum) {
			for (int i=0; i<length; i++)
				fitness[i] = computeFitness(props[i], length, matrix);

			double p = random.nextDouble();
			if (p < pc) for (int i=0; i<popNum/2; i++){
				int[] selected = new int[2];
				selected[0] = roundBet(popNum, length, props, fitness);
				selected[1] = roundBet(popNum, length, props, fitness);
				if (selected[0] == selected[1]) continue;
				cross(selected[0], selected[1]);
			}

			p = random.nextDouble();
			if (p < pm) disturbance(props, popNum, length, random.nextInt(length-1)+1);
		}

		int maxIdx = 0;
		for (int i=0; i<fitness.length; i++) {
			if (fitness[i] < fitness[maxIdx]) maxIdx = i;
		}

		return getDistance(props[maxIdx]);
	}

	private int getDistance(int[] xs) {
		int ans = matrix[xs[xs.length-1]-1][xs[0]-1];
		for (int i=1; i<xs.length; i++)
			ans += matrix[xs[i-1]-1][xs[i]-1];
		return ans;
	}

	private void cross(int x, int y) {
		int cutPoint = random.nextInt(length);
		for (int i=0; i<cutPoint; i++)
			swap(props[x], props[y], i);
		maintain(props[x]);
		maintain(props[y]);
	}

	private void maintain(int[] xs) {
		int[] cnt = new int[xs.length];
		for (int i=0; i<length; i++){
			int pos = xs[i]-1;
			while (cnt[pos] == codeCount[pos])
				pos = random.nextInt(codeNum);
			cnt[pos]++;
			xs[i] = codes[pos];
		}
	}

	private void swap(int[] xs, int[] ys, int i) {
		int tmp = xs[i];
		xs[i] = ys[i];
		ys[i] = tmp;
	}

	/**
	 * 随机产生初始解，思路：先产生，后修复（也可以边产生边修复，如产生的位置的代码计数过多，则重新随机产生）.
	 * 
	 * @param popNum 种群大小
	 * @param length  每一个个体长度.
	 * @param iniPop  产生的初始种群.
	 * @param codes   编码序列.
	 * @param codeNum   编码的数量.
	 * @param codeCount  每一个编码的计数.
	 */
	// PASS
	public void RandomInitialiation(int popNum, int length, int[] codes, int codeNum, int[] codeCount, int[][] iniPop) {
		int[] nJs = new int[codeNum];//统计每个编码产生的数量
		Random random = new Random();

		for (int i=0; i<popNum; i++) {
			Arrays.fill(nJs, 0);
			int[] pop = new int[length];
			for (int j=0; j<length; j++) {
				int randPos = random.nextInt(codeNum);
				while (nJs[randPos] == codeCount[randPos])
					randPos = random.nextInt(codeNum);
				nJs[randPos]++;
				pop[j]=codes[randPos];
			}
			iniPop[i] = pop;
		}
	}
	
	/**
	 * 
	 * @param pop 个体       
	 * @param length  个体长度.
	 * @param a 邻接矩阵
	 */
	// PASS
	public static double computeFitness(int[] pop, int length, int[][] a) {
	    double fitness = a[pop[length-1]-1][pop[0]-1];
	    for (int i=1; i<length; i++)
	    	fitness += a[pop[i-1]-1][pop[i]-1];
		return 1/fitness;
	}
	
	/**
	 * 
	 * @param popNum 个体 个数      
	 * @param length  个体长度.
	 * @param iniPop  种群
	 * @param fitness 每一个个体的适应度
	 */
	public static int roundBet(int popNum, int length, int[][] iniPop, double[] fitness) {
		double rand = random.nextDouble();
		for (int i=0; i<fitness.length; i++) {
			if (rand < fitness[i]) return i;
			rand -= fitness[i];
		}
		return fitness.length - 1;
	}
	

	/**
	 * 
	 * @param iniPop  种群
	 * @param popNum 个体 个数      
	 * @param length  个体长度.
	 * @param disPos  随机交换的位置
	 */
	public static void disturbance(int[][] iniPop, int popNum, int length, int disPos) {
	    for (int i=0; i<popNum; i++){
	    	int[] xs = iniPop[i];

			int nextPos = random.nextInt(length);
			int tmp = xs[disPos-1];
			xs[disPos-1] = xs[nextPos];
			xs[nextPos] = tmp;
		}
	}
	
	/**
	 * 获取code在codes中的位置
	 * @param code  编码
	 * @param codeNum 总编码数 
	 * @param codes  编码矩阵.
	 */
	public static int getCodePos(int code, int codeNum, int[] codes)
	{
		int pos = 0;
		for(; pos < codeNum; pos++)
		{
			if(code == codes[pos])
			{
				return pos;
			}
		}
		return -1;
	}
}
