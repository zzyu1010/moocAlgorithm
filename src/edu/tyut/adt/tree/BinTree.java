package edu.tyut.adt.tree;

import java.util.ArrayDeque;

/**
 * @title BinTree.java
 * @description 层序生成普通二叉树，然后先序、中序及后序遍历二叉树，有递归和非递归两种实现方式， 最后实现查找、删除结点。
 * @time 2017年5月1日下午7:54:36
 * @author
 * 		<li>ZZY</li>
 *         <li>E-mail: zzyu1010@163.com</li>
 * @version 0.0.1
 */
public class BinTree<E> {
	private class Node {
		private E e;
		private Node left;
		private Node right;

		public Node(E element) {
			this.e = element;
		}
	}

	private Node root;

	/**
	 * 根据输入数组，层序生成二叉树
	 * 
	 * @param elements
	 */
	public void createBinTree(E[] elements) {
		if (elements == null) {
			return;
		}
		ArrayDeque<Node> queue = new ArrayDeque<>();
		if (elements[0] == null) {// 若第一个数据为null，返回null
			return;
		} else {
			root = new Node(elements[0]);
			queue.add(root);
		}
		int i = 0;
		while (!queue.isEmpty() && i < elements.length - 2) { // 数组大小为2i+3
			Node parent = queue.poll();
			if (parent != null) {
				if (2 * i + 1 < elements.length) {// 添加左子树
					parent.left = new Node(elements[2 * i + 1]);
					queue.add(parent.left);
				} else {
					break;
				}
				if (2 * i + 2 < elements.length) {// 添加右子树
					parent.right = new Node(elements[2 * i + 2]);
					queue.add(parent.right);
				} else {
					break;
				}
			}
			i++;
		}
	}
	/**
	 * 删除结点及其子结点
	 * @param o
	 * @return
	 */
	public boolean delete(Object o) {
		if (o == null) {
			return false;
		}
		 // 删除根结点
		if (this.root == null) {
			return false;
		} else {
			if (o.equals(root.e)) {
				this.root = null;
				return true;
			}
		}
		return deleteNode(this.root,this.root,false, o);
	}
	
	private  boolean deleteNode(Node parent, Node node, boolean flag, Object o) {
		boolean result = false;
		boolean lResult = false;
		boolean rResult = false;
		if (node != null) {
			if (o.equals(node.e)) {
				if (flag == true) {
					parent.left = null;
				} else {
					parent.right = null;
				}
				node = parent;
				return true;
			} else {
				parent = node;
			}
			lResult = deleteNode(parent, node.left,true,o);
			rResult = deleteNode(parent, node.right,false, o);
		}
		if (lResult != false) {
			result = lResult;
		}
		if (rResult != false) {
			result = rResult;
		}
		return result;
	}

	/**
	 * 中序遍历---递归实现
	 */
	public void inOrderTraversal() {
		inOrderTraversalNode(this.root);
		System.out.println();
	}
	
	/**
	 * 中序遍历---非递归实现
	 */
	public void midOrderTraversal() {
		Node node = root;
		ArrayDeque<Node> stack = new ArrayDeque<>();
		while (node != null || !stack.isEmpty()) {
			while (node != null) {
				stack.push(node);
				node = node.left;
			}
			node = stack.pop();
			if (node.e != null)
				System.out.print(node.e);
			node = node.right;
		}
		System.out.println();
	}

	private void inOrderTraversalNode(Node node) {
		if (node != null) {
			inOrderTraversalNode(node.left);
			if (node.e != null) {
				System.out.print(node.e);
			}
			inOrderTraversalNode(node.right);
		}
	}

	/**
	 * 先序遍历---递归实现
	 */
	public void preOrderTraversal() {
		preOrderTraversalNode(this.root);
		System.out.println();
	}
	/**
	 * 先序遍历---非递归实现
	 */
	public void previousOrderTraversal() {
		Node node = root;
		ArrayDeque<Node> stack = new ArrayDeque<Node>();
		while (node != null || !stack.isEmpty()) {
			while (node != null) {
				if (node.e != null)
					System.out.print(node.e);
				stack.push(node);
				node = node.left;
			}
			node = stack.pop();
			// 转向右子树
			node = node.right;
		}
		System.out.println();
	}
	
	private void preOrderTraversalNode(Node node) {
		if (node != null) {
			if (node.e != null) {
				System.out.print(node.e);
			}
			preOrderTraversalNode(node.left);
			preOrderTraversalNode(node.right);
		}
	}

	/**
	 * 后序遍历---递归实现
	 */
	public void postOrderTraversal() {
		postOrderTraversalNode(this.root);
		System.out.println();
	}
	private void postOrderTraversalNode(Node node) {
		if (node != null) {
			postOrderTraversalNode(node.left);
			postOrderTraversalNode(node.right);
			if (node.e != null) {
				System.out.print(node.e);
			}
		}
	}
	/**
	 * 后序遍历---非递归实现
	 */
	public void postTraversal() {
		Node node = root;
		Node pre = root; //记录已经访问过的结点
		ArrayDeque<Node> stack = new ArrayDeque<>();
		while (node != null || !stack.isEmpty()) {
			while (node != null) { // 一直向左并将沿途结点压入堆栈
				stack.push(node);
				node = node.left;
			}
			// 取出当前结点
			node = stack.peek();
			if (node.right == null || node.right.equals(pre)) {// 没有右结点或右结点已经出栈时，访问当前结点
				if (node.e != null)
					System.out.print(node.e);
				stack.pop();	
				pre = node;	 // 记录上一出栈的结点
				node = null; // 防止此结点重新入栈
			} else {
				// 转向右子树
				node = node.right;
			}
			
		}
		
	}
	
	/**
	 * 层序遍历
	 */
	public void levelTraversal() {
		Node node = root;
		ArrayDeque<Node> queue = new ArrayDeque<>();
		queue.add(node);
		while (!queue.isEmpty()) {
			node = queue.poll();
			if (node.e != null)
				System.out.print(node.e); // 输出结点
			if (node.left != null)
				queue.add(node.left); // 添加左结点
			if (node.right != null)
				queue.add(node.right); // 添加右结点
		}
	}
	
	
	
	public boolean contains(Object o) {
		 if (o != null) {
			 return containNode(this.root, o) == null ? false : true;
		 }
		 return false;
	}

	private Node containNode(Node node, Object o) {
		Node lResult = null;
		Node rResult = null;
		Node result = null;
		if (node != null) {
			if (o.equals(node.e)) {
				System.out.println(node.e);
				return node;
			} 
			lResult = containNode(node.left, o);
			rResult = containNode(node.right, o);
		}
		if (lResult != null) {
			result = lResult;
		}
		if (rResult != null) {
			result = rResult;
		}
		return result;
	}

	public boolean isEmpty() {
		return root == null ? true : false;
	}
}
