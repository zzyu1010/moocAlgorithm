package edu.tyut.adt.tree;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @title BSTree.java
 * @description 二叉搜索树 :
 * <li>非空左子树的所有键值小于其根结点的键值</li>
 * <li>非空右子树的所有键值大于其根节点的键值</li>
 * <li>左、右子树都是二叉搜索树</li>
 * @time 2017年5月5日下午9:02:09
 * @author
 * 		   <li>ZZY</li>
 *         <li>E-mail: zzyu1010@163.com</li>
 * @version 0.0.1
 */
public class BSTree<E extends Comparable<E>> {
	public class Node {
		private E data;
		private Node left;
		private Node right;

		public Node(E data) {
			this.data = data;
		}
	}

	private Node root;

	/**
	 * 二叉搜索树创建--迭代实现
	 * 
	 * @param e
	 */
	public void add(E e) {
		Node newNode = new Node(e);
		if (root == null) {
			root = newNode;
			return;
		}
		Node node = root;
		Node parent = root; // 记录当前结点的父节点
		boolean flag = false; // flag为false，表示要插入左子树，true表示要插入右子树
		while (node != null) { // 找到要插入的当前结点及其父节点
			if (e.compareTo(node.data) < 0) { // 遍历左子树
				parent = node;
				flag = false;
				node = node.left;
			} else if (e.compareTo(node.data) > 0) { // 遍历右子树
				parent = node;
				flag = true;
				node = node.right;
			} else {
				return; // 添加的数据已存在，什么都不做
			}
		}
		if (flag) {
			parent.right = newNode;
		} else {
			parent.left = newNode;
		}
	}

	/**
	 * 建立二叉搜索树，递归实现
	 * 
	 * @param e
	 */
	public void addRecursive(E e) {
		if (root == null) { // 建立二叉搜索树
			root = new Node(e);
		} else {
			// addNode(e, root);
			addNode2(e, root, root, false);
		}
	}

	@SuppressWarnings("unused")
	private Node addNode(E e, Node node) {
		if (node == null) {
			node = new Node(e); // 建立要插入的新结点
			return node;
		}
		if (e.compareTo(node.data) < 0) {
			node.left = addNode(e, node.left); // 递归插入左子树
		} else if (e.compareTo(node.data) > 0) {
			node.right = addNode(e, node.right); // 递归插入右子树
		} /*
			 * else { // 当出现相同的数据，此部分加上出现错误 return null; // 要插入的数据已存在，什么都不做 }
			 */
		return node; // why? 返回node（父节点）
	}

	/**
	 * 
	 * @param e
	 *            要插入的数据
	 * @param parent
	 *            当前插入结点的父结点
	 * @param node
	 *            遍历当前结点
	 * @param flag
	 *            true 表示插入左子树，false 表示插入右子树
	 */
	private void addNode2(E e, Node parent, Node node, boolean flag) {
		if (node == null) {
			node = new Node(e); // 建立要插入的新结点
			if (flag) {
				parent.left = node;
			} else {
				parent.right = node;
			}
		}
		if (e.compareTo(node.data) < 0) {
			flag = true;
			addNode2(e, node, node.left, flag); // 递归插入左子树
		} else if (e.compareTo(node.data) > 0) {
			flag = false;
			addNode2(e, node, node.right, flag); // 递归插入右子树
		}
	}

	/**
	 * 查找当前二叉搜索树的最小结点值--迭代实现
	 * 
	 * @return
	 */
	/*
	 * public E getMin() { Node node = root; Node parent = node; while (node !=
	 * null) { parent = node; node = node.left; } return parent.data; }
	 */
	public E getMin() {
		if (isEmpty()) {
			return null;
		}
		Node node = root;
		while (node.left != null) {
			node = node.left;
		}
		return node.data;
	}

	/**
	 * 递归实现获取最小值
	 * 
	 * @return
	 */
	public E getMinData() {
		if (isEmpty()) {
			return null;
		}
		return getMinNode(root).data;
	}

	private Node getMinNode(Node node) {
		if (node.left == null) {
			return node;
		} else {
			return getMinNode(node.left);
		}
	}

	/**
	 * 查找当前二叉搜索树的最大结点值--迭代实现
	 * 
	 * @return
	 */
	/*
	 * public E getMax() { Node node = root; Node parent = node; while (node !=
	 * null) { parent = node; node = node.right; } return parent.data; }
	 */
	public E getMax() {
		if (isEmpty()) {
			return null;
		}
		Node node = root;
		while (node.right != null) { // 遍历右子树
			node = node.right;
		}
		return node.data;
	}

	/**
	 * 递归实现--获取最大值
	 * 
	 * @return
	 */
	public E getMaxData() {
		if (isEmpty()) {
			return null;
		}
		return getMaxNode(root).data;
	}

	private Node getMaxNode(Node node) {
		if (node.right == null) {
			return node;
		} else {
			return getMaxNode(node.right);
		}
	}
	
	/**
	 * 删除删除二叉搜索树e结点，非递归实现
	 * @param e
	 */
	public void delete(E e) {
		if (isEmpty() || e == null) {
			return;
		}

		Node parent = null;
		Node node = null;
		boolean flag = true; // 默认左子树
		Map<Node, Boolean> map = null;
		Set<Node> set = null;
		Iterator<Node> iter;
		if (e.compareTo(root.data) == 0) { // 处理根结点的情况
			deleteRoot();
			return ;
		} else {
			map = getParent(e); // 获取其父结点
			set = map.keySet();
			iter = set.iterator();
			while (iter.hasNext()) {
				parent = iter.next();
				flag = map.get(parent);
			}
			if (parent == null) {
				return;
			}
			node = flag ? parent.left : parent.right; // 取出当前要删除的结点
		}
		 
		if (node.left != null && node.right != null) { // 当前结点存在左右孩子结点
			deleteDoubleNode(node);
		} else if (node.left != null) { // 当前结点存在左孩子结点
			deleteNode(parent, node.left, flag);
		} else if (node.right != null) { // 当前结点存在右孩子结点
			deleteNode(parent, node.right, flag);
		} else { // 当前结点左右孩子结点都不存在
			deleteNode(parent, null, flag);
		}
		node = null;
	}
	
	/**
	 * 处理根结点的情况
	 */
	private void deleteRoot() {
			Node node = root; // 当前要删除的结点为根结点
			if (node.left != null && node.right == null) { //根结点只有左子树
				root = node.left;
				return ;
			} else if (node.right != null && node.left == null) { // 根结点只有右子树
				root = node.right;
				return ;
			} else if (node.left == null && node.right == null) { // 根结点没有左右孩子
				root = null;
			} else { // 根结点具有左右孩子
				deleteDoubleNode(root);
			}
	}
	/**
	 * 要删除的结点具有左右孩子
	 * @param parent 当前结点的父结点
	 * @param node 要删除的当前结点
	 */
	private void deleteDoubleNode(Node node) {
		/**
		 *要删除结点的位置可以由其左子树的最大结点或其右子树的最小结点代替
		 */
		if (node.left != null && node.right != null) { // 当前结点存在左右孩子结点
			Node maxNode = getMaxNode(node.left); // 找到以node为根节点的左子树，取出其左子树最大值
			// 找到最大值结点的父结点，然后删除最大值结点
			Map<Node,Boolean>map = getParent(maxNode.data);
			Set<Node> set = map.keySet();
			Iterator<Node> iter = set.iterator();
			boolean flag = true;
			Node parent = null;
			while (iter.hasNext()) {
				parent = iter.next();
				flag = map.get(parent);
			}
			 // 将最大值赋予要删除的结点数据
			node.data = maxNode.data;
			// 最大值结点最多只有一个左孩子或没有孩子结点
			if (maxNode.left != null) { // 只有左孩子
				deleteNode(parent, maxNode.left, flag);
			} else { // 没有孩子结点
				deleteNode(parent, null, flag);
			}
		}
	}
	/**
	 * 实现删除没有孩子结点或只有一个孩子结点
	 * 
	 * @param parent
	 *            当前要删除结点的父结点
	 * @param child
	 *            当前要删除的结点
	 * @param flag
	 *            为true表示当前child结点为parent结点的左孩子
	 */
	private void deleteNode(Node parent, Node child, boolean flag) {
		if (flag) {
			parent.left = child;
		} else {
			parent.right = child;
		}
	}

	/**
	 * 找到给定数值结点的父节点，以及其所在父节点左右方向，以map集合返回，node表示其父结点，flag为true 表示其是左孩子。
	 * 本函数不能处理二叉搜索树的根节点
	 * 
	 * @param e
	 * @return
	 */
	private Map<Node, Boolean> getParent(E e) {
		if (e == null) {
			return null;
		}
		Map<Node, Boolean> map = new HashMap<>();
		boolean flag = false; // flag = false,表示要删除的结点是parent结点的右孩子
		Node node = root;
		Node parent = node;
		while (node != null) {
			if (e.compareTo(node.data) < 0) { // 遍历左子树
				flag = true;
				parent = node;
				node = node.left;
			} else if (e.compareTo(node.data) > 0) { // 遍历右子树
				flag = false;
				parent = node;
				node = node.right;
			} else if (e.compareTo(node.data) == 0) { // 找到结点
				map.put(parent, flag);
				break;
			}
		}
		return map;
	}

	public void postOrderTraverl() {
		Node node = root;
		ArrayDeque<Node> stack = new ArrayDeque<>();
		Node pre = node; // 记录上一出栈的结点
		while (node != null || !stack.isEmpty()) {
			while (node != null) {
				stack.push(node);
				node = node.left;
			}
			node = stack.peek();
			if (node.right == null || node.right.equals(pre)) {
				stack.pop();
				pre = node;
				node = null;
			} else {
				node = node.right;
			}
		}
	}

	/**
	 * 销毁二叉搜索树
	 */
	public void clear() {
		if (isEmpty()) {
			return;
		}
		freeNode(root);
		root = null;
	}

	private void freeNode(Node node) {
		if (node != null) {
			freeNode(node.left);
			freeNode(node.right);
		}
		node = null;
	}

	/**
	 * 中序遍历--可以实现二叉搜索树，从小到大的顺序排序
	 */
	public void inOrderTraversal() {
		ArrayDeque<Node> stack = new ArrayDeque<>();
		Node node = root;
		while (node != null || !stack.isEmpty()) {
			while (node != null) {
				stack.push(node);
				node = node.left;
			}
			node = stack.pop();
			if (node.data != null) {
				System.out.print(node.data + " ");
			}
			node = node.right;
		}
		System.out.println();
	}

	public boolean isEmpty() {
		return root == null ? true : false;
	}
}
