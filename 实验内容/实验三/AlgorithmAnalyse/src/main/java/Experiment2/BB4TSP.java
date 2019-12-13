package Experiment2;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Vector;

public class BB4TSP {

	int NoEdge = -1; //表示没有边
	private int minCost = Integer.MAX_VALUE; //当前最小代价
	public int getMinCost() {
		return minCost;
	}

	public void setMinCost(int minCost) {
		this.minCost = minCost;
	}

	private LinkedList<HeapNode> heap = new LinkedList<HeapNode>();//存储活节点
	private Vector<Integer> bestH = new Vector<Integer>();
	
	
	@SuppressWarnings("rawtypes")
	public static class HeapNode implements Comparable{
		Vector<Integer> liveNode;//城市排列
		int lcost; //代价的下界
		int level;//0-level的城市是已经排好的
		//构造方法
		public HeapNode(Vector<Integer> node,int lb, int lev){
			liveNode.addAll(0, node);
			lcost = lb;
			level = lev;
		}
		
		@Override
		public int compareTo(Object x) {//升序排列, 每一次pollFirst
			int xu=((HeapNode)x).lcost;
			if(lcost<xu) return -1;
			if(lcost==xu) return 0;
			return 1;
		}
		public boolean equals(Object x){
			return lcost==((HeapNode)x).lcost;
		}

	}
	
	/**
	 * 计算部分解的下界.
	 * 
	 * @param liveNode 
	 * 		              城市的排泄
	 *  
	 * @param n   
	 * 			   当前确定的城市的个数.
	 * @param cMatrix
	 *            邻接矩阵，第0行，0列不算
	 * 
	 * @exception IllegalArgumentException
	 */
	public int computeLB(Vector<Integer> liveNode, int level, int[][] cMatrix)
	{
		//TODO
		return -1;
	}
	
	/**
	 * 计算TSP问题的最小代价的路径.
	 * 
	 * @param cMatrix
	 *            邻接矩阵，第0行，0列不算
	 * @param n   城市个数.
	 * @exception IllegalArgumentException
	 */
	public int bb4TSP(int[][] cMatrix, int n)
	{
		//构造初始节点
		Vector<Integer> liveNode = new Vector<Integer>() ;//城市排列
		for(int i = 1; i<=n; i++) liveNode.add(i);
		int level = 1;//0-level的城市是已经排好的
		int lcost = computeLB(liveNode, level, cMatrix) ; //代价的下界
		while(level != n)
		{
			//TODO
			//参考优先队列，不停扩展节点,选取下一个节点
		}
		
		return minCost;
	}
	
	
}
