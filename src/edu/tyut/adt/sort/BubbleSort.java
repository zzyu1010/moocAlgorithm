package edu.tyut.adt.sort;
/**
 * @title BubbleSort.java
 * @description 冒泡排序：冒泡排序是最简单的交换排序。对元素个数为N的待排序列进行排序时，
 * 供进行N-1次循环。在第k次循环中，对从第一道第N-k个元素从前往后进行比较，每次比较相邻的两个
 * 元素，若前一个元素大于后一个元素，则两者互换位置，否则保持位置不变。这样一次循环下来，就把
 * 第k大的元素移动到N-k个位置上，称为第k趟的冒泡。整个过程一共进行N-1趟冒泡，直到第一个和第二个
 * 元素比较完成，最终剩余的最小的元素，留在第一个位置上，排序结束。
 * @time 2017年5月22日下午9:25:29
 * @author <li>ZZY</li><li>E-mail: zzyu1010@163.com</li>
 * @version 0.0.1 
 */
public class BubbleSort {
	
	/**
	 * 升序排列
	 * @param src
	 */
	public void sort(int[] src) {
		boolean flag = false;
		int temp;
		for (int i = src.length-1; i >= 0; i--) { // 冒泡的趟数
			flag = false;
			for (int j = 0; j < i; j++) { // 每趟比较的次数
				if (src[j] > src[j+1]) { // 找出一个最大元素，交换到最右端
					temp = src[j];
					src[j] = src[j+1];
					src[j+1] = temp;
					flag = true;
				}
			}
			if (!flag) { // 没有发生交换，说明数组已经有序，跳出循环
				break;
			}
		}
	}
}
