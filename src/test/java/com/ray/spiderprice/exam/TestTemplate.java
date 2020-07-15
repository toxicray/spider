package com.ray.spiderprice.exam;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;


public class TestTemplate {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str;
		while ((str = br.readLine()) != null){
			Set<Integer> set = new HashSet<>();
			Integer count = Integer.valueOf(str);
			for (Integer i = 0; i < count; i++) {
				set.add(Integer.valueOf(br.readLine()));
			}
			Integer count1 = Integer.valueOf(br.readLine());
			for (Integer i = 0; i < count1; i++) {
				set.add(Integer.valueOf(br.readLine()));
			}
			set.stream().sorted().forEach(item->{
				System.out.println(item);
			});
		}
	}
}
