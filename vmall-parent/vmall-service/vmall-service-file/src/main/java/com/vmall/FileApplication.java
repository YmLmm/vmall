package com.vmall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/*
 * @ClassName: FileApplication
 * @Description:
 * @Author: Se7en
 * @Date: 2020/7/14 4:26
 * @Version: 1.0
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)//排除数据库自动加载
@EnableEurekaClient
public class FileApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileApplication.class);
    }
}
