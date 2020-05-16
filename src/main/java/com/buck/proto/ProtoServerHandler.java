package com.buck.proto;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;


/**
 * @program: netty-test
 * @description: 自定义协议解决TCP粘包拆包问题
 * @author: buck
 * @create: 2020-03-06 22:35
 **/
public class ProtoServerHandler extends SimpleChannelInboundHandler<ProtoMessage> {

    private int counter;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ProtoMessage msg) throws Exception {

        System.out.println("服务端接收到消息："+ new String(msg.getMessage(),Charset.forName("utf-8"))+"；消息数量："+ (++counter));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("发生异常："+cause.getMessage());
    }
}
