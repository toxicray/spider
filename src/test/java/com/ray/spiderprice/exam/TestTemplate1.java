package com.ray.spiderprice.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TestTemplate1 {
	public static void main(String[] args) throws IOException {

		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		String str;
		Map<Character,Integer> map = new HashMap<>();
		map.put('0', 0);
		map.put('1', 1);
		map.put('2', 2);
		map.put('3', 3);
		map.put('4', 4);
		map.put('5', 5);
		map.put('6', 6);
		map.put('7', 7);
		map.put('8', 8);
		map.put('9', 9);
		map.put('A', 10);
		map.put('B', 11);
		map.put('C', 12);
		map.put('D', 13);
		map.put('E', 14);
		map.put('F', 15);
		while ((str = br.readLine())!=null){
			int result = 0;
			char[] chars = str.substring(2).toCharArray();
			for (int i = chars.length - 1; i >= 0; i--) {
				result += map.get(chars[i])*Math.pow(16, chars.length-1-i);
			}
			System.out.println(result+"");
		}
	}
}

