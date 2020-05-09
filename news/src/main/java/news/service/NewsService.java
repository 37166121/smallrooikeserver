package news.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import news.po.News;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Intellij IDEA.
 *
 * @author 22510
 * @create 2020/4/15 21:08 星期三
 */
public interface NewsService {
//    获取用户所有快递信息
    public List<News> getAllNews(String uuid);
//    通过订单号获取快递信息
    public List<News> getNews(String orderid);
//    更改签收状态
    public void setStatu(String statu,String uuid);
//    更改签收地址
    public void setAddress(String address,String uuid);
//    更改历史记录(添加)
    public void addHistory(JSONArray recording, String uuid);
}
