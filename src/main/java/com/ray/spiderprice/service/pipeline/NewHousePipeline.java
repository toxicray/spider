package com.ray.spiderprice.service.pipeline;

import com.ray.spiderprice.domain.po.ErshouHouseInfo;
import com.ray.spiderprice.domain.po.NewHouseInfo;
import com.ray.spiderprice.mapper.ErshouHousePriceMapper;
import com.ray.spiderprice.service.ErshouHousePriceService;
import com.ray.spiderprice.service.NewHousePriceService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

/**
 * Package:com.ray.spiderprice.service.pipeline
 * *Author:ray
 * *version:...
 * *Created in 2020/1/27  16:12
 **/
//获取新房的小区数据
@Component
public class NewHousePipeline implements Pipeline {

	@Autowired
	private NewHousePriceService newHousePriceService;

	@Override
	public void process(ResultItems resultItems, Task task) {
		List<NewHouseInfo> houseInfo = resultItems.get("newHouseInfo");
		if (CollectionUtils.isNotEmpty(houseInfo)) {
			//需要校验数据是否已存在
			houseInfo.forEach(info->{
				newHousePriceService.addErShouHouseInfo(info);
			});
		}
	}
}
