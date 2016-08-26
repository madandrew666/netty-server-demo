package com.pb.server.handler;

import java.util.Map;

import com.server.constant.PBCONSTANT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.server.model.Message;
import com.pb.server.session.PBSession;
import com.pb.server.session.SessionManage;
import com.pb.server.util.ContexHolder;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.ChannelHandler.Sharable;

public class PBIoHandler extends SimpleChannelInboundHandler<Message> {
    private static Logger logger = LoggerFactory.getLogger(PBIoHandler.class);
    private Map<String, PBRequestHandler> handlers;

    private String uid = null;

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Message msg)
            throws Exception {
        logger.info("Received from " + ctx.channel().remoteAddress() + " "
                + msg.toString());
        if (msg != null) {
            PBSession pbSession = new PBSession(ctx.channel());
            pbSession.setUid(uid);
            Message reply = null;
            switch (msg.getType()) {
                case PBCONSTANT.LOGIN_FLAG:
                    reply = handlers.get(PBCONSTANT.LOGIN).process(pbSession, msg);
                    if(reply.get("st").equals(PBCONSTANT.SUCCESS))    uid = reply.get("r_uid");
                    break;
                case PBCONSTANT.MESSAGE_FLAG:
                    reply = handlers.get(PBCONSTANT.MESSAGE).process(pbSession, msg);
                    break;
                case PBCONSTANT.ACK_FLAG:
                    reply = handlers.get(PBCONSTANT.ACK).process(pbSession, msg);
                    break;
                case PBCONSTANT.LOGOUT_FLAG:
                    reply = handlers.get(PBCONSTANT.LOGOUT).process(pbSession,msg);
                    break;
                default:
            }
            pbSession.write(reply);
        }
    }

    public void setHandlers(Map<String, PBRequestHandler> handlers) {
        this.handlers = handlers;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("Connected from:" + ctx.channel().remoteAddress());
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("unregistered");
        PBSession pbSession = new PBSession(ctx.channel());
        pbSession.setUid(uid);
        handlers.get(PBCONSTANT.LOGOUT).process(pbSession,null);
    }


}
