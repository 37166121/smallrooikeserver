package user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Intellij IDEA.
 *
 * @author 22510
 * @create 2020/4/17 15:27 星期五
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
@EnableCircuitBreaker
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class,args);
    }
}
