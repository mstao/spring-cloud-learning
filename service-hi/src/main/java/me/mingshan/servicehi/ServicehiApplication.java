package me.mingshan.servicehi;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@RestController
@EnableHystrix
@EnableHystrixDashboard
@EnableCircuitBreaker
public class ServicehiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicehiApplication.class, args);
    }

    @Value("${server.port}")
    String port;

    /**
     * 访问地址 http://localhost:8763/hystrix
     *  然后输入：http://localhost:8763/hystrix.stream
     */
    @RequestMapping("/hi")
    @HystrixCommand(fallbackMethod = "hiError")
    public String hi(@RequestParam(value = "name", required = true, defaultValue = "walker") String name) {
        return port + " - " + name;
    }

    public String hiError(String name) {
        return "hi," + name + ",sorry,error!";
    }

}
