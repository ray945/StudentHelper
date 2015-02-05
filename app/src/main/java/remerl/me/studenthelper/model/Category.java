package remerl.me.studenthelper.model;

/**
 * Created by qiugang on 2014/11/12.
 */
public enum Category {

    Home("主页"), TodoList("待办事项"), ClassInformation("班级通知"),MessageBoard("留言板"),Settings("个人信息");
    private String mDisplayName;

    Category(String displayName) {
        mDisplayName = displayName;
    }

    public String getDisplayName() {
        return mDisplayName;
    }
}
