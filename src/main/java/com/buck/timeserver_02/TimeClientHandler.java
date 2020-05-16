package com.buck.timeserver_02;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.logging.Logger;

/**
 * @program: netty-test
 * @description: dd
 * @author: buck
 * @create: 2020-03-06 23:00
 **/
public class TimeClientHandler extends ChannelInboundHandlerAdapter {
    private static final Logger logger = Logger.getLogger(TimeClientHandler.class.getName());


    private int counter;

    private byte[] req;

    public TimeClientHandler(){

        req = ("QUERY TIME ORDER" + System.getProperty("line.separator")).getBytes();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf message;
        for (int i=0;i<1;i++){
            message = Unpooled.buffer(req.length);
            message.writeBytes(req);
            ctx.writeAndFlush(message);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        System.out.println("now is : "+body+"; the counter is : "+ ++counter);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.warning("Unexpected exception from downstream : "+cause.getMessage());
        ctx.close();
    }
}
