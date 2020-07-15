package com.ray.spiderprice.exam;

import org.jcp.xml.dsig.internal.dom.ApacheCanonicalizer;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Package:com.ray.spiderprice.exam
 * *Author:ray
 * *version:...
 * *Created in 2020/4/10  18:52
 **/
public class Main1 {
	public static void main(String[] args) {
		String str = "aba";
		System.out.println(str.replaceFirst("a", ""));
		System.out.println(str);
	}
	public int lengthOfLongestSubstring(String s) {
		int slow = 0;
		int fast = 0;
		Set<Character> set = new HashSet<>();
		int result = 0;
		while(fast <= s.length()-1 ){
			if (set.contains(s.charAt(fast))){
				set.remove(s.charAt(slow));
				slow++;
			}else{
				fast++;
				set.add(s.charAt(fast));
				result= Math.max(result,fast-slow);
			}
		}
		return result;
	}
	public void quickSort(int[] arr){
		qSort(arr,0,arr.length-1);
	}

	private void qSort(int[] arr, int low, int high) {
		if(low < high){
			int pivot = partition(arr,low,high);
			partition(arr,pivot+1, high);
			partition(arr,low,pivot);
		}
	}

	private int partition(int[] arr, int low, int high) {
		int pivotKey = arr[low];
		while(low < high){
			while(low < high && arr[high] >=pivotKey){
				high--;
			}
			arr[low] = arr[high];
			while(low < high  && arr[low] <= pivotKey){
				low++;
			}
		}
		return low;
	};
}
