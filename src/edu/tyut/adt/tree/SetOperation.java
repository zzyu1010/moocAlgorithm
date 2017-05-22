package edu.tyut.adt.tree;

/**
 * @title SetOperation.java
 * @description 集合是一种常用的数据表示方法。集合运算包括交、并、补、差以及判定一个数据是否是某
 * 一个集合中的元素等。
 * @time 2017年5月22日下午2:15:27
 * @author <li>ZZY</li><li>E-mail: zzyu1010@163.com</li>
 * @version 0.0.1 
 */
public class SetOperation {
	public class SetNode  {
		private int data;
		private int parent;
		
		public SetNode(int data, int parent) {
			this.data = data;
			this.parent = parent;
		}
		public SetNode() {}
	}
	
	private static final int DEFAULT_CAPACTIY = 12;
	private int capacity;
	private int size;
	private SetNode[] sets;
	
	public SetOperation() {
		sets = new SetNode[DEFAULT_CAPACTIY];
		capacity = DEFAULT_CAPACTIY;
	}
	
	public SetOperation(int capacity) {
		sets = new SetNode[capacity];
		this.capacity = capacity;
	}
	
	
	/**
	 * 添加数据，data域存储结点本身的数据，一般元素pParent域指向父结点的指针，
	 * 即存储父结点在数组中的下标；而根结点的parent域存放-1以区别其他节点，它
	 * 代表了一棵树所表示的集合
	 * 与我们之前见到的父子关系指针不同，这里节点的指针不是父节点指向子结点，而是由
	 * 子结点指向父结点。每个根结点与其集合名称相关联。选用哪个结点作为代表此集合的父结点
	 * 是无关紧要的。
	 * @param data
	 * @param parent
	 */
	public void add(int data, int parent) {
		if (isFull()) {
			return ;
		}
		SetNode set = new SetNode(data, parent);
		sets[size++] = set;
	}
	
	/**
	 * 查找某个元素所在的集合
	 * @param element
	 * @return 找到元素所在的集合，返回根结点在数组sets中的下标
	 */
	public int find(int element) {
		// 确定元素element是否存在
		int i = 0;
		for (; i < size && sets[i].data != element; i++);
		if (i >= size)
			return -1; // 未找到element，返回-1
		// 找到element元素，返回根结点
		for (; sets[i].parent >= 0; i = sets[i].parent);
		return i;
	}
	
	/**
	 * 将data1和data2所属的两个集合合并操作。通过先找到两个元素所在集合树的根结点，如果他们不同根，
	 * 则将其中一个根结点的父结点域值设置成另一个根结点的数组下标即可。
	 * @param data1
	 * @param data2
	 */
	public void union(int data1, int data2) {
		int root1 = find(data1);
		int root2 = find(data2);
		if (root1 != root2) {
			sets[root2].parent = root1;
		}
	}
	
	/**
	 * 测试类
	 */
	public void print() {
		System.out.println("下标  " + "data " + "parent");
		for (int i = 0; i < size; i++) {
			System.out.println(i +" " + sets[i].data + " " + sets[i].parent);
		}
	}
	
	public boolean isEmpty() {
		return size == 0 ? true : false;
	}
	public boolean isFull() {
		return size == capacity ? true :false;
	}
}
