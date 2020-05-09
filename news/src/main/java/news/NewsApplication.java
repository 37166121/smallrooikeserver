package news;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by Intellij IDEA.
 *
 * @author 22510
 * @create 2020/4/15 20:19 星期三
 */
@EnableEurekaClient
@SpringBootApplication
public class NewsApplication {
    public static void main(String[] args) {
        SpringApplication.run(NewsApplication.class,args);
    }
}
