package com.ray.spiderprice.util;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Package:com.ray.spiderprice.util
 * *Author:ray
 * *version:...
 * *Created in 2020/1/24  10:07
 **/
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
