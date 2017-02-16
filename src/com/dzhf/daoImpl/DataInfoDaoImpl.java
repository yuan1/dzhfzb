package com.dzhf.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.dzhf.commonDao.BaseDaoImpl;
import com.dzhf.dao.DataInfoDao;
import com.dzhf.model.DataInfo;

@Repository
public class DataInfoDaoImpl extends BaseDaoImpl<DataInfo> implements DataInfoDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getMonth(int deptid) {
		Query query=getSession().createQuery("select DISTINCT(month) FROM DataInfo WHERE dept_id=?");
		query.setInteger(0,deptid);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllMonth() {
		Query query=getSession().createQuery("select DISTINCT(month) FROM DataInfo");
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DataInfo> getDataInfo(int dept_id, String time) {
		String hql="FROM DataInfo where month=? and dept_id=?";
		Query query=getSession().createQuery(hql);
		query.setString(0,time);
		query.setInteger(1,dept_id);
		return query.list();
	}

	@Override
	public List<DataInfo> getDataSum(String month) {
		String hql="FROM DataInfo where month=? and targets.id in ('52','54','91','117','57','59','60')";
		Query query=getSession().createQuery(hql);
		query.setString(0,month);
		return query.list();
	}
	
	@Override
	public List<DataInfo> getDataSum(int deptid,String stime,String etime) {
		String hql="FROM DataInfo where dept.id=? and month<=? and month>=?";
		Query query=getSession().createQuery(hql);
		query.setInteger(0,deptid);
		query.setString(1,etime);
		query.setString(2,stime);
		return query.list();
	}
	
	public List<String> getDataSumMonth(int deptid,String stime,String etime){
		String hql="select DISTINCT(month) FROM DataInfo where dept.id=? and month<=? and month>=? ORDER BY month ASC";
		Query query=getSession().createQuery(hql);
		query.setInteger(0,deptid);
		query.setString(1,etime);
		query.setString(2,stime);
		return query.list();
	}
}
