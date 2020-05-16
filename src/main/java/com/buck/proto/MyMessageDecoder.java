package com.buck.proto;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * @program: netty-test
 * @description: 自定义消息解码器
 * @author: buck
 * @create: 2020-03-21 15:30
 **/
public class MyMessageDecoder extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("调用decode方法");
        int len = in.readInt();
        byte[] bytes = new byte[len];
        in.readBytes(bytes);
        ProtoMessage protoMessage = new ProtoMessage();
        protoMessage.setLen(len);
        protoMessage.setMessage(bytes);
        out.add(protoMessage);
    }
}
