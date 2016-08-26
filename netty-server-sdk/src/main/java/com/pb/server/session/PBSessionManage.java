package com.pb.server.session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PBSessionManage implements SessionManage {

	private static Map<String, PBSession> sessions = new HashMap<String, PBSession>();

	@Override
	public PBSession get(String uid) {
		return sessions.get(uid);
	}

	@Override
	public void add(String uid, PBSession session) {
		if (session != null) {
			sessions.put(uid, session);
		}
	}

	@Override
	public List<PBSession> getAllSession() {
		List<PBSession> list = new ArrayList<PBSession>();
		list.addAll(sessions.values());
		return list;
	}

	@Override
	public void update(String uid, PBSession session) {
		if (session != null) {
			sessions.put(uid, session);
		}
	}

	@Override
	public void remove(String uid) {
		sessions.remove(uid);
	}

	@Override
	public void setState(String uid, int state) {
		sessions.get(uid).setStatus(state);
	}

}
