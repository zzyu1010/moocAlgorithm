package edu.tyut.adt.tree;

/**
 * @title HuffmanTree.java
 * @description 构造哈夫曼树
 * @time 2017年5月21日下午7:47:53
 * @author
 * 		<li>ZZY</li>
 *         <li>E-mail: zzyu1010@163.com</li>
 * @version 0.0.1
 */
public class HuffmanTree {
	private class Node implements Comparable<Node>{
		int weight;
		Node left;
		Node right;

		public Node(int weight, Node left, Node right) {
			this.weight = weight;
			this.left = left;
			this.right = right;
		}

		public Node(int weight) {
			this(weight, null, null);
		}
		public Node() {}

		@Override
		public int compareTo(Node o) {
			if (this.weight > o.weight) {
				return 1;
			} else if (this.weight < o.weight) {
				return -1;
			} else {
				return 0;
			}
		}
		
		@Override
		public String toString() {
			return weight + " ";
		}

	}
	
	private Node root = new Node();

	public void createHuffman(int[] weights) {
		MinHeapGenetics<HuffmanTree.Node> mh = new MinHeapGenetics<HuffmanTree.Node>();
		for (int i = 0; i < weights.length; i++) {
			mh.insert(new Node(weights[i]));
		}
		mh.buildMinHeap();
		for (int i = 1; i < weights.length; i++) { // 做weights.length-1次合并
			Node temp = new Node();
			temp.left = mh.deleteMin();
			temp.right = mh.deleteMin();
			temp.weight = temp.left.weight + temp.right.weight;
			mh.insert(temp);
		}
		root = mh.deleteMin();
	}
	
	/**
	 * 中序遍历
	 */
	public void inOrderTraverse() {
		inOrderTraversNode(root);
	}
	private void inOrderTraversNode(Node node) {
		if (node != null) {
			inOrderTraversNode(node.left);
			System.out.print(node);
			inOrderTraversNode(node.right);
		}
	}
}
