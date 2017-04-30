package edu.tyut.adt.sort;

import java.util.Arrays;

/**
 * @title HeapSort.java
 * @description 实现堆排序, 数据从数组下标0开始，父结点编号为(i-1)/2,
 * 左右孩子结点为2i和2i+1. 平均时间复杂度为O(NlogN),额外空间复杂度为O(1).
 * @time 2017年4月30日下午3:45:30
 * @author <li>ZZY</li><li>E-mail: zzyu1010@163.com</li>
 * @version 0.0.1 
 */
public class HeapSort<E extends Comparable<E>> {
	private Object[] elements;
	private int capacity = 16;
	private int size;
	
	public HeapSort() {
		elements = new Object[capacity];
	}
	public HeapSort(int capacity) {
		this.capacity = capacity;
	}
	/**
	 * 添加要排序的数据
	 * @param e
	 */
	public void add(E e) {
		if (isFull()) {
			throw new ArrayIndexOutOfBoundsException("index = " + size + ", capacity = " + capacity);
		}
		elements[size++] = e;
	}
	public void sort(Object[] o) {
		clear();
		elements = o;
		size = o.length;
		sort();
	}
	public void sort() {
		// 根据输入的数组，建立初始化最大堆
		for (int i = size/2-1; i >= 0; i--) { // 从有儿子的最后一个结点开始
			adjust(i,size);
		}
		// 调整最大堆
		Object temp;
		for (int i = size-1; i >= 1; i--) {
			 temp = elements[0];
			 elements[0] = elements[i];
			 elements[i] = temp;
			 // 将有i个元素的新堆从根结点向下过滤调整
			 adjust(0,i);
		}
	}
	/**
	 * 对elements[]中的前N个元素从第i个元素开始向下迁移调整
	 * @param i
	 * @param N
	 */
	private void adjust(int i, int N) {
		int child = -1;
		int parent = -1;
		E temp = typeOf(elements[i]);
		for (parent = i; 2 * parent + 1 < N; parent = child) {// 从0开始，数组下标最大为N-1
			child = 2 * parent + 1;
			if (child != N-1 && typeOf(elements[child]).compareTo(typeOf(elements[child+1])) < 0) {
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
	 
	@SuppressWarnings("unchecked")
	private E typeOf(Object o) {
		return (E)o;
	}
	
	public Object[] toArray() {
		return Arrays.copyOfRange(elements, 0, size);
	}
	public boolean isEmpty() {
		return size == 0 ? true : false;
	}
	public boolean isFull() {
		return size == capacity ? true : false;
	}
	public void clear() {
		for (int i = 0; i < size; i++) {
			elements[i] = null;
		}
		size = 0;
	}
	public int size() {
		return size;
	}
}
