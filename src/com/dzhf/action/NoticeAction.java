package com.dzhf.action;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dzhf.model.Notice;
import com.dzhf.service.NoticeServ;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
/**
 * @author dzxy ������
 * @NoticeAction.java
 * ����ʱ�䣺@2016��3��24�� @����8:41:27
 */
@Controller("notice")
@Scope("prototype")
public class NoticeAction extends ActionSupport {
	private static final long serialVersionUID = -362537665759184202L;
	private ServletResponse response = ServletActionContext.getResponse();
	@Resource
	private NoticeServ noticeServ;
	private int p; // �˵�����
	private Notice nt;
	private int nt_id; // ����ΨһID
	private int pageIndex = 0;// ��ǰҳ��

	/**
	 * addNoticeView
	 * 
	 * @return
	 */
	public String addNotice() {
		return "addNotice";
	}

	/**
	 * addNoticeDo
	 * 
	 * @throws IOException
	 */
	public void addNoticeDo() throws IOException {
		if ("ok".equals(noticeServ.addNotice(nt))) {
			response.getWriter().print(1);
		}
	}

	/**
	 * noticeList
	 * 
	 * �����б�
	 * 
	 * @return
	 */
	public String noticeList() {
		ActionContext.getContext().put("counts",(int) Math.ceil(noticeServ.getNoticeCount()));
		ActionContext.getContext().put("allPage",(int) Math.floor((int) Math.ceil(noticeServ.getNoticeCount()) / 10));
		ActionContext.getContext().put("noticeList",noticeServ.getNoticeList(pageIndex, 10));
		return "noticeList";
	}

	/**
	 * watchNotice
	 * 
	 * �鿴����
	 * 
	 * @return
	 */
	public String showNotice() {
		ActionContext.getContext().put("noticeMessage",noticeServ.noticeSee(nt_id));
		return "showNotice";
	}

	/**
	 * deleteNotice
	 * 
	 * ɾ���������
	 * 
	 * @throws IOException
	 */
	public void deleteNotice() throws IOException {
		if ("ok".equals(noticeServ.deleteNotice(nt_id))) {
			response.getWriter().print(1);
		}
	}

	/**
	 * updateNoticeShow
	 * 
	 * �޸Ĺ���ҳ��
	 * 
	 * @return
	 */
	public String updateNoticeShow() {
		ActionContext.getContext().put("updateNoticeMessage",noticeServ.noticeSee(nt_id));
		return "updateNoticeShow";
	}

	/**
	 * updateNoticeDo
	 * 
	 * 
	 * �޸Ĺ������
	 * 
	 * @throws IOException
	 */
	public void updateNoticeDo() throws IOException {
		if ("ok".equals(noticeServ.updateNotice(nt))) {
			response.getWriter().print(1);
		}
	}

	public int getP() {
		return p;
	}

	public void setP(int p) {
		this.p = p;
	}

	public Notice getNt() {
		return nt;
	}

	public void setNt(Notice nt) {
		this.nt = nt;
	}

	public int getNt_id() {
		return nt_id;
	}

	public void setNt_id(int nt_id) {
		this.nt_id = nt_id;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

}
