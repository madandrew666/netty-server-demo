package com.pb.server.session;

import io.netty.channel.Channel;

public class PBSession {
	private Channel session;// 用户与服务器连接的session
	private String sid;// session在服务器中的id
	private String deviceId;// 客户端id,用户id+“@”+终端版本
	private String uid;// 用户id
	private Long loggin_time;// 登录时间
	private String channel;// 终端设备类型
	private String deviceModel;// 终端设备型号
	private String clientVersion;// 终端应用版本
	private String systemVersion;// 终端系统版本
	private Double longitude;// 经度
	private Double latitude;// 维度
	private String location;// 位置
	private int status;// 状态

	public PBSession(Channel session) {
		this.session = session;
		this.sid = session.id().asLongText();
	}

	public PBSession() {
	}

	public Channel getSession() {
		return session;
	}

	public void setSession(Channel session) {
		this.session = session;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Long getLoggin_time() {
		return loggin_time;
	}

	public void setLoggin_time(Long loggin_time) {
		this.loggin_time = loggin_time;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	public String getClientVersion() {
		return clientVersion;
	}

	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
	}

	public String getSystemVersion() {
		return systemVersion;
	}

	public void setSystemVersion(String systemVersion) {
		this.systemVersion = systemVersion;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	public void close(){
		if(session!=null){
			session.disconnect();
			session.close();
		}
	}

	public boolean write(Object msg) {
		if (session != null && msg != null) {
			return session.writeAndFlush(msg).awaitUninterruptibly(5000);
		}

		return false;
	}

}
