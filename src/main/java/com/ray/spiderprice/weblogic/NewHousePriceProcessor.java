package com.ray.spiderprice.weblogic;

import com.ray.spiderprice.constant.Discription;
import com.ray.spiderprice.domain.po.ErshouHouseInfo;
import com.ray.spiderprice.domain.po.NewHouseInfo;
import com.ray.spiderprice.mapper.ErshouHousePriceMapper;
import com.ray.spiderprice.service.ErshouHousePriceService;
import com.ray.spiderprice.service.pipeline.ErshouFangPipeline;
import com.ray.spiderprice.service.pipeline.NewHousePipeline;
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
import us.codecraft.webmagic.selector.Selectable;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Package:com.ray.spiderprice.weblogic
 * *Author:ray
 * *version:...
 * *Created in 2020/1/24  10:10
 **/
@Component
public class NewHousePriceProcessor implements PageProcessor {

	@Autowired
	private ErshouHousePriceMapper ershouHousePriceMapper;

	@Autowired
	private ErshouHousePriceService ershouHousePriceService;


	//访问的基础数据设置
	private Site site = Site.me()
			.setTimeOut(10 * 1000)
			.setRetrySleepTime(3 * 1000)
			.setRetryTimes(3);

	//开关
	private boolean flag= true;

	//解析页面的方法
	@Override
	public void process(Page page) {
		if(flag){
			String strPrefix="https://wh.fang.ke.com/loupan/nht1pg";
			ArrayList<String> list=new ArrayList<>();
			for (int i = 2; i <= 100; i++) {
				list.add(strPrefix+i);
			}
			page.addTargetRequests(list);
			flag=false;
		}

		ArrayList<NewHouseInfo> list = new ArrayList<>();
		List<Selectable> nodes = page.getHtml().css("li.post_ulog_exposure_scroll").nodes();

		List<Selectable> selectables = page.getHtml()
				.css("ul.resblock-list-wrapper")
				.css("li.post_ulog_exposure_scroll").nodes();

		if (CollectionUtils.isNotEmpty(selectables)){
			selectables.forEach(node -> {
				NewHouseInfo info = new NewHouseInfo();
				//获取房屋属性
				node.css("div.resblock-name")
						.css("span").nodes()
						.stream().skip(1L).
						findFirst().ifPresent(
						node1 -> {
							String s = node.css("span", "text").get();
							info.setHouseType(s);
						}
				);
				//获取小区名称
				String title = node
						.css("div.resblock-name")
						.css("a.name", "title")
						.get();
				info.setVillageName(title);

				//获取地址   一级地址和二级地址
				String location = node.css("a.resblock-location", "text").get();
				if (location != null) {
					String[] strs = location.split("/");
					if (strs.length >= 2) {
						String firstLocation = strs[0];
						String secondLocation = strs[1];
						info.setFirstLocation(firstLocation);
						info.setSecondLocation(secondLocation);
					}
				}
				///loupan/p_lkxtafsqe/
				//获取小区id
				String href = node.css("div.resblock-name")
						.css("a.name", "href")
						.get();
				String[] split = href.split("/");
				if (split.length > 2) {
					String id = split[split.length - 1];
					info.setVillageId(id);
				}
				//获取房子的主题情况
				String mainInfo = node.css("a.resblock-room").css("span").nodes().stream()
						.map(node1 ->
								node1.css("span", "text").get()
						).collect(Collectors.joining("/"));
				info.setHouseMaininfo(mainInfo);

				//获取房子的大小范围(最大值和最小值)
				String text = node.css("a.resblock-room").css("span.area", "text").get();
				if (StringUtils.isNotEmpty(text)) {
					String s = text.replace("建面 ", "");
					String[] strings = s.substring(0, s.length() - 1).split("-");
					if (strings.length>=1){
						BigDecimal min = new BigDecimal(strings[0]);
						info.setMin_area(min);
					}
					if (strings.length>=2) {
						BigDecimal max = new BigDecimal(strings[1]);
						info.setMax_area(max);
					}
				}


				//获取房屋的单价
				String text2 = node.css("div.main-price")
						.css("span.number", "text").get();
				text2=ObjectUtils.defaultIfNull(text2, "0");
				BigDecimal unitPrice = null;
				try {
					unitPrice = new BigDecimal(text2);
				} catch (Exception e) {
					unitPrice=BigDecimal.ZERO;
				}
				info.setUnitPrice(unitPrice);
				info.setCreateTime(new Date());
				list.add(info);
			});
			page.putField("newHouseInfo", list);
		}
	}

	/**
	 * @return
	 * @Description 获取二手房的数据
	 * @Param
	 **/
	private void parseHtml(Page page) {
		//解析详情页面
		ErshouHouseInfo info = new ErshouHouseInfo();

		System.out.println(page.getHtml().toString());
	}


	@Override
	public Site getSite() {
		return site;
	}

	@Autowired
	private NewHousePipeline newHousePipeline;

	private String url = "https://wh.fang.ke.com/loupan/nht1pg1/";

	// initialDelay:当任务启动后，等待多久执行该方法（1000表示1秒）
	// fixedDelay：每隔多久执行方法（100*1000表示100秒）
	//@Scheduled(initialDelay = 5000,fixedDelay = 100*1000)
	public void process() {
		//String str = "https://wh.ke.com/ershoufang/19123110510100135529.html?fb_expo_id=274139822099705872";
		System.out.println("爬虫启动");
		Spider.create(new NewHousePriceProcessor())
				.addUrl(url)
				.setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(100000)))
				.addPipeline(newHousePipeline)
				.thread(10)
				.run();
	}


	public static void main(String[] args) {

		String str = "https://wh.fang.ke.com/loupan/";
		str = "https://wh.fang.ke.com/loupan/guanggudong/";
		str = "https://wh.fang.ke.com/loupan/nho0pg1/";
		str = "https://wh.ke.com/ershoufang/19123110510100135529.html?fb_expo_id=274139822099705872";
		str = "https://wh.fang.ke.com/loupan/nht1pg1/";
		//str="https://wh.ke.com/ershoufang/p02/";//主页面
		Spider spider = Spider.create(new NewHousePriceProcessor())
				.addUrl(str)
				.setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(1000 * 1000)));
		Scheduler scheduler = spider.getScheduler();
		spider.run();
	}

}
