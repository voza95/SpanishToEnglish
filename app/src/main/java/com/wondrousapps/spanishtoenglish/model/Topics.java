package com.wondrousapps.spanishtoenglish.model;

public class Topics {
    private int id;
    private String topic;

    public Topics(int id, String topic) {
        this.id = id;
        this.topic = topic;
    }

    public Topics(String topic) {
        this.topic = topic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
