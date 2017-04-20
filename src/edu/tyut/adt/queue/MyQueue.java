package edu.tyut.adt.queue;

/**
 * @author ZZY, E-mail: zzyu1010@163.com
 * @version 0.0.1, created:2017年4月19日下午9:21:26
 */
public class MyQueue<T> {
	private int length;
	private int defaultCapacity = 4;
	private int capacity ;
	private int head;
	private int tail;
	private Object[] elements;
	
	public MyQueue() {
		this.elements = new Object[this.defaultCapacity];
		this.capacity = this.defaultCapacity;
	}
	public MyQueue(int capacity) {
		this.elements = new Object[capacity];
		this.capacity = capacity;
	}
	
	public boolean addLast(T t) {
		if (t == null) {
			return false;
		}
		this.elements[tail] = t;
		tail = (tail + 1)%capacity;
		this.length ++;
		if (isFull()) {
			doubleCapacity();
		}
		return true;
	}
	public T pollFirst() {
		if (isEmpity()) {
			return null;
		}
		@SuppressWarnings("unchecked")
		T result = (T)elements[head];
		elements[head] = null;
		head = (head+1) % capacity;
		this.length --;
		return result;
	}
	private void doubleCapacity() {
		assert head == tail;
		int p = head;
		int n = capacity - p;
		int newCapacity = capacity << 1;
		Object[] newElements = new Object[newCapacity];
		System.arraycopy(elements, p, newElements, 0, n);
		System.arraycopy(elements, 0, newElements, n, p);
		elements = newElements;
		head = 0;
		tail = capacity;
		capacity = newCapacity;
	}
	
	public void clear() {
		int h = head;
		int t = tail;
		this.length = 0;
		if (h != t) {
			head = tail = 0;
			int i = h;
			do {
				elements[i] = null;
				i = (i+1) % capacity;
			} while (i != t);
		}
	}
	public Object[] toArray() {
		Object[] obj = new Object[this.length];
		if (head < tail) {
			System.arraycopy(elements, head, obj, 0, this.length);
		} else if (head > tail) {
			int headPortionLen = this.length - head;
			System.arraycopy(elements, head, obj, 0, headPortionLen);
			System.arraycopy(elements, 0, obj, headPortionLen, tail);
		}
		return obj;
	}
	public boolean isEmpity() {
		return length == 0 ? true : false;
	}
	public boolean isFull() {
		return length == capacity ? true : false;
	}
	public int size() {
		return this.length;
	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int count = 0;
		if (isEmpity()) {
			return null;
		}
		for (int i = head; i < this.length + head; i++) {
			sb.append(elements[i] + " "+ "前面排队人数：" + count++);
			i = i % capacity;
		}
		return sb.toString();
	}
}
