package com.ray.spiderprice.weblogic;

import com.ray.spiderprice.constant.Discription;
import com.ray.spiderprice.domain.po.ErshouHouseInfo;
import com.ray.spiderprice.mapper.ErshouHousePriceMapper;
import com.ray.spiderprice.service.ErshouHousePriceService;
import com.ray.spiderprice.service.pipeline.ErshouFangPipeline;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.scheduler.Scheduler;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.math.BigDecimal;
import java.util.*;

/**
 * Package:com.ray.spiderprice.weblogic
 * *Author:ray
 * *version:...
 * *Created in 2020/1/24  10:10
 **/
@Component
public class PriceProcessor implements PageProcessor {

	@Autowired
	private ErshouHousePriceMapper ershouHousePriceMapper;

	@Autowired
	private ErshouHousePriceService ershouHousePriceService;


	//访问的基础数据设置
	private Site site = Site.me()
			.setTimeOut(10*1000)
			.setRetrySleepTime(3*1000)
			.setRetryTimes(3);

	//解析页面的方法
	@Override
	public void process(Page page) {
		String urlPrefix="https://wh.ke.com/ershoufang/pg";//域名前缀
		ArrayList<String> list=new ArrayList<>();
		//获取初始化的加载页面
		for (int i = 3; i <= 100; i++) {
			list.add(urlPrefix+i);
		}
		page.addTargetRequests(list);

		//判断当前进入的页面是详情页面还是列表页面
		List<Selectable> selectables = page.getHtml().css("li.clear").css("a.img").nodes();
		if (CollectionUtils.isNotEmpty(selectables)){
		    //如果列表项不为空,说明是列表页面
			page.getHtml().css("li.clear").css("a.img")
			.nodes().stream().forEach(node->{
						String id = node.css("a.img", "data-maidian").get();
						String url = node.links().get();
						String defautStr="?fb_expo_id=";
						page.addTargetRequest(url+defautStr+id);
					}
			);
		}else{
			parseHtml(page);
		}

		////获取页面的链接信息
		Selectable css = page.getHtml().css("li.clear").css("a.img");
		css.nodes().stream().forEach(node->{
					String id = node.css("a.img", "data-maidian").get();
					String url = node.links().get();
					String defautStr="?fb_expo_id=";
					System.out.println(url+defautStr+id);
				}
				);

	}

	/**
	 *@Description 获取二手房的数据
			* @Param
			* @return
			**/
	private void parseHtml(Page page) {

		//解析详情页面
		ErshouHouseInfo info=new ErshouHouseInfo();

		//总价
		String price = page.getHtml().css("div.price").css("span.total", "text").toString();
		price= ObjectUtils.defaultIfNull(price,"0");
		info.setSumPrice(new BigDecimal(price));
		//单价
		String unitprice = page.getHtml().css("div.price")
				.css("div.text")
				.css("div.unitprice")
				.css("span.unitPriceValue","text")
				.toString();
		unitprice=ObjectUtils.defaultIfNull(unitprice, "0");
		info.setUnitPrice(new BigDecimal(unitprice));

		String mainInfo = page.getHtml().css("div.houseInfo")
				.css("div.mainInfo","text").toString();
		info.setHouseMaininfo(mainInfo);

		String subInfo = page.getHtml().css("div.houseInfo")
				.css("div.subInfo","text").toString();
		info.setHouseSubinfo(subInfo);


		//大小
		String area = page.getHtml().css("div.area")
				.css("div.mainInfo","text").toString();
		String newarea = area.replace("平米", "");
		if (area != null) {
			info.setArea(new BigDecimal(newarea));
		}

		String year = page.getHtml().css("div.area")
				.css("div.subInfo","text").toString();
		info.setBuildTime(year);

		String villageName = page.getHtml().css("a.no_resblock_a", "text").toString();
		info.setVillageName(villageName);

		//获取小区的区域
		List<String> all = page.getHtml().css("div.areaName")
				.css("a", "text").all();
		String region="";
		String village="";
		if (CollectionUtils.isNotEmpty(all)){
			region=all.get(0);
			if (all.size()>1){
				village=all.get(1);
			}
		}
		info.setFirstLocation(region);
		info.setSecondLocation(village);

		//"------------------基本属性------------------------");
		//获取房屋的基本属性
		List<Selectable> nodes = page.getHtml()
				.css("div.introContent")
				.css("div.content")
				.css("li").nodes();
		if (CollectionUtils.isNotEmpty(nodes)){
			Map<String,String> map=new HashMap<>();
			nodes.stream().forEach(node->{
				String value = node.css("li", "text").toString();//房屋的属性值
				String key = node.css("li").css("span","text").toString();//房屋的属性值
				map.put(key,value);
			});
			//map.forEach((k,v)->{
			//	System.out.println(k+":...:"+v);
			//});
			info.setHouseLimit(map.get(Discription.CHANQUAN_NIANXIAN));
			info.setHangTime(map.get("挂牌时间"));
			info.setCreateTime(new Date());
			info.setRatio(map.get(Discription.TIHU_BILI));
			map.clear();
		}
		//获取贝壳房号id
		String houseId = page.getHtml().css("div.houseRecord")
				.css("span.info", "text").get();
		String s = StringUtils.deleteWhitespace(houseId);
		info.setHouseId(s);
		page.putField("houseInfo",info);
	}


	//
	@Override
	public Site getSite() {
		return site;
	}

	@Autowired
	private ErshouFangPipeline ershouFangPipeline;


	private String url="https://wh.ke.com/ershoufang/pg01/";

	// initialDelay:当任务启动后，等待多久执行该方法（1000表示1秒）
	// fixedDelay：每隔多久执行方法（100*1000表示100秒）
	@Scheduled(initialDelay = 5000,fixedDelay = 100*1000)
	public void process(){
		String str="https://wh.ke.com/ershoufang/19123110510100135529.html?fb_expo_id=274139822099705872";

		System.out.println("爬虫启动");
		Spider.create(new PriceProcessor())
				.addUrl(str)
				.setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(100000)))
				.addPipeline(ershouFangPipeline)
				.thread(10)
				.run();
	}







	public static void main(String[] args) {

		String str="https://wh.fang.ke.com/loupan/";
		str="https://wh.fang.ke.com/loupan/guanggudong/";
		str= "https://wh.fang.ke.com/loupan/nho0pg1/";
		str="https://wh.ke.com/ershoufang/19123110510100135529.html?fb_expo_id=274139822099705872";

		//str="https://wh.ke.com/ershoufang/p02/";//主页面
		Spider spider = Spider.create(new PriceProcessor())
				.addUrl(str)
				.setScheduler( new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(1000*1000)));
		Scheduler scheduler = spider.getScheduler();
		spider.run();
	}

}
