package com.dzhf.dao;

import com.dzhf.commonDao.BaseDao;
import com.dzhf.model.Notice;

/**
 * 
 * @author dzxy ������
 * @NoticeDao.java
 * ����ʱ�䣺@2016��3��24�� @����8:31:35
 */
public interface NoticeDao extends BaseDao<Notice>{
	void updateNotice(Notice notice);
}
