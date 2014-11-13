package remerl.me.studenthelper.dao;

/**
 * Created by qiugang on 2014/11/12.
 */
public enum Category {

    Home("主页"), Personal("个人"), MyClass("班级"),MessageBoard("留言板"),Settings("设置");
    private String mDisplayName;

    Category(String displayName) {
        mDisplayName = displayName;
    }

    public String getDisplayName() {
        return mDisplayName;
    }
}
