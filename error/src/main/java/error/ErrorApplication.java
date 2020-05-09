package error;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 代码错误集
 * 401 需要身份验证
 * 403 不允许访问
 * 404 页面找不到
 * 405 访问方式错误
 * 500 服务器抛出异常
 * 504 服务器访问人数过多
 */
@SpringBootApplication
public class ErrorApplication {
//    失败
    public static final int FAILURE = 0;
//    成功
    public static final int SUCCESS = 1;
//    参数错误 Parameter error
    public static final int PARAMET_EREROR = 10;
//    账号或密码错误 Incorrect account password
    public static final int IAP = 11;
//    已发送 Has been sent
    public static final int HBS = 30;
//    手机号码已存在 Phone number already exists
    public static final int PNAE = 30;

    public static void main(String[] args) {
        SpringApplication.run(ErrorApplication.class,args);
    }
}
