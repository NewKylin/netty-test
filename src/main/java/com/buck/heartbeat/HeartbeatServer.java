package com.buck.heartbeat;

import com.buck.timeserver.TimeServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @program: netty-test
 * @description: 心跳检测服务端
 * @author: buck
 * @create: 2020-03-16 22:11
 **/
public class HeartbeatServer {

    public static void main(String[] args) throws Exception {

        //配置服务端的NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO));
            b.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    /**
                     * IdleStateHandler本质上也是将任务塞进EventLoop里面的任务丢列中串行调度执行
                     */
                    ch.pipeline().addLast(new IdleStateHandler(3,5,7, TimeUnit.SECONDS));
                    ch.pipeline().addLast(new MyServerHandler());
                }
            });
            ChannelFuture sync = b.bind(8080).sync();
            sync.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

}
