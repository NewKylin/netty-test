package com.buck.timeserver;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @program: netty-test
 * @description: 测试
 * @author: buck
 * @create: 2020-03-06 22:35
 **/
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    private int counter;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf)msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        ctx.channel().eventLoop().schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("我是定时任务");
            }
        },1, TimeUnit.SECONDS);
        String body = new String(req,"UTF-8")
                .substring(0,req.length-System.getProperty("line.separator").length());

        System.out.println("The time server receive order : "+body+"; the counter is : "+ ++counter);
        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)?
                new Date(System.currentTimeMillis()).toString():"BAD ORDER";

        currentTime = currentTime + System.getProperty("line.separator");
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.writeAndFlush(resp).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
