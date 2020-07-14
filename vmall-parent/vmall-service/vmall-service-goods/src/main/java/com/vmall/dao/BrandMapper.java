package com.vmall.dao;
/*
 * @ClassName: BrandMapper
 * @Description: Interface, 继承了mapper接口就自动实现了增删改查方法
 * 增加数据： 调用Mapper.insert()
 *              Mapper.insertSelective()
 * 修改数据： 调用Mapper.update(T)
 *              Mapper.updateByPrimayKey(T)
 * 查询数据： 调用Mapper.selectByPrimaryKey(ID)
 *              Mapper.select(T)
 * @Author: Se7en
 * @Date: 2020/7/13 18:29
 * @Version: 1.0
 */

import com.vmall.goods.pojo.Brand;
import tk.mybatis.mapper.common.Mapper;

public interface BrandMapper extends Mapper<Brand> {

}
