package com.dzhf.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dzhf.dao.NoticeDao;
import com.dzhf.model.Notice;
import com.dzhf.util.BasicUtil;

/**
 * @author dzxy 惠晓东
 * @NoticeServ.java
 * 创建时间：@2016年3月24日 @上午8:37:35
 */
@Service
public class NoticeServ {
	@Resource
	private NoticeDao noticeDao;

	/**
	 * 保存公告
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
	 * 获得公告数目
	 * 
	 * @return
	 */
	public long getNoticeCount() {
		return noticeDao.getCount();
	}

	/**
	 * 公告列表
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
	 * 查看公告
	 * 
	 * @param nt_id
	 * @return
	 */
	public Notice noticeSee(int nt_id) {
		return noticeDao.getById(nt_id);
	}

	/**
	 * 删除
	 * 
	 * @param nt_id
	 * @return
	 */
	public String deleteNotice(int nt_id) {
		noticeDao.delete(nt_id);
		return "ok";
	}

	/**
	 * 修改
	 * 
	 * @param notice
	 * @return
	 */
	public String updateNotice(Notice nt) {
		noticeDao.updateNotice(nt);
		return "ok";
	}
}
