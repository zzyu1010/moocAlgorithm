package edu.tyut.adt.tree;

import java.util.Arrays;

/**
 * @title MinHeapGenetics.java
 * @description 泛型
 * @time 2017年5月22日上午10:49:20
 * @author
 * 		<li>ZZY</li>
 *         <li>E-mail: zzyu1010@163.com</li>
 * @version 0.0.1
 */
public class MinHeapGenetics<T extends Comparable<T>> {
	private T[] elements;
	private int size;
	private int capacity;
	private static final int DEFAULT_CAPACITY = 12;

	@SuppressWarnings("unchecked")
	public MinHeapGenetics() {
		elements = (T[])new Object[DEFAULT_CAPACITY];
		capacity = DEFAULT_CAPACITY;
	}

	@SuppressWarnings("unchecked")
	public MinHeapGenetics(int capacity) {
		elements = (T[])new Object[capacity];
		this.capacity = capacity;
	}

	/**
	 * 配合buildMinHeap()使用
	 * 
	 * @param src
	 */
	public MinHeapGenetics(T[] src) { // capacity= 12
		this();
		size = src.length;
		System.arraycopy(src, 0, elements, 0, size);
	}

	/**
	 * 和MinHeap(int[] src)一起使用
	 * 
	 * @param src
	 */
	public void buildMinHeap() {
		int parent = -1, child = -1;
		T temp = null;
		for (int i = size / 2 - 1; i >= 0; i--) { // 从最后一个叶子结点的父结点下标开始
			temp = elements[i];
			for (parent = i; parent * 2 + 1 < size; parent = child) { // 有子结点
				child = 2 * parent + 1; // 左孩子结点

				if (child != size - 1 && elements[child].compareTo(elements[child + 1]) > 0) { // 有右孩子，且右孩子比左孩子小
					child++; // 取出左右孩子中较小的一个
				}
				if (temp.compareTo(elements[child]) < 0) // 父结点比孩子结点小，不做调整
					break;
				else
					elements[parent] = elements[child]; // 交换父结点与孩子结点位置
			}
			elements[parent] = temp;
		}
	}

	public void insert(T item) {
		if (isFull()) {
			return;
		}
		if (isEmpty()) {
			elements[0] = item;
		}
		// 调整最小堆,向下过滤结点
		int i = size++;
		for (; elements[(i - 1) / 2].compareTo(item) > 0 && i > 0; i = (i - 1) / 2) { // 要插入的结点与其父结点相比较
			elements[i] = elements[(i - 1) / 2];
		}
		elements[i] = item;
	}

	/**
	 * 删除最小值，并返回
	 * 
	 * @return
	 */
	public T deleteMin() {
		if (isEmpty()) {
			return null;
		}
		int parent = -1;
		int child = -1;

		T minItem = elements[0]; // 取出根节点最小值
		// 用最小堆中最后一个元素从根节点开始向上过滤下层结点
		T temp = elements[--size];
		for (parent = 0; 2 * parent + 1 <= size; parent = child) {
			child = parent * 2 + 1;
			if (child != size && elements[child].compareTo(elements[child + 1]) > 0) {
				child++;// child指向左右孩子结点较小者
			}
			if (temp.compareTo(elements[child]) < 0) {
				break;
			} else { // 移动temp元素到下一层
				elements[parent] = elements[child];
			}
		}
		elements[parent] = temp;
		return minItem;
	}

	public T[] toArray() {
		return Arrays.copyOf(elements, size);
	}

	public boolean isEmpty() {
		return size == 0 ? true : false;
	}

	public boolean isFull() {
		return size == capacity ? true : false;
	}
}
