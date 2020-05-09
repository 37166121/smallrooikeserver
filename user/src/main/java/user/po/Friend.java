package user.po;

public class Friend {
    //    好友信息
    private User user;
    //    好友类型 0：普通 1：屏蔽 2：拉黑
    private int friendtype;
    //    备注
    private String remarks;

    public User getUserBean() {
        return user;
    }

    public void setUserBean(User user) {
        this.user = user;
    }

    public int getFriendtype() {
        return friendtype;
    }

    public void setFriendtype(int friendtype) {
        this.friendtype = friendtype;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}
