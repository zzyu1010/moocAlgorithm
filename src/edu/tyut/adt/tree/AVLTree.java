package edu.tyut.adt.tree;
/**
 * @title AVLTree.java
 * @description 二叉平衡树
 * @time 2017年5月21日上午9:42:59
 * @author <li>ZZY</li><li>E-mail: zzyu1010@163.com</li>
 * @version 0.0.1 
 */
public class AVLTree<T extends Comparable<T>> {
	private class Node {
		T  data;
		Node left;
		Node right;
		int height;
		
		public Node(T data, Node left, Node right, int height) {
			this.data = data;
			this.left = left;
			this.right = right;
			this.height = height;
		}
		public Node(T data) {
			this(data, null, null, 0);
		}
	}
	
	
	private Node root;
	
	/**
	 * 将elementData 插入AVL树中，并调整AVL树。若树中已存在此数，则不插入。
	 * 循环实现
	 * @param elementData
	 */
	public void insertion(T elementData) {
		if (elementData == null) {
			return ;
		}
		if (isEmpty()) {
			root = new Node(elementData);
			return ;
		} 
		
		root = insertionNode(root, elementData);
	}
	
	private Node insertionNode(Node node, T elementData) {
		if (node == null) {
			node = new Node(elementData);
		}
		if (elementData.compareTo(node.data) < 0) { // 插入左子树
			node.left = insertionNode(node.left, elementData);
			
			if (getHeight(node.left) - getHeight(node.right) == 2) {// 需要左旋
				if (elementData.compareTo(node.left.data) < 0) {
					node = singleLeftRotation(node);	// 左单旋
				} else {
					node = doubleLeftRightRotation(node); // 左-右双旋
				}
			}
			
		} else if (elementData.compareTo(node.data) > 0) { // 插入右子树
			node.right = insertionNode(node.right, elementData);
			
			if (getHeight(node.left) - getHeight(node.right)  == -2) { // 需要右旋
				if (elementData.compareTo(node.right.data) > 0) {
					node = singleRightRotation(node); // 右单旋
				} else {
					node = doubleRightLeftRotation(node); // 右-左双旋
				}
			}
		}
		
		node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1; // 更新树高
		
		return node;
	
	}
	
	/**
	 * 右-左双旋
	 * @param node
	 * @return
	 */
	private AVLTree<T>.Node doubleRightLeftRotation(AVLTree<T>.Node node) {
		node.right = singleLeftRotation(node.right);
		return singleRightRotation(node);
	}

	/**
	 * 左右双旋--相当于以B为根节点的子树做了一次有单旋，再对以A为根结点的子树做了一次左单旋，是两次单旋的合成结果
	 * @param node(A)必须先有一个左子结点B，且B必须有一个右子结点C
	 * @return
	 */
	private AVLTree<T>.Node doubleLeftRightRotation(AVLTree<T>.Node node) {
		node.left = singleRightRotation(node.left);
		return singleLeftRotation(node);
	}

	/**
	 * 左单旋实现方法
	 * @param node 必须有一个左子结点
	 * @return
	 */
	private Node singleLeftRotation(Node a) { 
		Node b = a.left;
		a.left = b.right;
		b.right = a;
		a.height = Math.max(getHeight(a.left), getHeight(a.right)) + 1;
		b.height = Math.max(getHeight(b.left), a.height) + 1;
		return b;
	}
	
	/**
	 * 
	 * @param 发现问题结点a，插入结点c(产生结点)以及他们之间路径上的结点b三个结点 向右排成一线
	 * @return
	 */
	private Node singleRightRotation(Node node) {
		Node newNode = node.right;
		node.right = newNode.left;
		newNode.left = node;
		node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
		newNode.height = Math.max(getHeight(newNode.left), node.height) + 1;
		return newNode;
	}
	
	/**
	 * 测试类，测试求树高方法
	 * @return
	 */
	public int getHeight(T elementData) {
		
		return getHeight(root );
	}
	
	private int getHeight(Node node) {
		int hl, hr, maxh;
		if (node != null) {
			hl = getHeight(node.left );
			hr = getHeight(node.right);
			maxh = hl > hr ? hl : hr;
			return maxh + 1;
		}  else {
			return 0; // 空树为0
		}
	}
	
	
	public boolean isEmpty() {
		return root == null ? true : false;
	}
	/**
	 * 中序遍历
	 * @param node
	 */
	public void inOrderTraverse() {
		if (isEmpty()) {
			return ;
		}
		inOrderTraverseNode(root);
	}
	private void inOrderTraverseNode(Node node) {
		if (node != null) {
			inOrderTraverseNode(node.left);
			System.out.print(node.data + " ");
			inOrderTraverseNode(node.right);
		}
	}
	/**
	 * 前序遍历
	 */
	public void preOrderTraverse() {
		if (isEmpty()) {
			return ;
		}
		preOrederTraverseNode(root);
	}
	private void preOrederTraverseNode(Node node) {
		if (node != null) {
			System.out.print(node.data + " ");
			preOrederTraverseNode(node.left);
			preOrederTraverseNode(node.right);
		}
	}
	
}
