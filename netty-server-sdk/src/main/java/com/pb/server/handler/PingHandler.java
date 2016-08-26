package com.pb.server.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pb.server.session.PBSession;
import com.server.constant.PBCONSTANT;
import com.server.model.Message;

public class PingHandler implements PBRequestHandler {

	private static Logger logger = LoggerFactory.getLogger(PingHandler.class);

	@Override
	public Message process(PBSession session, Message msg) {
		logger.info("Ping from:" + session.getSession().remoteAddress());
		Message reply = new Message();
		/*reply.setContent(PBCONSTANT.PING);
		reply.setReceiver_uid(msg.getSender_uid());
		reply.setSender_uid(PBCONSTANT.SYSTEM);
		reply.setTitle(PBCONSTANT.PING);
		reply.setType(PBCONSTANT.PING);
		reply.setTime(System.currentTimeMillis());*/
		return reply;
	}

}
