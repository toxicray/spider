package com.ray.spiderprice.thread.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Package:com.ray.spiderprice.thread.atomic
 * *Author:ray
 * *version:...
 * *Created in 2020/2/13  22:09
 **/
public class AtomicDemo {
	public static void main(String[] args) {
		AtomicInteger demo=new AtomicInteger(0);
		demo.getAndUpdate((a)->a+2);
		System.out.println(demo.get());
	}
}
