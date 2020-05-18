package com.buck.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ByteBuf buffer = Unpooled.buffer(3);
        buffer.writeLong(123L);
        ByteBuf byteBuf2 = buffer;
        buffer.release();
        System.out.println(byteBuf2.readByte());
    }
}
