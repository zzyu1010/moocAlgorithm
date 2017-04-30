package edu.tyut.adt.sort;
/**
 * @title SimpleSelectionSort.java
 * @description 简单选择排序：首先在未排序的序列中选出最小的元素和序列的首位元素交换，
 * 接下来在剩下的未排序序列中再选出最小元素与序列的第二位元素交换，以此类推，最后形成
 * 从小到大的已排序列。
 * @time 2017年4月30日下午6:06:41
 * @author <li>ZZY</li><li>E-mail: zzyu1010@163.com</li>
 * @version 0.0.1 
 */
public class SimpleSelectionSort {
	public int[] sort(int[] a) {
		int min = -1;
		int temp;
		for (int i = 0; i < a.length; i++) {
			min = i;
			for (int j = i+1; j < a.length; j++) {
				if (a[min] > a[j]) {
					min = j;
				}
			}
			if (min != i) {
				temp = a[min];
				a[min] = a[i];
				a[i] = temp;
			}
		}
		return a;
	}
}
