package com.ming.page.bean;

public class DataBean {
    public String content;
    public int id;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "content='" + content + '\'' +
                ", id=" + id +
                '}';
    }
}
