package com.hirath.urbanbean;

public abstract class Message {
    private Object bean;
    private String tag;
    private String description;

    public Message(Object bean, String tag, String description) {
        this.bean = bean;
        this.tag = tag;
        this.description = description;
    }

    public Object getBean() {
        return bean;
    }

    public String getTag() {
        return tag;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "bean : " + bean + ", tag: " + tag + ", description; " + description;
    }
}
