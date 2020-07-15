package com.ray.spiderprice.thread.completetion;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Package:com.ray.spiderprice.thread.completetion
 * *Author:ray
 * *version:...
 * *Created in 2020/2/13  20:23
 **/
public class CompletionServiceDemo {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		Tyrion tyrion = new Tyrion();
		System.out.println(tyrion);
		ExecutorService threadPool = Executors.newFixedThreadPool(5);
		CompletionService<Integer> service = new ExecutorCompletionService<>(threadPool);
		//Future<Tyrion> future = service.submit(() -> {
		//	try {
		//		Thread.sleep(1000);
		//	} catch (InterruptedException e) {
		//		e.printStackTrace();
		//	}
		//	tyrion.setAge(12);
		//}, tyrion);
		//System.out.println(future.get());
		Random random = new Random();
		service.submit(() ->
		{
			int j = random.nextInt(10);
			Thread.sleep(j * 100);
			return j;
		});
		service.submit(() ->
		{
			int j = random.nextInt(10);
			Thread.sleep(j * 100);
			return j;
		});
		service.submit(() ->
		{
			int j = random.nextInt(10);
			Thread.sleep(j * 100);
			return j;
		});
		service.submit(() ->
		{
			int j = random.nextInt(10);
			Thread.sleep(j * 100);
			return j;
		});
		service.submit(() ->
		{ int j = random.nextInt(10);
			Thread.sleep(j * 100);
			return j; });

		for (int i = 0; i < 5; i++) {
			//System.out.println(service.take().get());
			Future<Integer> future = service.poll(150, TimeUnit.MILLISECONDS);
			if (future != null) {
				System.out.println(future.get());
			}
		}
		threadPool.shutdown();
	}
	static class Tyrion {
		private String nickName;
		private Integer age;
		private String familyName;
		public String getNickName() {
			return nickName;
		}
		public void setNickName(String nickName) {
			this.nickName = nickName;
		}
		public Integer getAge() {
			return age;
		}
		public void setAge(Integer age) {
			this.age = age;
		}
		public String getFamilyName() {
			return familyName;
		}
		public void setFamilyName(String familyName) {
			this.familyName = familyName;
		}
	}
}
