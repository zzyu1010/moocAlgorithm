package edu.tyut.adt.map;
/**
 * @title AdjacencyMatrix2.java
 * @description TODO
 * @time 2017年7月22日上午10:27:57
 * @author <li>ZZY</li><li>E-mail: zzyu1010@163.com</li>
 * @version 0.0.1 
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 图：
 * 					  A 
 * 					/   \ 
 * 					B 	 D 
 * 				   / \  / \ 
 * 				   C F G - H 
 * 				   \ / 
 * 					E
 * 
 * 邻接矩阵：
 * 	 	A	B	C	D	E	F	G	H 
 * A 		1		1
 * B	1		1			1
 * C		1		 	1
 * D	1						1	1
 * E			1			1
 * F		1			1
 * G				1				1
 * H				1			1
 * 
 * 
 * 
 * 
 * 深度优先遍历： ABCEFDGH
 * 
 * 广度优先遍历： ABDCFGHE
 * 
 * @author ZZY
 *
 */
public class AdjacencyMatrixMooc {

	public class Node {
		private char data;
		private boolean isVisted;

		public Node(char data) {
			this.data = data;
		}
		public void setIsVisted(boolean isVisted) {
			this.isVisted = isVisted;
		}
	}

	private int capacity; // 图中最多可以容纳的顶点数
	private int nodeCount; // 已经添加的顶点个数
	private Node[] nodes; // 用来存放顶点数组
	private int[] matrix; // 用来存放邻接矩阵

	public AdjacencyMatrixMooc(int capacity) {
		this.capacity = capacity;
		nodeCount = 0;
		this.nodes = new Node[capacity];
		for (int i = 0; i < nodes.length; i++) {
			nodes[i] = new Node(' ');
		}
		this.matrix = new int[capacity * capacity];
	}

	/**
	 * 向图中加入顶点（结点）
	 * 
	 * @param pNode
	 * @return
	 */
	public boolean addNode(Node node) {
		if (node == null) {
			return false;
		}
		this.nodes[this.nodeCount++].data = node.data;
		return true;
	}

	/**
	 * 重置顶点
	 */
	public void resetNode() {
		for (int i = 0; i < nodes.length; i++) {
			nodes[i].isVisted = false;
		}
	}

	/**
	 * 为有向图设置邻接矩阵
	 * 
	 * @param row
	 * @param col
	 * @param val
	 * @return
	 */
	public boolean setValueToMatrixForDirectedGraph(int row, int col, int val) {
		if (checkArrayIndex(row, col)) {
			this.matrix[row * this.capacity + col] = val;
			return true;
		}
		return false;
	}

	/**
	 * 为有向图设置邻接矩阵
	 * 
	 * @param row
	 * @param col
	 * @param val
	 *            设置val默认值为1
	 * @return
	 */
	public boolean setValueToMatrixForDirectedGraph(int row, int col) {
		if (checkArrayIndex(row, col)) {
			this.matrix[row * this.capacity + col] = 1;
			return true;
		}
		return false;
	}

	/**
	 * 为无向图设置邻接矩阵
	 * 
	 * @param row
	 * @param col
	 * @param val
	 * @return
	 */
	public boolean setValueToMatrixForUndirectedGraph(int row, int col, int val) {
		if (checkArrayIndex(row, col)) {
			this.matrix[row * this.capacity + col] = val;
			this.matrix[col * this.capacity + row] = val;
			return true;
		}
		return false;
	}

	/**
	 * 为无向图设置邻接矩阵
	 * 
	 * @param row
	 * @param col
	 * @param val
	 *            设置val默认值为1
	 * @return
	 */
	public boolean setValueToMatrixForUndirectedGraph(int row, int col) {
		if (checkArrayIndex(row, col)) {
			this.matrix[row * this.capacity + col] = 1;
			this.matrix[col * this.capacity + row] = 1;
			return true;
		}
		return false;

	}

	public void printMatrix() {
		int count = 1;
		for (int x : this.matrix) {
			System.out.print(x + " ");
			if (count++ % this.nodeCount == 0) {
				System.out.println();
			}
		}
	}

	public int getValueFromMatrix(int row, int col) {
		if (checkArrayIndex(row, col)) {
			return this.matrix[row * this.capacity + col];
		}
		return -1;
	}

	private boolean checkArrayIndex(int row, int col) {
		if ((row < 0 || row >= capacity) || (col < 0 || col >= capacity)) {
			return false;
		}
		return true;
	}

	/**
	 * 深度优先遍历
	 * 
	 * @param nodeIndex
	 */
	public void depthFirstTraverse(int nodeIndex) {
		int value = -1;
		if (!checkArrayIndex(nodeIndex, 0)) {
			return ;
		}
		System.out.print(this.nodes[nodeIndex].data);
		nodes[nodeIndex].isVisted = true;
		for (int i = 0; i < this.capacity; i++) {
			value = getValueFromMatrix(nodeIndex, i);
			if (value == 1) {
				if (nodes[i].isVisted == true) {
					continue;
				} else {
					depthFirstTraverse(i);
				}
			} else {
				continue;
			}
		}
	}

	/**
	 * 广度优先遍历
	 * 
	 * @param nodeIndex
	 */
	public void breadthFirstTraverse(int nodeIndex) {
		if (checkArrayIndex(nodeIndex, 0)) {
			List<Integer> list = new ArrayList<Integer>();
			list.add(nodeIndex);
			breadthFirstCircle(list);
		}
	}
	
	private void breadthFirstCircle(List<Integer> preList) {
		 
		List<Integer> curList = new ArrayList<>();
		int value = -1;
		int nodeIndex = -1;
		for (int i = 0; i < preList.size(); i++) {
			nodeIndex = preList.get(i);
			if (!nodes[nodeIndex].isVisted) {
				System.out.print(nodes[nodeIndex].data);
				nodes[nodeIndex].isVisted = true;
			}
			for (int j = 0; j < capacity; j++) {
				value = getValueFromMatrix(nodeIndex, j);
				if (value == 1) {
					if (!nodes[j].isVisted) {
						curList.add(j);
						System.out.print(nodes[j].data);
						nodes[j].isVisted = true;
					}
				}  
			}
			
		}
		
		if (curList.size() == 0) {
			return ;
		} else {
			breadthFirstCircle(curList);
		}
	}
}

		 
 
