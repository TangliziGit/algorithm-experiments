package Experiment3;
import java.util.Random;

public class GAOperations {
	/**
	 * ���������ʼ�⣬˼·���Ȳ��������޸���Ҳ���Ա߲������޸����������λ�õĴ���������࣬���������������.
	 * 
	 * @param popNum ��Ⱥ��С       
	 * @param length  ÿһ�����峤��.
	 * @param iniPop  �����ĳ�ʼ��Ⱥ.
	 * @param codes   ��������.
	 * @param codeNum   ���������.
	 * @param codeCount  ÿһ������ļ���.
	 */
	public void RandomInitialiation(int popNum, int length, int[] codes, int codeNum, int[] codeCount, int[][] iniPop) {
		int i, j;
		int[] nJs = new int[codeNum];//ͳ��ÿ���������������
		Random random = new Random();
		//TODO
		//����������룬��ȥ�أ��޸�
		
	}
	
	/**
	 * 
	 * @param pop ����       
	 * @param length  ���峤��.
	 * @param a �ڽӾ���
	 */
	public static double computeFitness(int[] pop, int length, int[][] a)
	{
		//���������Ӧ��
		//TODO
		
		return 0.0;
	}
	
	/**
	 * 
	 * @param popNum ���� ����      
	 * @param length  ���峤��.
	 * @param iniPop1  ��Ⱥ
	 * @param fitness ÿһ���������Ӧ��
	 */
	public static void roundBet(int popNum, int length, int[][] iniPop1, double[] fitness)
	{
		//¥�̶�
	}
	

	/**
	 * 
	 * @param iniPop  ��Ⱥ
	 * @param popNum ���� ����      
	 * @param length  ���峤��.
	 * @param disPos  ���������λ����
	 */
	public static void Disturbance(int [][] iniPop, int popNum, int length, int disPos)
	{
		//�Ŷ�
	}
	
	/**
	 * ��ȡcode��codes�е�λ��
	 * @param code  ����
	 * @param codeNum �ܱ����� 
	 * @param codes  �������.
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
