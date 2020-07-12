package com.vmall;

/*
 * @ClassName: EurekaApplication
 * @Description:
 * @Author: Se7en
 * @Date: 2020/7/12 4:10
 * @Version: 1.0
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {

    /*
    * @Description: Load the startup class as the current configuration standard for springboot
    * @Param: [args]
    * @return: void
    * @Author: Se7en
    * @Date: 2020/7/12
    */
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class, args);
    }
}
