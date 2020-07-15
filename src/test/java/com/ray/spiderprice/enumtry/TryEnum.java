package com.ray.spiderprice.enumtry;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Package:com.ray.spiderprice.enumtry
 * *Author:ray
 * *version:...
 * *Created in 2020/5/21  23:42
 **/
public enum TryEnum {

	M(1,2),

	L(2,4);

	private Integer name;

	private Integer value;

	TryEnum(Integer name, Integer value) {
		this.name = name;
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public Integer getName() {
		return name;
	}

	public static void main(String[] args) {
		Stream.of(TryEnum.values())
				.collect(Collectors.toMap(a->a.getName(), b-> b.getValue()))
				.forEach((k,v)->{
					System.out.println(k+"----"+v);
				});
	}
}
