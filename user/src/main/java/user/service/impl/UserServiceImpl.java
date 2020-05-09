package user.service.impl;

import user.mapper.UserMapper;
import user.po.User;
import user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Intellij IDEA.
 *
 * @author 22510
 * @create 2019/11/3 17:31
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    /**
     * 登录
     * @param tel
     * @param pwdmd5
     * @return
     */
    @Override
    public User login(String tel,String pwdmd5) {
        return userMapper.getUser(tel,pwdmd5);
    }

    /**
     * 添加token
     * @param token token
     * @param uuid 用户ID
     */
    @Override
    public void setToken(String token,String uuid) {
        userMapper.setToken(token, uuid);
    }

    /**
     * 注册
     * @param user
     */
    @Override
    public void addUser(User user) {
        userMapper.addUser(user.getUuid(),user.getAuthority(),user.getTel(),user.getPwdmd5(),user.geteMail(),user.getUsername(),user.getSex(),user.getDob(),user.getNickName(),user.getHome());
    }

    /**
     * 获取好友类型
     * @param friendid
     * @return
     */
    @Override
    public List<Integer> getFirendType(String friendid) {
        return userMapper.getFirendType(friendid);
    }

    /**
     * 查找好友
     * @param uuid
     * @return
     */
    @Override
    public List<User> getFriend(String uuid) {
        return userMapper.getFirendInfo(uuid);
    }

    /**
     * 添加好友
     * @param userid
     * @param touserid
     * @param friendtype
     * @param remarks
     * @param add
     * @param addtime
     */
    @Override
    public void addFriend(String userid, String touserid, int friendtype, String remarks, String add, Date addtime) {
        
    }

    /**
     * 同意好友申请
     * @param userid
     * @param touserid
     * @param addtime
     */
    @Override
    public void addAccept(String userid, String touserid, Date addtime) {

    }

    /**
     * 测试方法
     */
    @Override
    public void test(){

    }
}