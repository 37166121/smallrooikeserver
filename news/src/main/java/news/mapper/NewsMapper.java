package news.mapper;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import news.po.News;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by Intellij IDEA.
 *
 * @author 22510
 * @create 2020/4/15 23:49 星期三
 */
@Mapper
public interface NewsMapper {
    /**
     * 获取物流记录
     * @param uuid 用户id
     * @return News
     */
    @Select("SELECT * FROM t_news WHERE userid=#{uuid};")
    List<News> getAllNews(@Param("uuid") String uuid);

    @Select("SELECT * FROM t_news WHERE orderid=#{orderid};")
    List<News> getNews(@Param("orderid") String orderid);

    /**
     * 设置物流状态
     * @param statu 状态
     */
    @Update("UPDATE t_news SET statu=#{statu} WHERE userid=#{uuid};")
    void setStatu(@Param("statu") String statu,@Param("uuid") String uuid);

    /**
     * 设置快递收货地址
     * @param address 地址
     */
    @Update("UPDATE t_news SET address=#{address} WHERE userid=#{uuid};")
    void setAddress(@Param("address") String address,@Param("uuid") String uuid);

    /**
     * 更新物流进度
     * @param recording 记录
     */
    @Update("UPDATE t_news SET history=#{recording} WHERE userid=#{uuid};")
    void addHistory(@Param("recording") JSONArray recording,@Param("uuid") String uuid);
}
