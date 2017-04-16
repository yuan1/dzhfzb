package com.dzhf.model;

/**
 * 系统的基本配置
 * @author Dezhou University 惠晓东
 * @version 创建时间：2015年11月19日 下午10:46:26
 */
public class SystemInfo {
	
	//系统名字
	private String osname;
	//服务器地址
	private String serIP;
	//主机地址
	private String hostIP;
	//系统路径
	private String appDir;
	
	public String getOsname() {
		return osname;
	}
	public void setOsname(String osname) {
		this.osname = osname;
	}
	public String getSerIP() {
		return serIP;
	}
	public void setSerIP(String serIP) {
		this.serIP = serIP;
	}
	public String getHostIP() {
		return hostIP;
	}
	public void setHostIP(String hostIP) {
		this.hostIP = hostIP;
	}
	public String getAppDir() {
		return appDir;
	}
	public void setAppDir(String appDir) {
		this.appDir = appDir;
	}
}
