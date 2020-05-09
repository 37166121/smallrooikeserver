package news.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import news.mapper.NewsMapper;
import news.po.News;
import news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Intellij IDEA.
 *
 * @author 22510
 * @create 2020/4/15 23:27 星期三
 */
@Service
@Transactional
public class NewsServiceImpl implements NewsService {
    @Autowired
    NewsMapper newsMapper;

    @Override
    public List<News> getAllNews(String uuid) {
        return newsMapper.getAllNews(uuid);
    }

    @Override
    public List<News> getNews(String orderid) {
        return newsMapper.getNews(orderid);
    }

    @Override
    public void setStatu(String statu,String uuid) {
        newsMapper.setStatu(statu,uuid);
    }

    @Override
    public void setAddress(String address,String uuid) {
        newsMapper.setAddress(address,uuid);
    }

    @Override
    public void addHistory(JSONArray recording, String uuid) {
        newsMapper.addHistory(recording, uuid);
    }
}
