package captcha.controller;

import captcha.mapper.CaptchaMapper;
import captcha.service.CaptchaService;
import captcha.util.CaptchaUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.google.gson.internal.$Gson$Preconditions;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.mail.internet.MimeBodyPart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Random;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import java.util.*;
import com.aliyuncs.dm.model.v20151123.*;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.http.MethodType;

/**
 * Created by Intellij IDEA.
 *
 * @author 22510
 * @create 2019/11/5 18:24
 */
@RestController
public class CaptchaController {
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
    @Autowired
    CaptchaService captchaService;
    private static final String AK = "LTAIzevc7Glik96o";
    private static final String AS = "L8gfsjCgoUQU75yxK1cD0w8EggbfFN";

    public Map<String,String> getMap(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        Map<String,String> map = new HashMap<>();
        if (session.getAttribute("map") != null){
            map = (HashMap<String,String>)session.getAttribute("map");
        }
        return map;
    }
    public void setMap(Map<String,String> map, HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        session.setAttribute("map", map);
        System.out.println("map.size()：" + map.size());
    }

    /**
     * 用于生成带四位数字验证码的图片
     */
    @RequestMapping(value="/image")
    public void imagecode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //验证码不能缓存
        response.setHeader("Expires", "-1");
        response.setHeader("cache-control", "no-cahce");
        response.setHeader("pragma", "no-cache");
        Map<String,String> map = getMap(request, response);
        CaptchaUtil vcg = new CaptchaUtil();
        //获取验证码
        String vcode = vcg.generatorVCode();
        map.put("image",vcode);
        setMap(map,request,response);
        System.out.println(map.get("image"));
        //获取验证码图片
        BufferedImage vcodeImage = vcg.generatorVCodeImage(vcode, true);
        //将验证码保存到session域对象
        request.getSession().setAttribute("map", map);
        //输出验证码图片
        ImageIO.write(vcodeImage, "jpeg", response.getOutputStream());
    }

    /**
     * 手机短信验证码
     */
    @RequestMapping(value="/sendSms/{phoneNumbers}")
    public JSONObject sendSms(@PathVariable String phoneNumbers,HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        if (captchaService.isTelPresence(phoneNumbers)){
            SendSms sendSms = new SendSms(phoneNumbers,getMap(request, response),request,response);
            System.out.println(phoneNumbers);
            sendSms.sendSms();
            jsonObject.put("msg",HBS);
            return jsonObject;
        }
        jsonObject.put("msg",PNAE);
        return jsonObject;
    }
    public static class SendSms {
        private String phoneNumbers;
        private Map<String,String> map = new HashMap<>();
        private HttpServletRequest request;
        private HttpServletResponse response;
        SendSms(String phoneNumbers,Map<String,String> map,HttpServletRequest request, HttpServletResponse response){
            this.phoneNumbers = phoneNumbers;
            this.map = map;
            this.request = request;
            this.response = response;
        }
        public void sendSms() {
            DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", AK, AS);
            IAcsClient client = new DefaultAcsClient(profile);
            CommonRequest request = new CommonRequest();
            String sendSms = String.format("%04d",new Random().nextInt(9999));
            request.setMethod(MethodType.POST);
            request.setDomain("dysmsapi.aliyuncs.com");
            request.setVersion("2017-05-25");
            request.setAction("SendSms");
            request.putQueryParameter("RegionId", "cn-hangzhou");
            request.putQueryParameter("PhoneNumbers", phoneNumbers);
            request.putQueryParameter("SignName", "小菜鸟");
            request.putQueryParameter("TemplateCode", "SMS_187939841");
            request.putQueryParameter("TemplateParam", "{\"code\":\"" + sendSms + "\"}");
//            session.setAttribute("yzmcode", sendSms);
//            session.setAttribute("type","sendsms");
            map.put("sendsms",sendSms);
            new CaptchaController().setMap(map,this.request,response);
            System.out.println(map.get("sendsms"));
//            System.out.println(session.getAttribute("map").toString().toLowerCase());
            try {
                CommonResponse response = client.getCommonResponse(request);
                System.out.println(response.getData());
            } catch (ClientException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 邮箱验证码
     * @param emailaddress 收件地址
     */
    @RequestMapping(value="/sendMail/{emailaddress}")
    public void sendEmail(@PathVariable String emailaddress,HttpServletRequest request, HttpServletResponse response) {
        SingleSendMail sampleMail = new SingleSendMail(emailaddress, request, response);
        System.out.println(emailaddress);
        sampleMail.singleSendMail();
    }
    public static class SingleSendMail {
        private HttpServletRequest request;
        private HttpServletResponse response;
        private String emailaddress;
        SingleSendMail(String emailaddress,HttpServletRequest request, HttpServletResponse response){
            this.emailaddress = emailaddress;
            this.request = request;
            this.response = response;
        }
        public void singleSendMail() {
            HttpSession session = request.getSession();
            Map<String,String> map = new HashMap<>();
            map = (HashMap<String,String>)session.getAttribute("map");
            String sendMail = String.format("%04d",new Random().nextInt(9999));
            DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", AK, AS);
            IAcsClient client = new DefaultAcsClient(profile);
            SingleSendMailRequest request = new SingleSendMailRequest();
            request.setRegionId("cn-hangzhou");
            request.setAccountName("xcl@aliyunm.top");
            request.setAddressType(1);
            request.setReplyToAddress(false);
            request.setToAddress(emailaddress);
            request.setSubject("验证码");
            request.setTextBody(sendMail);
            session.setAttribute("yzmcode", sendMail);
            session.setAttribute("type","sendMail");
            System.out.println(session.getAttribute("yzmcode").toString().toLowerCase());
            try {
                SingleSendMailResponse response = client.getAcsResponse(request);
                System.out.println(new Gson().toJson(response));
            } catch (ServerException e) {
                e.printStackTrace();
            } catch (ClientException e) {
                System.out.println("ErrCode:" + e.getErrCode());
                System.out.println("ErrMsg:" + e.getErrMsg());
                System.out.println("RequestId:" + e.getRequestId());
            }
        }
    }

    /**
     * 验证APP发送的验证码是否正确
     * @param code 接收APP验证码
     * @param request
     * @param response
     * @return boolean
     */
    @RequestMapping(value="/isCaptcha/{type}/{code}")
    public JSONObject isCaptcha(@PathVariable String code, @PathVariable String type, HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        Map<String,String> map = getMap(request, response);
        if (map.size() == 0){
            jsonObject.put("msg", FAILURE);
            return jsonObject;
        }
        switch (type){
            case "image":
                jsonObject = isCode(map.get(type).toUpperCase(),code.toUpperCase(),type);
                break;
            case "sendsms":
                jsonObject = isCode(map.get(type).toUpperCase(),code.toUpperCase(),type);
                break;
            case "sendmail":
                jsonObject = isCode(map.get(type).toUpperCase(),code.toUpperCase(),type);
                break;
            default:break;
        }
        return jsonObject;
    }
    private JSONObject isCode(String oldCode, String code, String type){
        JSONObject jsonObject = new JSONObject();
        if (!oldCode.equals(code)){
            jsonObject.put("msg", FAILURE);
            return jsonObject;
        }
        jsonObject.put("type",type);
        jsonObject.put("msg", SUCCESS);
        return jsonObject;
    }
}