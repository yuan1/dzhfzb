package com.dzhf.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.dzhf.commonDao.BaseDaoImpl;
import com.dzhf.dao.TargetHistoryDao;
import com.dzhf.model.TargetHistory;

@Repository
public class TargetHistoryDaoImpl extends BaseDaoImpl<TargetHistory> implements TargetHistoryDao{

	@Override
	public void delTargetHistory(String month, int depttypeid) {
		Session session = getSession();
		SQLQuery sqlQuery = session
				.createSQLQuery("delete from TargetHistory where month=? and depttype_id=?");
		sqlQuery.setString(0, month);
		sqlQuery.setInteger(1, depttypeid);
		sqlQuery.executeUpdate();
	}

	@Override
	public List<String> getMonths() {
		Query query=getSession().createQuery("select DISTINCT(month) FROM TargetHistory order by month desc");
		return query.list();
	}

}
