package edu.tyut.adt.sort;
/**
 * @title MergeSort.java
 * @description 归并排序是建立在归并操作基础上的一种排序方法。归并操作是将两个已排序的子序列
 * 合并成一个有序序列的过程。
 * 其基本原理是：将大小为N的序列看成N个长度为1的子序列，接下来将相邻子序列两两进行归并操作，形成
 * N/2个长度为2（或1）的有序子序列；然后再继续进行相邻子序列两两归并操作，如此一直循环，直到剩下
 * 1个长度为N的序列，则该序列为原序列完成排序后的结果。
 * 归并排序一般不用于内部排序，它是进行外部排序非常有用的工具
 * @time 2017年5月23日上午10:11:18
 * @author <li>ZZY</li><li>E-mail: zzyu1010@163.com</li>
 * @version 0.0.1 
 */
public class MergeSort {
	/**
	 * 归并排序，递归实现
	 * @param src
	 */
	public void mergeSort(int[] src) {
		int len = src.length;
		int[] tempArr = new int[len]; // 临时数组
		mSort(src, tempArr,0, len-1);
	}
	
	private void mSort(int[] src, int[] tempArr, int left, int right) {
		// 递归将src[left]~src[right]排序
		if (left < right) {
			mSort(src, tempArr, left, (left+right)/2); // 递归排左半边
			mSort(src, tempArr,(left+right)/2+1, right); // 递归排右半边
			merge(src, tempArr, left, (left+right)/2+1, right); // 归并
		}
	}
	
	private void merge(int[] src,int[] tempArr, int left, int mid,int right) {
		int sp = left;	// 有序序列开始位置
		int leftStart = left; // 左半部分子序列开始位置
		int leftEnd = mid-1; // 左半部分子序列结束位置
		// 左右子序列开始归并操作
		while (leftStart <= leftEnd && mid <= right) {
			if (src[leftStart] <= src[mid]) {
				tempArr[sp++] = src[leftStart++];
			} else {
				tempArr[sp++] = src[mid++];
			}
		}
		if (leftStart <= leftEnd) { // 左半部分子序列还有剩余元素
			System.arraycopy(src, leftStart, tempArr, sp, leftEnd-leftStart+1);
		}
		if (mid <= right) { // 右半部分子序列还有剩余元素
			System.arraycopy(src, mid, tempArr, sp, right-mid+1);
		}
		// 把临时数组tempArr的(right-leftStart + 1)个已排序子序列元素复制给源数组src
		System.arraycopy(tempArr, left, src, left, right-left + 1);
	}
	
	/**
	 * 归并排序---循环实现
	 * @param src
	 */
	public void sort(int[] src) {
		int interval = 1; // 初始化序列长度
		int[] tempArr = new int[src.length];
		while (interval < src.length) {
			mergePass(src,tempArr,interval);
			interval *= 2;
			mergePass(tempArr, src, interval); // 利用临时数组做为额外空间
			interval *= 2;
		}
	 
	}
	
	private void mergePass(int[] src, int[] tempArr, int interval) {
		int i = 0;
		for (; i + 2*interval <= src.length; i += 2*interval) {
			merge1(src, tempArr, i, i+interval, i + 2*interval-1);
		}
		if (i + interval < src.length) { // 归并最后两个子序列
			merge1(src,tempArr,i,i+interval,src.length-1);
		} else { // 只剩最后一个序列
			System.arraycopy(src, i, tempArr, i, src.length-i);
		}
	}
	
	private void merge1(int[] src, int[] tempArr, int left, int mid, int right){
		int leftStart = left;
		int leftEnd = mid-1;
		int sp = left; 
		while (left <= leftEnd && mid <= right) {
			if (src[left] <= src[mid]) {
				tempArr[sp++] = src[left++];
			} else {
				tempArr[sp++] = src[mid++];
			}
		}
		if (left <= leftEnd) {
			System.arraycopy(src, left, tempArr, sp, leftEnd-left+1);
		}
		if (mid <= right) {
			System.arraycopy(src, mid, tempArr, sp, right-mid+1);
		}
		System.arraycopy(tempArr, leftStart, src, leftStart, right-leftStart+1);
	}
	
}
