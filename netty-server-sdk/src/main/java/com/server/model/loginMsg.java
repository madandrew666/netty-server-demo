package com.server.model;

import java.io.Serializable;

public class loginMsg extends Message implements Serializable {
	private static final long serialVersionUID = 1L;
	private String deviceId;// 客户端id,用户id+“@”+终端版本
	private String channel;// 终端设备类型
	private String deviceModel;// 终端设备型号
	private String clientVersion;// 终端应用版本
	private String systemVersion;// 终端系统版本
	private Double longitude;// 经度
	private Double latitude;// 维度
	private String location;// 位置
	private int status;// 状态

	public loginMsg() {
		this.longitude = 0D;
		this.latitude = 0D;
		this.location = "default";
		this.status = 1;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
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

}
