package com.buck.proto;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.logging.Logger;

/**
 * @program: netty-test
 * @description: dd
 * @author: buck
 * @create: 2020-03-06 23:00
 **/
public class MyClientHandler extends ChannelInboundHandlerAdapter {
    private static final Logger logger = Logger.getLogger(MyClientHandler.class.getName());

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        for (int i=0;i<5;i++){
            String msg = "今天煲汤";
            ProtoMessage protoMessage = new ProtoMessage();
            protoMessage.setMessage(msg.getBytes(Charset.forName("utf-8")));
            protoMessage.setLen(msg.getBytes(Charset.forName("utf-8")).length);
            System.out.println("发送消息："+msg);
            ctx.writeAndFlush(protoMessage);
        }
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.warning("Unexpected exception from downstream : "+cause.getMessage());
        ctx.close();
    }
}
