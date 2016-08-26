package com.pb.server.session;

import io.netty.channel.Channel;

import java.util.List;

public interface SessionManage {
	/**
	 * 
	 * @param uid
	 *            用户的id对应一个session
	 * @return 该uid用户与服务器连接的session封装
	 */
	public PBSession get(String uid);

	/**
	 * 
	 * @param uid
	 *            用户的id
	 * @param session
	 *            该用户与服务器连接的session封装
	 */
	public void add(String uid, PBSession session);

	/**
	 * 
	 * @return 全部用户与服务器连接的session
	 */
	public List<PBSession> getAllSession();

	/**
	 * 
	 * @param uid
	 *            用户id
	 * @param session
	 *            该用户与服务器连接的session
	 */
	public void update(String uid, PBSession session);

	/**
	 * 
	 * @param uid
	 *            用户id
	 */
	public void remove(String uid);

	/**
	 * 
	 * @param uid
	 *            用户id
	 * @param state
	 *            用户的状态
	 */
	public void setState(String uid, int state);

	/**
	 * 
	 * @param asLongText session的id
	 */
	//public void removebysid(String asLongText);
}
