package com.buck.proto;

/**
 * @program: netty-test
 * @description: 消息体
 * @author: buck
 * @create: 2020-03-21 15:31
 **/
public class ProtoMessage {

    private int len;

    private byte[] message;

    public byte[] getMessage() {
        return message;
    }

    public  void setMessage(byte[] message) {
        this.message = message;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }
}
