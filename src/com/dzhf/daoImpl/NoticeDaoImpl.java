package com.dzhf.daoImpl;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.dzhf.commonDao.BaseDaoImpl;
import com.dzhf.dao.NoticeDao;
import com.dzhf.model.Notice;

/**
 * 
 * @author dzxy 惠晓东
 * @NoticeDaoImpl.java
 * 创建时间：@2016年3月24日 @上午8:34:20
 */
@Repository
public class NoticeDaoImpl extends BaseDaoImpl<Notice> implements NoticeDao {

	@Override
	public void updateNotice(Notice notice) {
		Session session = getSession();
		SQLQuery query = session
				.createSQLQuery("update notice set title=?,content=? where id=?");
		query.setString(0, notice.getTitle());
		query.setString(1, notice.getContent());
		query.setInteger(2, notice.getId());
		query.executeUpdate();
	}

}
