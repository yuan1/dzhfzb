package com.dzhf.dao;

import com.dzhf.commonDao.BaseDao;
import com.dzhf.model.Notice;

/**
 * 
 * @author dzxy 惠晓东
 * @NoticeDao.java
 * 创建时间：@2016年3月24日 @上午8:31:35
 */
public interface NoticeDao extends BaseDao<Notice>{
	void updateNotice(Notice notice);
}
