package edu.tyut.adt.sort;

/**
 * @title RadixSort.java
 * @description 基数排序分为主位优先法和次位优先法
 * @time 2017年5月23日下午5:18:30
 * @author <li>ZZY</li><li>E-mail: zzyu1010@163.com</li>
 * @version 0.0.1 
 */
/**
 * 给定范围在0~999之间的10个关键字{64,8,216,512,27,729,0,1,343,125},采用基数排序算法进行递增排序
 * 
 * 我们可以将每个关键字看成一个3位的十进制整数（不足位的在左边补0），从而将每个十进制整数关键字分解成3个关键字
 * ，其个位数为最次位关键字，百位数为最主位关键字。这就是以10为基数的分解
 * 
 * 对给定的10个记录用次位优先法进行基数排序，首先对最次位（个位）关键字建立10个桶，将记录按其个位数字的大小放入
 * 响应的桶中，接下来按下一位次位关键字（十位）排序，最后按最主位（百位）关键字排序。
 * 
 * @author ZZY
 *
 */
public class RadixSort {
	private int maxDigit = 3; // 最大数字可分解的位数
	private int radix = 10; // 排序所采用的基数

	private class Node { // 对应每个数字
		int[] keys = new int[maxDigit]; // 存放关键字，其中keys[0]位最主位关键字，keys[maxDigit-1]为最次位关键字
		int data;
		Node next;
	}

	private Node root = new Node();

	/**
	 * 针对不大于三位数组成的数值进行排序（0~999）
	 * @param src
	 */
	public void radixSort(int[] src) {
		Node pre = root;
		for (int x : src) {
			Node node = new Node();
			node.keys[0] = x / 100;
			node.keys[1] = (x / 10) % 10;
			node.keys[2] = x % 10;
			node.data = x;
			pre.next = node;
			pre = pre.next;
		}

		radixSortNode(root.next);
		Node temp = root;
		int i = 0;
		while (temp != null) {
			src[i++] = temp.data;
			temp = temp.next;
		}

		// 测试链表的建立及关键字提取
		/*
		 * Node temp = root.next; while (temp != null) {
		 * System.out.print(temp.data +Arrays.toString(temp.keys)+" "); temp =
		 * temp.next; }
		 */
	}

	private void radixSortNode(Node node) {
		Node[] bucket = new Node[radix]; // 建立10个桶(0~9)
		Node[] rear = new Node[radix]; // 记录每个桶链表的尾元素位置
		int digit;

		for (int i = maxDigit - 1; i >= 0; i--) { // 从最次位关键字开始
			for (int k = 0; k < radix; k++)
				bucket[k] = rear[k] = null; // 每趟初始化一次
			while (node != null) { // 将关键字逐一分配到桶
				digit = node.keys[i]; // 取出当前关键字
				if (bucket[digit] == null) { // 若对应的桶是空的
					bucket[digit] = node;
				} else {
					rear[digit].next = node; // 否则放入桶尾
				}
				rear[digit] = node; // 更新尾指针
				node = node.next;
			}
			// 按照当前关键字排序完成
			for (int j = radix - 1; j >= 0; j--) { // 将所有桶中元素收集串联,建立新的链表
				if (bucket[j] != null) { //
					rear[j].next = node;
					node = bucket[j];
				}
			}
		}
		root = node;
	}
	
}
