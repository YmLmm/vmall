package com.vmall.controller;

import com.github.pagehelper.PageInfo;
import com.mysql.cj.x.protobuf.Mysqlx;
import com.vmall.goods.pojo.Brand;
import com.vmall.service.BrandService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * @ClassName: BrandController
 * @Description:
 * @Author: Se7en
 * @Date: 2020/7/13 18:16
 * @Version: 1.0
 */
@RestController
@RequestMapping(value = "/brand")
@CrossOrigin //跨域: A域名访问B域名的数据
public class BrandController {

    @Autowired
    private BrandService brandService;

    /***
     * 条件分页查询实现
     */
    @PostMapping(value = "/search/{page}/{size}")
    public Result<PageInfo<Brand>> findPage(@RequestBody Brand brand,
                                            @PathVariable(value = "page")Integer page,
                                            @PathVariable(value = "size")Integer size){
        PageInfo<Brand> pageInfo = brandService.findPage(brand, page, size);

        return new Result<PageInfo<Brand>>(true, StatusCode.OK, "条件分页查询成功", pageInfo);
    }

    /***
     * 分页查询实现
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageInfo<Brand>> findPage(@PathVariable(value = "page")Integer page,
                                            @PathVariable(value = "size")Integer size){
        int q = 10 / 0;
        PageInfo<Brand> pageInfo = brandService.findPage(page, size);

        return new Result<PageInfo<Brand>>(true, StatusCode.OK, "分页查询成功", pageInfo);
    }

    /***
     * 条件查询
     */
    @PostMapping(value = "/search")
    public Result<List<Brand>> findList(@RequestBody Brand brand){
        List<Brand> brands = brandService.findList(brand);

        return new Result<List<Brand>>(true, StatusCode.OK, "条件搜索查询", brands);
    }

    /***
     * 根据ID删除信息
     */
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable(value = "id") Integer id) {
        brandService.delete(id);

        return new Result(true, StatusCode.OK, "删除成功");
    }

    /***
     * 品牌修改实现
     */
    @PutMapping(value = "/{id}")
    public Result update(@PathVariable(value = "id")Integer id, @RequestBody Brand brand) {
        brand.setId(id);
        //调用Service实现修改
        brandService.update(brand);

        return new Result(true, StatusCode.OK, "修改成功");
    }

    /***
     * 增加品牌
     */
    @PostMapping
    public Result add(@RequestBody Brand brand) {
        brandService.add(brand);

        return new Result(true, StatusCode.OK, "增加Brand成功");
    }

    /***
     * 根据主键ID查询
     */
    @GetMapping(value = "/{id}")
    public Result<Brand> findById(@PathVariable(value = "id")Integer id ) {
        Brand brand = brandService.findById(id);

        return new Result<Brand>(true, StatusCode.OK, "根据ID查询Brand成功", brand);
    }

    /***
     * 查询所有品牌
     */
    @GetMapping
    public Result<List<Brand>> findAll() {
        List<Brand> brands = brandService.findAll();

        return new Result<List<Brand>>(true, StatusCode.OK, "查询品牌集合成功!", brands);
    }
}
