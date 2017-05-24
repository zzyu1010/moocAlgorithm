package edu.tyut.adt.search;
/**
 * @title SeparateChaining.java
 * @description 分离链接法是解决冲突的另一种方法，其做法是将所有关键字为同义词的数据对象通过结点
 * 链接存储在同一个链表中。
 * @time 2017年5月24日下午8:01:52
 * @author <li>ZZY</li><li>E-mail: zzyu1010@163.com</li>
 * @version 0.0.1 
 */
 
public class SeparateChaining {
	
	private class Node {
		int element;
		Node next;
		
		public Node() {}
		public Node(int element) {
			this.element = element;
		}
	}
	
	private class HashTable {
		int size;
		Node[] lists;
	}
	
	private HashTable hashTable;
	
	public SeparateChaining(int tableSize) {
		initializeTable(tableSize);
	}
	
	private void initializeTable(int tableSize) {
		hashTable = new HashTable();
		// 确定真正的散列表长度
		hashTable.size = getNextPrime(tableSize);
		hashTable.lists = new Node[hashTable.size];
		
		for (int i = 0; i < hashTable.size; i++) {
			Node root = new Node();
			hashTable.lists[i] = root; // 添加头结点，头结点的element用来存储该单向链表的结点数
		}
	}
	
	/**
	 * 查找关键字
	 * @param key
	 * @return 若找到了关键字，函数直接返回结点的所在的node，若找不到则返回null。
	 */
	private Node find(int key) {
		int pos = hash(key); // 计算地址，获得关键字所在的lists数组中的下标
		Node root = hashTable.lists[pos]; // 空白头结点
		Node node = root.next; // 真正第一元素所在的结点
		for (; node != null && node.element != key; node = node.next);
		return node;
	}
	
	/**
	 * 插入数据。插入插入时，新元素插入到表头，不仅为了操作方便，而且还因为
	 * 新近插入的元素最有可能最先被访问，这样可以加快在单向链表中的顺序查找
	 * 速度。
	 * @param key
	 */
	public void insert(int key) {
		Node node = find(key);
		if (node != null) { // 关键字找到了，不做插入处理
			return ;
		} else { // 关键字为找到
			int pos = hash(key);
			Node root = hashTable.lists[pos];
			Node newNode = new Node(key);
			newNode.next = root.next; // 插入第一个结点
			root.next = newNode;
			root.element ++;	// 头结点的element表示该链表的长度，增加1
		}
	}
	
	/**
	 * 删除结点
	 * @param key
	 */
	public void delete(int key) {
		int pos = hash(key);
		Node root = hashTable.lists[pos];
		Node pre = root;
		Node node = root.next;
		while (node != null) {
			if (node.element == key) {
				break;
			}
			pre = node;
			node = node.next;
		}
		if (node != null) { // 找到了关键字所在的结点
			// 删除结点
			pre.next = node.next;
			node.next = null;
			root.element--; // 更新链表长度
		}
		
		
	}
	
	public String print() {
		int i = 0;
		StringBuilder sb = new StringBuilder();
		 
		for (Node node : hashTable.lists) {
			sb.append(i);
			while (node != null) {
				if (node.element == 0) {
					sb.append("[").append(" ").append("]").append("——>");
				} else {
					sb.append("[").append(node.element).append("]").append("——>");
				}
				node = node.next;
			}
			sb = sb.replace(sb.length()-3, sb.length(), "");
			sb.append("\n");
			i++;
		}
		return sb.toString();
	}
	
	/**
	 * 清空除了头结点意外的链表结点
	 */
	public void clear() {
		for (Node node : hashTable.lists) {
			node.element = 0;
			node.next = null;
		}
	}
	
	/**
	 * 计算地址
	 * @param key 给定关键字
	 * @return
	 */
	private int hash(int key) {
		return key % hashTable.size;
	}
	
	/**
	 * 找出给定数值相邻的素数，且满足4k+3形式
	 * @param number
	 * @return
	 */
	private int getNextPrime(int number) {
		int candidate = number;
		boolean flag = false; // 为false 表示找到素数
		while (true) {
			flag = false;
			candidate++;
			for (int i = 2; i <= Math.sqrt(candidate); i++) {
				if (candidate % i == 0) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				if ((candidate-3)%4 == 0) {
					break;
				}
			}
		}
		return candidate;
	}
	
}
