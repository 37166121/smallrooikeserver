package user.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import user.po.User;
import user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import user.util.Base64Util;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Intellij IDEA.
 *
 * @author 22510
 * @create 2019/11/3 17:34
 */
@RestController
public class UserController {
    //    失败
    public static final int FAILURE = 0;
    //    成功
    public static final int SUCCESS = 1;
    //    参数错误 Parameter error
    public static final int PARAMET_EREROR = 10;
    //    账号或密码错误 Incorrect account password
    public static final int IAP = 11;
    private User user = new User();
    @Resource
    protected HttpServletRequest request;
    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param tel 登录时验证的手机号码
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/getUser/{tel}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    private JSONObject findUserByTel(@PathVariable String tel,HttpServletRequest request, HttpServletResponse response) throws IOException{
        String json = null;
        User user;
        JSONObject jsonObject;
        try{
            InputStreamReader reader = new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8);
            char [] buff=new char[1024];
            int length = 0;
            while((length=reader.read(buff)) != -1){
                json = new String(buff,0,length);
            }
            jsonObject = JSONObject.parseObject(json);
            System.out.println(jsonObject);
            user = this.userService.login(jsonObject.getString("tel"),jsonObject.getString("pwdmd5"));
            if (user != null){
                user.setPwdmd5("");
                jsonObject = (JSONObject) JSON.parse(JSON.toJSONString(user));
                jsonObject.put("msg",SUCCESS);
            } else {
                jsonObject = new JSONObject();
                jsonObject.put("msg",IAP);
                return jsonObject;
            }
        } catch (NullPointerException e){
            jsonObject = new JSONObject();
            jsonObject.put("msg", PARAMET_EREROR);
        }
        return jsonObject;
    }
    /**
     * 注册
     * @throws IOException
     */
    @RequestMapping(value = "/addUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    private JSONObject addUser(HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        try{
            String str = null;
            InputStreamReader reader = new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8);
            char [] buff=new char[1024];
            int length = 0;
            while((length=reader.read(buff)) != -1){
                str = new String(buff,0,length);
            }
            jsonObject = JSONObject.parseObject(str);
            System.out.println(jsonObject);
            user = JSON.parseObject(jsonObject.toJSONString(), new TypeReference<User>(){});
            String uuid = UUID.randomUUID().toString();
            user.setUuid(uuid);
            user.setAuthority(1);
            this.userService.addUser(user);
            jsonObject = new JSONObject();
            jsonObject.put("msg",SUCCESS);
        } catch (IOException e) {
            e.printStackTrace();
            jsonObject = new JSONObject();
            jsonObject.put("msg",FAILURE);
        }
        return jsonObject;
    }
    /**
     * 获取好友列表
     * @param uuid 用户uuid
     * @return
     */
    @RequestMapping(value = "/getFriend/{uuid}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    private JSONObject getFriend(@PathVariable String uuid){
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        List<User> userList = userService.getFriend(uuid);
        List<Integer> friendTypeList = userService.getFirendType(uuid);
        for (int i = 0 ; i < userList.size() ; i++) {
            user = userList.get(i);
            user.setPwdmd5("");
            userList.set(i,user);
        }
        jsonArray = JSONArray.parseArray(JSON.toJSONString(userList));
        jsonObject.put("friends",jsonArray);
        return jsonObject;
    }
    /**
     * 注销
     * @param token
     */
    @RequestMapping(value = "/logout/{token}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    private JSONObject logout(@PathVariable String token){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg",FAILURE);
        return jsonObject;
    }
    /**
     * 添加头像
     */
    @RequestMapping(value = "/setAvatar/{tel}")
    private JSONObject addAvatar(@PathVariable String tel,HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        byte[] bytes;
        return jsonObject;
    }

    @RequestMapping(value = "/addFriend/{uuid}/{totel}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    private JSONObject addFriend(HttpServletRequest request, HttpServletResponse response){
        String json = null;
        List<User> users = new ArrayList<>();
        JSONObject jsonObject;
        try{
            InputStreamReader reader = new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8);
            char [] buff=new char[1024];
            int length = 0;
            while((length=reader.read(buff)) != -1){
                json = new String(buff,0,length);
            }
            jsonObject = JSONObject.parseObject(json);
            System.out.println(jsonObject);
            user = this.userService.login(jsonObject.getString("tel"),jsonObject.getString("pwdmd5"));
            if (user != null){
                user.setPwdmd5("");
                jsonObject = (JSONObject) JSON.parse(JSON.toJSONString(user));
                jsonObject.put("msg",SUCCESS);
            } else {
                jsonObject = new JSONObject();
                jsonObject.put("msg",IAP);
                return jsonObject;
            }
        } catch (NullPointerException | IOException e){
            jsonObject = new JSONObject();
            jsonObject.put("msg", PARAMET_EREROR);
        }
        return jsonObject;
    }
}
