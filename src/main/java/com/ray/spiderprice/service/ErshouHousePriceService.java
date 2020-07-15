package com.ray.spiderprice.service;

import com.google.common.collect.Lists;
import com.ray.spiderprice.domain.po.ErshouHouseInfo;
import com.ray.spiderprice.mapper.ErshouHousePriceMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Package:com.ray.spiderprice.service
 * *Author:ray
 * *version:...
 * *Created in 2020/1/27  16:54
 **/
@Service
public class ErshouHousePriceService {

	@Autowired
	private ErshouHousePriceMapper ershouHousePriceMapper;

	public void addErShouHouseInfo(ErshouHouseInfo info){
		//校验数据是否已存在
		ErshouHouseInfo example =new ErshouHouseInfo();
		example.setHouseId(info.getHouseId());
		List<ErshouHouseInfo> ershouHouseInfos = ershouHousePriceMapper.select(example);
		if (CollectionUtils.isEmpty(ershouHouseInfos)){
			ershouHousePriceMapper.insert(info);
		}
	}
}
