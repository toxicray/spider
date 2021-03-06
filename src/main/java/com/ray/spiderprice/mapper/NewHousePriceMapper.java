package com.ray.spiderprice.mapper;

import com.ray.spiderprice.domain.po.ErshouHouseInfo;
import com.ray.spiderprice.domain.po.NewHouseInfo;
import com.ray.spiderprice.util.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * Package:com.ray.spiderprice.mapper
 * *Author:ray
 * *version:...
 * *Created in 2020/1/25  19:24
 **/
@Repository
public interface NewHousePriceMapper extends BaseMapper<NewHouseInfo>{

	//获取数据总量
	int countNum();
}
