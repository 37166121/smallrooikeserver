package user.util;

/**
 * Created by Intellij IDEA.
 * 发送手机短信验证码
 * @author 22510
 * @create 2019/11/5 20:25
 */
public class Sendsms {
    private static String Url = "http://106.ihuyi.com/webservice/sms.php?method=Submit";
//    public void sms(String tel) {
//
//        HttpClient client = new HttpClient();
//        PostMethod method = new PostMethod(Url);
//
//        client.getParams().setContentCharset("GBK");
//        method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=GBK");
//
//        int mobileCode = (int)((Math.random()*9+1)*100000);
//
//        String content = new String("您的验证码是：" + mobileCode + "。请不要把验证码泄露给其他人。");
//
//        NameValuePair[] data = {//提交短信
//                new NameValuePair("account", "C84135290"),
//                new NameValuePair("password", "2aae801388d38256014849b8f974b561"),
//                //new NameValuePair("password", util.StringUtil.MD5Encode("密码")),
//                new NameValuePair("mobile", tel),
//                new NameValuePair("content", content),
//        };
//        method.setRequestBody(Arrays.toString(data));
//
//        try {
//            client.executeMethod(method);
//
//            String SubmitResult = method.getResponseBodyAsString();
//
//            //System.out.println(SubmitResult);
//
//            Document doc = DocumentHelper.parseText(SubmitResult);
//            Element root = doc.getRootElement();
//
//            String code = root.elementText("code");
//            String msg = root.elementText("msg");
//            String smsid = root.elementText("smsid");
//
//            System.out.println(code);
//            System.out.println(msg);
//            System.out.println(smsid);
//
//            if("2".equals(code)){
//                System.out.println("短信提交成功");
//            }
//        } catch (IOException | DocumentException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
}
