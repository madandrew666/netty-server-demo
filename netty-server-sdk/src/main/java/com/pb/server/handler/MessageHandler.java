package com.pb.server.handler;

import com.pb.server.MessageFactory.OfflineMessageManager;
import com.pb.server.pusher.PBMessagePusher;
import com.server.model.Message;
import com.pb.server.session.PBSession;
import com.pb.server.session.SessionManage;
import com.pb.server.util.ContexHolder;
import com.server.constant.PBCONSTANT;

public class MessageHandler implements PBRequestHandler {
	@Override
	public Message process(PBSession session, Message msg) {
		SessionManage sessionManager = (SessionManage) ContexHolder
				.getBean("sessionManager");
		PBSession receiver_session = sessionManager.get(msg.get("r_uid"));
		Message reply = new Message();
		reply.setType(PBCONSTANT.MESSAGE_REPLY_FLAG);
		if (receiver_session != null && receiver_session.getSession().isActive()) {
			((PBMessagePusher)ContexHolder.getBean("messagePusher")).push(msg);
			reply.setParam("r_uid",msg.get("s_uid"));
			reply.setParam("st",PBCONSTANT.SUCCESS);
		} else {
			reply.setParam("st","fl");
			((OfflineMessageManager)ContexHolder.getBean("offlineMessageManager")).add(msg);
		}
		reply.setParam("s_uid",PBCONSTANT.SYSTEM);
		return reply;
	}
}
