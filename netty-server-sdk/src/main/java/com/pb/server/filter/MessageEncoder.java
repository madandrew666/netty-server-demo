package com.pb.server.filter;

import com.pb.server.util.PBProtocol;
import com.server.model.Message;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MessageEncoder extends MessageToByteEncoder<Message> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf outbuf)
            throws Exception {
        byte[] body = PBProtocol.Encode(msg.getEncode(), msg.getEnzip(), msg.getContent());
        int body_length = body.length;
        outbuf.writeInt(body_length);
        outbuf.writeByte(msg.getEncode());
        outbuf.writeByte(msg.getEnzip());
        outbuf.writeByte(msg.getType());
        byte low = (byte) msg.getMsg_id();
        byte high = (byte) (msg.getMsg_id() >> 8);
        outbuf.writeByte(high);
        outbuf.writeByte(low);
        outbuf.writeBytes(body);
        //System.out.println(outbuf.readableBytes());
        ctx.flush();
    }

}
