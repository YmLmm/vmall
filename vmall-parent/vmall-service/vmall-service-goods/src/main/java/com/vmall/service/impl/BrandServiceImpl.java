package com.vmall.service.impl;
/*
 * @ClassName: BrandServiceImpl
 * @Description:
 * @Author: Se7en
 * @Date: 2020/7/13 18:13
 * @Version: 1.0
 */

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vmall.dao.BrandMapper;
import com.vmall.goods.pojo.Brand;
import com.vmall.service.BrandService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    /***
     * 分页+条件搜索
     * @param brand:搜索条件
     * @param page:当前页
     * @param size:每页显示的条数
     * @return
     */
    @Override
    public PageInfo<Brand> findPage(Brand brand, Integer page, Integer size) {
        //分页
        PageHelper.startPage(page, size);
        //搜索数据 name不为空，根据名字模糊搜索，letter不为空，根据letter搜索
        Example example = createExample(brand);
        List<Brand> brands = brandMapper.selectByExample(example);
        //封装
        return new PageInfo<Brand>(brands);
    }

    /***
     * 分页查询
     * @param page:当前页
     * @param size:每页显示的条数
     * @return
     */
    @Override
    public PageInfo<Brand> findPage(Integer page, Integer size) {
        /***
         * 分页实现
         * 1：当前页
         * 2：每页显示多少条
         */
        PageHelper.startPage(page, size);

        //查询集合
        List<Brand> brands = brandMapper.selectAll();

        //封装pageinfo
        return new PageInfo<Brand>(brands);
    }

    /***
     * 多条件搜索
     * @param brand
     * @return
     */
    @Override
    public List<Brand> findList(Brand brand) {
        //调用方法构建条件
        Example example = createExample(brand);
        return brandMapper.selectByExample(example);
    }

    /***
     * 条件构建
     * @param brand
     * @return
     */
    public Example createExample(Brand brand){
        //自定义条件搜索对象Example   是tk的example
        //前两行是固定写法，只需要修改example变量
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();//条件构造器

        if (brand != null) {
            //brand.name!=null根据名字模糊搜索
            if (!StringUtils.isEmpty(brand.getName())) {
                criteria.andLike("name", "%" + brand.getName() + "%");
            }
            //brand.letter!=null根据首字母模糊搜索
            if (!StringUtils.isEmpty(brand.getLetter())){
                criteria.andEqualTo("letter", brand.getLetter());
            }
        }

        return example;
    }

    /***
     * 根据ID删除信息
     * @param id
     */
    @Override
    public void delete(Integer id) {
        //Mapper.delete()
        brandMapper.deleteByPrimaryKey(id);
    }

    /***
     * 根据ID修改品牌
     * @param brand
     */
    @Override
    public void update(Brand brand) {
        //Mapper.update()
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    /***
     * 增加品牌
     * @param brand
     */
    @Override
    public void add(Brand brand) {
        //Mapper.insertSelective()
        //方法中但凡带有selective, 会忽略空值（同一行）
        brandMapper.insertSelective(brand);
    }

    /***
     * 根据ID 查询
     * @param id
     * @return
     */
    @Override
    public Brand findById(Integer id) {
        //Mapper
        return brandMapper.selectByPrimaryKey(id);
    }

    /***
     * 查询所有
     * @return
     */
    @Override
    public List<Brand> findAll() {
        //Mapper.selectAll()
        return brandMapper.selectAll();
    }
}
