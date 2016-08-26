package com.pb.server.handler;

import com.server.model.Message;
import com.pb.server.session.PBSession;

public interface PBRequestHandler {

	public abstract Message process(PBSession session, Message msg);

}