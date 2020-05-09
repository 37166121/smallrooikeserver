package news.po;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Base64;

/**
 * Created by Intellij IDEA.
 *
 * @author 22510
 * @create 2020/4/15 20:38 星期三
 */
public class News {
//    取货码
    private String code;
//    快递单号
    private String orderid;
//    用户ID
    private String userid;
//    物品名称
    private String newsname;
//    物品数量
    private int quantity;
//    签收状态
    private String statu;
//    购买平台
    private String platform;
//    到达地址
    private String address;
//    历史记录
    private JSONArray history;
//    物品图片
    private Base64 image;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getNewsname() {
        return newsname;
    }

    public void setNewsname(String newsname) {
        this.newsname = newsname;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public JSONArray getHistory() {
        return history;
    }

    public void setHistory(JSONArray history) {
        this.history = history;
    }

    public Base64 getImage() {
        return image;
    }

    public void setImage(Base64 image) {
        this.image = image;
    }
}
