package com.pb.server.handler;

import com.pb.server.session.PBSession;
import com.pb.server.MessageFactory.MessageHolder;
import com.server.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by piecebook on 2016/8/8.
 */
public class ACKHandler implements PBRequestHandler{
    private static Logger logger = LoggerFactory.getLogger(ACKHandler.class);

    @Override
    public Message process(PBSession session, Message msg) {
        logger.info("ACK:" + msg.toString());
        MessageHolder.rec_messages.add(msg.get("msg_key"));
        return null;
    }
}
