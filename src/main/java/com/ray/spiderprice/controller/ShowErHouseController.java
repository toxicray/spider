package com.ray.spiderprice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Package:com.ray.spiderprice.controller
 * *Author:ray
 * *version:...
 * *Created in 2020/1/29  15:48
 **/
@RestController
public class ShowErHouseController {

	@GetMapping("/ershou/test")
	public String showDistribute(){
		return "新年快乐";
	}

}
