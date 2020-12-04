package com.alpha.framework.bmob;

import cn.bmob.v3.BmobObject;

public class MyData extends BmobObject {

    private String name;
    // 性别 0：男孩  1：女孩
    private int sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
