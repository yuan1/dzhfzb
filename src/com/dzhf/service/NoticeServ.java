package com.dzhf.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dzhf.dao.NoticeDao;
import com.dzhf.model.Notice;
import com.dzhf.util.BasicUtil;

/**
 * @author dzxy ������
 * @NoticeServ.java
 * ����ʱ�䣺@2016��3��24�� @����8:37:35
 */
@Service
public class NoticeServ {
	@Resource
	private NoticeDao noticeDao;

	/**
	 * ���湫��
	 * 
	 * @param nt
	 * @return
	 */
	public String addNotice(Notice nt) {
		nt.setDate(BasicUtil.getCurrentTime());
		noticeDao.save(nt);
		return "ok";
	}
	/**
	 * ��ù�����Ŀ
	 * 
	 * @return
	 */
	public long getNoticeCount() {
		return noticeDao.getCount();
	}

	/**
	 * �����б�
	 * 
	 * @param pageIndex
	 * @param count
	 * @return
	 */
	public List<Notice> getNoticeList(int pageIndex, int count) {
//		return noticeDao.getAllOnPage(pageIndex, count);
		return noticeDao.getByParmeterOnPage(pageIndex, count, new String[]{}," date desc ");
	}

	/**
	 * �鿴����
	 * 
	 * @param nt_id
	 * @return
	 */
	public Notice noticeSee(int nt_id) {
		return noticeDao.getById(nt_id);
	}

	/**
	 * ɾ��
	 * 
	 * @param nt_id
	 * @return
	 */
	public String deleteNotice(int nt_id) {
		noticeDao.delete(nt_id);
		return "ok";
	}

	/**
	 * �޸�
	 * 
	 * @param notice
	 * @return
	 */
	public String updateNotice(Notice nt) {
		noticeDao.updateNotice(nt);
		return "ok";
	}
}
