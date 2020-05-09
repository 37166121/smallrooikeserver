package user.service;

import user.po.User;

import java.util.Date;
import java.util.List;

/**
 * Created by Intellij IDEA.
 *
 * @author 22510
 * @create 2019/11/3 17:25
 */
public interface UserService {
    public User login(String tel,String pwd);
    public void setToken(String token,String uuid);
    public void addUser(User user);
    public List<User> getFriend(String uuid);
    public void addFriend(String userid, String touserid, int friendtype, String remarks, String add, Date addtime);
    public void addAccept(String userid, String touserid,Date addtime);
    public List<Integer> getFirendType(String friendid);
    public void test();
}