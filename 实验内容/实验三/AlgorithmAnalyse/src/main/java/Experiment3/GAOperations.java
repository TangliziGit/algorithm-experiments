package Experiment3;
import java.util.Random;

public class GAOperations {
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
	public void RandomInitialiation(int popNum, int length, int[] codes, int codeNum, int[] codeCount, int[][] iniPop) {
		int i, j;
		int[] nJs = new int[codeNum];//统计每个编码产生的数量
		Random random = new Random();
		//TODO
		//随机产生编码，并去重，修复
		
	}
	
	/**
	 * 
	 * @param pop 个体       
	 * @param length  个体长度.
	 * @param a 邻接矩阵
	 */
	public static double computeFitness(int[] pop, int length, int[][] a)
	{
		//计算个体适应度
		//TODO
		
		return 0.0;
	}
	
	/**
	 * 
	 * @param popNum 个体 个数      
	 * @param length  个体长度.
	 * @param iniPop1  种群
	 * @param fitness 每一个个体的适应度
	 */
	public static void roundBet(int popNum, int length, int[][] iniPop1, double[] fitness)
	{
		//楼盘赌
	}
	

	/**
	 * 
	 * @param iniPop  种群
	 * @param popNum 个体 个数      
	 * @param length  个体长度.
	 * @param disPos  随机交换的位置数
	 */
	public static void Disturbance(int [][] iniPop, int popNum, int length, int disPos)
	{
		//扰动
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
