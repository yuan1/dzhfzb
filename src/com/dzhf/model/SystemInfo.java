package com.dzhf.model;

/**
 * ϵͳ�Ļ�������
 * @author Dezhou University ������
 * @version ����ʱ�䣺2015��11��19�� ����10:46:26
 */
public class SystemInfo {
	
	//ϵͳ����
	private String osname;
	//��������ַ
	private String serIP;
	//������ַ
	private String hostIP;
	//ϵͳ·��
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
