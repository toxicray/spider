package com.ray.spiderprice.starter;

import com.ray.spiderprice.parseHtml.ParseMethod;
import com.ray.spiderprice.weblogic.PriceProcessor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.scheduler.Scheduler;

import java.util.Date;

/**
 * Package:com.ray.spiderprice.starter
 * *Author:ray
 * *version:...
 * *Created in 2020/1/26  13:11
 **/
@Service
public class StarterService {

	/**
	 *@Description
			* @Param 启动爬虫,开始爬取数据
			* @return
			**/
	@Scheduled(initialDelay = 1000,fixedDelay = 1000*1000)
	public void executeGet(){
		System.out.println("爬虫启动");
		//String str= "https://wh.fang.ke.com/loupan/nho0pg1/";
		//Spider spider = Spider.create(new PriceProcessor())
		//		.addUrl(str)
		//		.setScheduler( new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(1000*1000)));
		//Scheduler scheduler = spider.getScheduler();
		//spider.run();
	}


	public void getInfo(String url, ParseMethod method, Page page){
		method.doParseHtml(page);
	}
}
