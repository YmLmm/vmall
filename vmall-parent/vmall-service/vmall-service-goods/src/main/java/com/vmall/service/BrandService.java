package com.vmall.service;
/*
 * @ClassName: BrandService
 * @Description: Interface
 * @Author: Se7en
 * @Date: 2020/7/13 18:14
 * @Version: 1.0
 */

import com.github.pagehelper.PageInfo;
import com.vmall.goods.pojo.Brand;

import java.util.List;

public interface BrandService {

    /***
     * 分页+条件搜索
     * @param brand:搜索条件
     * @param page:当前页
     * @param size:每页显示的条数
     */
    PageInfo<Brand> findPage(Brand brand, Integer page, Integer size);

    /***
     * 分页搜索
     * @param page:当前页
     * @param size:每页显示的条数
     */
    PageInfo<Brand> findPage(Integer page, Integer size);

    /***
     * 根据品牌信息多条件搜索
     */
    List<Brand> findList(Brand brand);

    /***
     * 根据ID删除数据
     */
    void delete(Integer id);

    /***
     * 根据ID 修改品牌数据
     */
    void update(Brand brand);

    /***
     * 增加品牌     *
     */
    void add(Brand brand);

    /***
     * 根据ID查询
     */
    Brand findById(Integer id);

    /***
     * 查询所有
     */
    List<Brand> findAll();
}
