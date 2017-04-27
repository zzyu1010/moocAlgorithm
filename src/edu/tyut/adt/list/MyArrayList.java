package edu.tyut.adt.list;

import java.util.Arrays;

/**
 * @author ZZY, E-mail: zzyu1010@163.com
 * @version 0.0.1, created: 2017年4月27日下午3:55:31
 */
public class MyArrayList<E> {
	private int size ;
	private int capacity;
	private static final int DEFAULT_CAPACITY = 4;
	private Object[] elements;
	
	public MyArrayList() {
		this.elements = new Object[DEFAULT_CAPACITY];
		this.capacity = DEFAULT_CAPACITY;
	}
	public MyArrayList(int inialCapacity) {
		this.elements = new Object[inialCapacity];
		this.capacity = inialCapacity;
	}
	
	@SuppressWarnings("unchecked")
	private E elementData(int index) {
		return (E)elements[index];
	}
	private void rangeCheck(int index) {
		if (index < 0 || index >= size) {
			throw new ArrayIndexOutOfBoundsException();
		}
	}
	public E get(int index) {
		rangeCheck(index);
		return elementData(index);
	}
	public E set(int index, E e) {
		rangeCheck(index);
		E oldValue = elementData(index);
		elements[index] = e;
		return oldValue;
		                    
	}
	public boolean add(E e) {
		if (isFull()) {
			doubleCapacity();
		}
		elements[size++] = e;
		return true;
	}
	public void add(int index, E e) {
		if (index < 0 || index > size) {
			throw new ArrayIndexOutOfBoundsException();
		}
		if (isFull()) {
			doubleCapacity();
		}
		int numCount = size - index;
		System.arraycopy(elements, index, elements, index+1, numCount);
		elements[index] = e;
		size++;
	}
	private void doubleCapacity() {
		int newCapacity = capacity << 1;
		Object[] temp = new Object[newCapacity];
		System.arraycopy(elements, 0, temp, 0, size);
		elements = temp;
		capacity = newCapacity;
	}
	public E remove(int index) {
		rangeCheck(index);
		E oldValue = elementData(index);
		fastRemove(index);
		return oldValue;
	}
	public boolean remove(Object obj) {
		if (obj == null) {
			for (int i = 0; i < size; i++) {
				if (obj == elements[i]) {
					fastRemove(i);
					return true;
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (obj.equals(elements[i])) {
					fastRemove(i);
					return true;
				}
			}
		}
		return false;
	}
	
	private void fastRemove(int index) {
		int numCount = size - index -1;
		System.arraycopy(elements, index+1, elements, index, numCount);
		// clear to let GC do its work
		elements[--size] = null;
	}
	
	/**
	 * 
	 * @param obj 需要查询的数据
	 * @return 指定数据所在的位置，如果不存在则返回-1;
	 */
	public int indexOf(Object obj) {
		if (obj == null) {
			for (int i = 0; i < size; i++) {
				 if (obj == elements[i]) {
					 return i;
				 }
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (obj.equals(elements[i])) {
					return i;
				}
			}
		}
		return -1;
	}
	
	public boolean contains(Object obj) {
		if (indexOf(obj) == -1) {
			return false;
		}
		return true;
	}
	
	public Object[] toArray() {
		return Arrays.copyOf(elements, size);
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
	public int capacity() {
		return capacity;
	}
	public boolean isEmpty() {
		return size == 0 ? true : false;
	}
	public boolean isFull() {
		return size == capacity ? true : false;
	}
}
