package com.example.viewpager;

public class Msg {
    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SEND = 1;
    private String content;
    private int type;
    //content表示消息的内容
    //type表示消息的类型，TYPE_RECEIVED表示收到的消息，TYPE_SEND表示发出的消息
    public Msg(String content,int type){
        this.content = content;
        this.type = type;
    }

    public String getContent(){
        return content;
    }

    public int getType(){
        return type;
    }
}
