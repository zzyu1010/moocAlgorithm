package edu.tyut.adt.sort;
/**
 * @title SimpleInsertionSort.java
 * @description 简单插入排序：其核心思想是将带待排序的一组序列分为已排序好的和未排序的两部分；初始
 * 状态时，已排序序列仅包含第一个元素，未排序序列中的元素为除第一个元素以外的N-1个元素；此后将未排序序列
 * 中的元素逐一插入到已排序的序列中。如此往复，经过N-1次插入后，未排序序列中元素个数为0，则排序完成。
 * @time 2017年5月22日下午7:59:55
 * @author <li>ZZY</li><li>E-mail: zzyu1010@163.com</li>
 * @version 0.0.1 
 */
public class SimpleInsertionSort {
	
	/**
	 * 升序排列
	 * @param src
	 */
	public void insertionSort(int[] src) {
		int temp ;
		int j;
		for (int i = 0; i < src.length; i++) {
			temp = src[i]; // 取出未排序序列中的第一个元素
			for (j = i; j > 0 && temp < src[j-1]; j--) {
				src[j] = src[j-1]; // 依次与已排列序列中元素比较并右移
			}
			src[j] = temp; // 放进合适位置
		}
		
	}
}
