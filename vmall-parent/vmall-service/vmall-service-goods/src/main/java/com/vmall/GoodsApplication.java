package com.vmall;
/*
 * @ClassName: GoodsApplication
 * @Description:
 * @Author: Se7en
 * @Date: 2020/7/13 17:42
 * @Version: 1.0
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableEurekaClient
/*
 开启通用Mapper包扫描
 注解包为 tk.mybatis.spring.annotation.MapperScan
 */
@MapperScan(basePackages = {"com.vmall.dao"})
public class GoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class, args);
    }
}
