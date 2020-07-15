package com.ray.spiderprice.service;

import com.github.pagehelper.PageHelper;
import com.ray.spiderprice.domain.po.ErshouHouseInfo;
import com.ray.spiderprice.domain.po.NewHouseInfo;
import com.ray.spiderprice.domain.query.NewHouseInfoQuery;
import com.ray.spiderprice.mapper.ErshouHousePriceMapper;
import com.ray.spiderprice.mapper.NewHousePriceMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Package:com.ray.spiderprice.service
 * *Author:ray
 * *version:...
 * *Created in 2020/1/27  16:54
 **/
@Service
public class NewHousePriceService {

	@Autowired
	private NewHousePriceMapper newHousePriceMapper;

	public void addErShouHouseInfo(NewHouseInfo info){
		////校验数据是否已存在
		//NewHouseInfo example =new NewHouseInfo();
		//example.setVillageId(info.getVillageId());
		//List<NewHouseInfo> ershouHouseInfos = newHousePriceMapper.select(example);
		//if (CollectionUtils.isEmpty(ershouHouseInfos)){
			newHousePriceMapper.insert(info);
		//}
	}



	public void listNewHouseInfo(NewHouseInfoQuery info) {
		PageHelper.startPage(info.getPageIndex(),info.getPageSize());
		int i = newHousePriceMapper.countNum();
		System.out.println(i);
	}
}
