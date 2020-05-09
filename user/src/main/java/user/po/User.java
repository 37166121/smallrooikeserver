package user.po;

import java.util.Base64;
import java.util.Date;

/**
 * Created by Intellij IDEA.
 *
 * @author 22510
 * @create 2019/11/3 16:29
 */

public class User {
//    权限
    private int authority;
//    登录令牌
    private String token;
//    uuid
    private String uuid;
//    头像
    private Base64 avatar;
//    昵称
    private String nickName;
//    密码转md5
    private String pwdmd5;
//    邮箱
    private String eMail;
//    tel
    private String tel;
//    姓名
    private String username;
//    性别
    private String sex;
//    出生日期
    private Date dob;
//    家庭住址
    private String home;
//    上次登录时间戳
    private String lastlogin;
//    上次登录ip
    private String lastip;

    @Override
    public String toString() {
        return "User{" +
                "token='" + token + '\'' +
                ", uuid='" + uuid + '\'' +
                ", nickName='" + nickName + '\'' +
                ", pwdmd5='" + pwdmd5 + '\'' +
                ", eMail='" + eMail + '\'' +
                ", tel='" + tel + '\'' +
                ", username='" + username + '\'' +
                ", sex='" + sex + '\'' +
                ", dob=" + dob +
                ", home='" + home + '\'' +
                '}';
    }

    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }

    public String getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(String lastlogin) {
        this.lastlogin = lastlogin;
    }

    public String getLastip() {
        return lastip;
    }

    public void setLastip(String lastip) {
        this.lastip = lastip;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Base64 getAvatar() {
        return avatar;
    }

    public void setAvatar(Base64 avatar) {
        this.avatar = avatar;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPwdmd5() {
        return pwdmd5;
    }

    public void setPwdmd5(String pwdmd5) {
        this.pwdmd5 = pwdmd5;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }
}
