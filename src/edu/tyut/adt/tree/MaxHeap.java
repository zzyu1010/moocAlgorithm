package edu.tyut.adt.tree;

import java.util.Arrays;

/**
 * @title MaxHeap.java
 * @description 数组实现最大堆，为方便表示，下标从1开始，父节点为i/2，
 * 	左、右子节点分别为2i和2i+1
 * @time 2017年4月29日下午1:54:09
 * @author <li>ZZY</li><li>E-mail: zzyu1010@163.com</li>
 * @version 0.0.1 
 */
public class MaxHeap<E extends Comparable<E>> {
	// 定义“哨兵”为大于堆中所有可能元素的值
	private static final int MaxData = 100000;
	private int size;
	private int capacity = 10;
	private Object[] elements;
	
	public MaxHeap() {
		elements = new Object[capacity];
	}
	public MaxHeap(int capacity) {
		elements = new Object[capacity+1];
		// elements[0] 定义为哨兵
		elements[0] = MaxData;
		this.capacity = capacity+1;
	}
	
	public void buildMaxHeap(Object[] o) {
		// 将新数组添加到原最大推上
		Object[] a = new Object[size+o.length+1];
		System.arraycopy(elements, 0, a, 0, size+1);
		System.arraycopy(o, 0, a, size+1, o.length);
		a[0] = MaxData;
		elements = a;
		size = a.length - 1;
		//重新生成最大推
		int parent = 1,child = 1;
		for (int i = size/2; i >= 1; i--) { // 从最后一个结点的父结点开始
			E temp = typeOf(elements[i]);
			for (parent = i; 2 * parent <= size; parent = child) {
				// 向下过滤
				child = 2 * parent;
				if (child != size && typeOf(elements[child]).compareTo(typeOf(elements[child+1])) < 0) {
					child ++;
				}
				if (temp.compareTo(typeOf(elements[child])) >= 0) {
					break;
				} else {
					elements[parent] = elements[child];
				}
			}
			elements[parent] = temp;
		}
	}
	public void add(E e) {
		if (isFull()) {
			System.out.println("最大堆已满");
			return;
		}
		if (e == null) {
			System.out.println("数据不合法");
			return ;
		}
		int i = ++size;
		for (; e.compareTo(typeOf(elements[i/2]))>0; i /= 2) {
			elements[i] = elements[i/2];
		}
		elements[i] = e;
	}
	/*public E deleteMax() {
		if (isEmpty()) {
			System.out.println("最大堆已空");
			return null;
		}
		// 取出最大值，此值为要删除的数据
		E max = typeOf(elements[1]);
		// 取出最大堆中的最后一个元素值
		E temp = typeOf(elements[size--]);
		int child = 0;
		int parent = 1;
		// 移动最后一个元素值，生成新的最大堆，并保持完全二叉树
		for (; 2 * parent <= size; parent = child) {
			child = 2 * parent;
			// 确定有右孩子，并且使child指向左右孩子中的较大者
			if (child != size && typeOf(elements[child]).compareTo(typeOf(elements[child+1])) < 0) {
				child++;
			}
			if (temp.compareTo(typeOf(elements[child])) >= 0) {
				break;
			} else {// 移动temp元素到下一层
				elements[parent] = elements[child];
			}
		}
		elements[parent] = temp;
		return max;
	}*/
	public E deleteMax() {
		return delete(elements[1]);
	}
	public E delete(Object o) {
		int child = 0;
		int parent = indexOf(o);
		E deleteItem = typeOf(o);
		E temp = typeOf(elements[size--]);
		if (parent == -1) {
			return null;
		}
		// 要删除结点的孩子结点形成最大堆
		while (2*parent <= size) {// 有孩子结点
			child = 2*parent;
			if (child != size && typeOf(elements[child]).compareTo(typeOf(elements[child+1])) < 0) {
				child++;
			}
			if (temp.compareTo(typeOf(elements[child])) >= 0) {
				break;
			} else {
				elements[parent] = elements[child];
				parent = child;
			}
		}
		elements[parent] = temp;
		// 要删除结点的父节点形成最大堆
		for (; temp.compareTo(typeOf(elements[parent/2])) > 0; parent /= 2) {
			elements[parent] = elements[parent/2];
		}
		elements[parent] = temp;
		return deleteItem;
	}
	
	public String levelTraverse() {
		int j = 0;
		int count = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= size; i++) {
			sb.append(elements[i]).append(" ") ;
			count++;
			if (count == Math.pow(2, j)) {
				j++;
				count = 0;
				sb.append("\r\n");
			}
		}
		return sb.toString();
	}
	public Object[] toArray() {
		return Arrays.copyOfRange(elements, 1, size+1);
	}
	@SuppressWarnings("unchecked")
	private E typeOf(Object o) {
		return (E)o;
	}
	public boolean isFull() {
		return size+1 == capacity ? true : false;
	}
	public boolean isEmpty() {
		return size == 0 ? true : false;
	}
	public int indexOf(Object o) {
		if (o == null) {
			for (int i = 1; i <= size; i++) {
				if (elements[i] == null) {
					return i;
				}
			}
		} else {
			E e = typeOf(o);
			for (int i = 1; i <= size; i++) {
				if (e.compareTo(typeOf(elements[i])) == 0) {
					return i;
				}
			}
		}
		return -1;
	}
	public int size() {
		return size;
	}
}
