package com.ray.spiderprice.exam;

import java.util.Scanner;

/**
 * Package:com.ray.spiderprice.exam
 * *Author:ray
 * *version:...
 * *Created in 2020/4/10  14:58
 **/
public class Test1 {

	public static void main(String[] args) {

		Scanner sc =new Scanner(System.in);


		System.out.println(Integer.reverse(1));

	}
	public String reverseWords(String s) {
		String str = s.trim();
		String[] strArr = str.split(" ");
		if(strArr.length == 0){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for(int i=strArr.length-1 ;i>=0;i--){
			if(!strArr[i].equalsIgnoreCase("")){
				sb.append(strArr[i]);
				if(i != 0){
					sb.append(" ");
				}
			}
		}
		return sb.toString();
	}

	public int countNum(int num){
		int result = 0;
		while(num != 0){
			if((num&1) != 0){
				result++;
			}
			num >>=1;
		}
		return result;
	}



}
