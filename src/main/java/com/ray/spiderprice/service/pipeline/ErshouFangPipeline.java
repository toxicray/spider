package com.ray.spiderprice.service.pipeline;

import com.ray.spiderprice.domain.po.ErshouHouseInfo;
import com.ray.spiderprice.mapper.ErshouHousePriceMapper;
import com.ray.spiderprice.service.ErshouHousePriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * Package:com.ray.spiderprice.service.pipeline
 * *Author:ray
 * *version:...
 * *Created in 2020/1/27  16:12
 **/
@Component
public class ErshouFangPipeline implements Pipeline {

	@Autowired
	private ErshouHousePriceMapper ershouHousePriceMapper;

	@Autowired
	private ErshouHousePriceService ershouHousePriceService;

	@Override
	public void process(ResultItems resultItems, Task task) {
		ErshouHouseInfo houseInfo = resultItems.get("houseInfo");
		if (houseInfo != null) {
			//需要校验数据是否已存在
			ershouHousePriceService.addErShouHouseInfo(houseInfo);
		}
	}
}
