package com.buck.proto;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @program: netty-test
 * @description: 自定义消息编码器
 * @author: buck
 * @create: 2020-03-21 15:46
 **/
public class MyMessageEncoder extends MessageToByteEncoder<ProtoMessage> {
    @Override
    protected void encode(ChannelHandlerContext ctx, ProtoMessage msg, ByteBuf out) throws Exception {
        System.out.println("MyMessageEncoder方法被调用");
        out.writeInt(msg.getLen());
        out.writeBytes(msg.getMessage());

    }
}
