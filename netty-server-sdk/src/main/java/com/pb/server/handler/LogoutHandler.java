package com.pb.server.handler;

import com.pb.server.session.PBSession;
import com.pb.server.session.SessionManage;
import com.pb.server.util.ContexHolder;
import com.server.constant.PBCONSTANT;
import com.server.model.Message;

public class LogoutHandler implements PBRequestHandler {

    @Override
    public Message process(PBSession session, Message msg) {
        SessionManage sessionManager = (SessionManage) ContexHolder.getBean("sessionManager");
        session.close();
        sessionManager.remove(session.getUid());
		Message reply = new Message();
        reply.setType(PBCONSTANT.LOGOUT_REPLY_FLAG);
        reply.setParam("r_uid",session.getUid());
        reply.setParam("st",PBCONSTANT.SUCCESS);
        reply.setParam("s_uid",PBCONSTANT.SYSTEM);
        return reply;
    }

}
