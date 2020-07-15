package com.ray.spiderprice.dp;

/**
 * Package:com.ray.spiderprice.dp
 * *Author:ray
 * *version:...
 * *Created in 2020/1/31  11:20
 **/
public class DpQ198 {
	public static void main(String[] args) {

		int[] arr={1,3,5,9,2,4,8};
		int value= robHouse(arr);
		System.out.println(value);
	}


	//尝试缩小数据规模
	private static int robHouse(int[] arr) {
		return solve(arr.length-1,arr);
	}

	public static int solve(int idx,int[] nums){
		if (idx<0){
		    return 0;
		}
		return Math.max(nums[idx]+solve(idx-2,nums ),solve(idx-1, nums));
	}
	//重复计算的子问题,可以节省计算,不至于重复解决问题

}
