package com.ray.spiderprice;

import com.ray.spiderprice.domain.po.ErshouHouseInfo;
import com.ray.spiderprice.domain.query.NewHouseInfoQuery;
import com.ray.spiderprice.mapper.NewHousePriceMapper;
import com.ray.spiderprice.service.ErshouHousePriceService;
import com.ray.spiderprice.service.NewHousePriceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpiderPriceApplicationTests {


	@Autowired
	private ErshouHousePriceService ershouHousePriceService;

	@Autowired
	private NewHousePriceService newHousePriceService;

	@Test
	void contextLoads() {

	}

	@Test
	public void testAdd(){
		ErshouHouseInfo info=new ErshouHouseInfo();
		info.setRatio("哈哈");
		ershouHousePriceService.addErShouHouseInfo(info);
	}


	@Test
	public void testXmlConfig(){
		NewHouseInfoQuery info=new NewHouseInfoQuery();
		info.setPageIndex(1);
		info.setPageSize(10);
		newHousePriceService.listNewHouseInfo(info);
	}
}
