package captcha.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by Intellij IDEA.
 *
 * @author 22510
 * @create 2019/11/12 11:51
 */
@Mapper
public interface CaptchaMapper {
    @Select("SELECT tel FROM t_user WHERE tel = #{tel}")
    String isTelPresence(@Param("tel") String tel);
}
