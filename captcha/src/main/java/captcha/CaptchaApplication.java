package captcha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by Intellij IDEA.
 *
 * @author 22510
 * @create 2019/11/5 18:23
 */
@SpringBootApplication
@EnableEurekaClient
public class CaptchaApplication {
    public static void main(String[] args) {
        SpringApplication.run(CaptchaApplication.class,args);
    }
}
