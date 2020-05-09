package user.mapper;

import org.apache.ibatis.annotations.*;
import user.po.User;

import java.util.Date;
import java.util.List;

/**
 * Created by Intellij IDEA.
 *
 * @author 22510
 * @create 2019/11/3 17:29
 */
@Mapper
public interface UserMapper {
    /**
     * 查询用户
     * @param tel tel登录
     * @param pwdmd5 密码转MD5
     * @return User
     */
    @Select("SELECT * FROM t_user WHERE tel=#{tel} AND pwdmd5=#{pwdmd5};")
    User getUser(@Param("tel") String tel,@Param("pwdmd5") String pwdmd5);

    /**
     * 添加token
     * @param token 令牌
     * @param uuid 用户ID
     */
    @Update("UPDATE t_user SET token=#{token} where uuid=#{uuid}")
    void setToken(@Param("token") String token,@Param("uuid") String uuid);

    /**
     * 查找用户的好友
     * @param userid 用户ID
     * @return 返回用户好友uuid
     */
    @Select("select * from t_user where uuid in (select friendid from t_friend where userid=#{userid});")
    List<User> getFirendInfo(@Param("userid") String userid);

    /**
     * 好友类型
     * 0：普通
     * 1：屏蔽
     * 2：拉黑
     * @param userid 用户ID
     * @return 返回用户好友uuid
     */
    @Select("select friendtype from t_friend where userid=#{userid};")
    List<Integer> getFirendType(@Param("userid") String userid);

    /**
     * 添加好友
     * @param userid 用户ID
     * @param touserid 好友ID
     */
    @Insert("INSERT INTO t_friend value(#{userid},#{touserid},#{friendtype},#{remarks},#{add},#{addtime});")
    void addFriend(@Param("userid") String userid,@Param("touserid") String touserid,
                   @Param("friendtype") int friendtype,@Param("remarks") String remarks,
                   @Param("add") String add,@Param("addtime") Date addtime);

    /**
     * 添加用户
     * @param uuid UUID
     * @param tel 手机号码
     * @param pwdmd5 密码转MD5
     * @param email 邮箱（可为空）
     * @param username 姓名
     * @param sex 性别
     * @param dob 出生日期
     * @param nickname 昵称
     * @param home 家庭住址
     */
    @Insert("INSERT INTO t_user(uuid,authority,tel,pwdmd5,email,username,sex,dob,nickname,home) VALUE(" +
            "#{uuid}," +
            "#{authority}," +
            "#{tel}," +
            "#{pwdmd5}," +
            "#{email}," +
            "#{username}," +
            "#{sex}," +
            "#{dob}," +
            "#{nickname}," +
            "#{home});")
    void addUser(@Param("uuid") String uuid,
                 @Param("authority") int authority,
                 @Param("tel") String tel,
                 @Param("pwdmd5") String pwdmd5,
                 @Param("email") String email,
                 @Param("username") String username,
                 @Param("sex") String sex,
                 @Param("dob") Date dob,
                 @Param("nickname") String nickname,
                 @Param("home") String home);
}