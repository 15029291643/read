package com.example.common.network;

public class JsonRootBean<T> {

    private int state;
    private String message;
    private T data;
    public void setState(int state) {
        this.state = state;
    }
    public int getState() {
        return state;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}