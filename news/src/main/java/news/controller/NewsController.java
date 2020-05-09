package news.controller;

import news.mapper.NewsMapper;
import news.po.News;
import news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Intellij IDEA.
 *
 * @author 22510
 * @create 2020/4/16 1:07 星期四
 */
@RestController
public class NewsController {
    @Autowired
    NewsService newsService;
    @RequestMapping(value = "/getAllNews/{uuid}")
    private List<News> getAllNews(@PathVariable String uuid){
        return newsService.getAllNews(uuid);
    }
    @RequestMapping(value = "/getNews/{orderid}")
    private List<News> getNews(@PathVariable String orderid){
        return newsService.getNews(orderid);
    }
}
