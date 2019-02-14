package org.jasonhww.customviewdemo;

import java.io.Serializable;

public class MainViewBean implements Serializable {
    private String title;
    private Class activityClass;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Class getActivityClass() {
        return activityClass;
    }

    public void setActivityClass(Class activityClass) {
        this.activityClass = activityClass;
    }
}
