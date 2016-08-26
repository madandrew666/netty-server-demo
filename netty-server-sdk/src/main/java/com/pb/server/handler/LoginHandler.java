package com.pb.server.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pb.server.session.PBSession;
import com.pb.server.session.SessionManage;
import com.pb.server.util.ContexHolder;
import com.server.constant.PBCONSTANT;
import com.server.model.Message;

public class LoginHandler implements PBRequestHandler {
	private static Logger logger = LoggerFactory.getLogger(LoginHandler.class);

	@Override
	public Message process(PBSession session, Message msg) {
		Message reply = new Message();
		reply.setType(PBCONSTANT.LOGIN_REPLY_FLAG);
		reply.setParam("r_uid",msg.get("s_uid"));

		SessionManage sessionManager = (SessionManage) ContexHolder
				.getBean("sessionManager");

		if (msg.get("s_uid").equals("test1")
				&& (msg.get("pwd").equals("123"))) {
			reply.setParam("st",PBCONSTANT.SUCCESS);
			PBSession oldsession = sessionManager.get(msg.get("s_uid"));
			logger.info(msg.get("s_uid")+" login on "+session.getSession().remoteAddress());
			if (oldsession == null)
				sessionManager.add(msg.get("s_uid"), session);
			else if (oldsession == session) {
			} else {
				sendForceOffLine(oldsession);
				sessionManager.add(msg.get("s_uid"), session);
			}
		} else if (msg.get("s_uid").equals("test2")
				&& (msg.get("pwd").equals("123"))) {
			reply.setParam("st",PBCONSTANT.SUCCESS);
			sessionManager.add(msg.get("s_uid"), session);
			logger.info(msg.get("s_uid")+" login on "+session.getSession().remoteAddress());
		} else {
			reply.setParam("st","fl");
		}
        reply.setParam("s_uid",PBCONSTANT.SYSTEM);
		return reply;
	}

	public void sendForceOffLine(PBSession session) {
		logger.info("Force offline:" + session.getUid() + " at "
				+ session.getSession().remoteAddress() + " on "
				+ session.getDeviceId());
		Message force = new Message();
		session.getSession().writeAndFlush(force);

	}
}
