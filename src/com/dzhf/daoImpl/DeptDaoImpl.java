package com.dzhf.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.dzhf.commonDao.BaseDaoImpl;
import com.dzhf.dao.DeptDao;
import com.dzhf.model.Dept;


/**
 * 
 * @author dzxy 惠晓东
 * @DeptDaoImpl.java
 * 创建时间：@2016年3月24日 @上午8:33:28
 */
@Repository
public class DeptDaoImpl extends BaseDaoImpl<Dept> implements DeptDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Dept> getNullDepts() {
		Session session = getSession();
		String hql="from Dept as dept where pid is null";
		Query query = session
				.createQuery(hql);
		return query.list();
	}

	@Override
	public void saveRoleDept(int role_id, int dept_id) {
		Session session = getSession();
		SQLQuery query = session
				.createSQLQuery("insert into role_dept (role_id,dept_id) values(?,?)");
		query.setInteger(0, role_id);
		query.setInteger(1, dept_id);
		query.executeUpdate();
	}

	@Override
	public void updateDept(Dept dept) {
		Session session = getSession();
		SQLQuery query = session
				.createSQLQuery("update dept set dept_name=?,sort=?,d_phone=?,duty_person=?,remark=?,d_address=?,deptType_id=? where id=?");
		query.setString(0, dept.getDept_name());
		query.setInteger(1, dept.getSort());
		query.setString(2, dept.getD_phone());
		query.setString(3, dept.getDuty_person());
		query.setString(4, dept.getRemark());
		query.setString(5, dept.getD_address());
		query.setInteger(6, dept.getDept_type().getId());
		query.setInteger(7, dept.getId());
		query.executeUpdate();
	}

	@Override
	public void deleteDept(int dept_id) {
		Session session = getSession();
		Query query = session
				.createQuery("update Dept as dept set status=0 where id=?");
		query.setInteger(0, dept_id);
		query.executeUpdate();
	}

	@Override
	public void moveDept(int d, int pid) {
		Session session = getSession();
		Query query = session
				.createQuery("update Dept as dept set pid=? where id=?");
		query.setInteger(0, pid);
		query.setInteger(1, d);
		query.executeUpdate();		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Dept> getDept(String time) {
		Session session = getSession();
		Query query = session
				.createQuery("from Dept where id not in(select DISTINCT(dept) FROM ReviewInfo a WHERE a.month=? and (a.status=1 or a.status=2))");
		query.setString(0,time);
		return query.list();
	}

	@Override
	public List<Dept> getDeptList(String status, String month) {
		Query query=getSession().createQuery("select DISTINCT(dept) FROM ReviewInfo a WHERE a.month=? and a.status=?");
		query.setString(0,month);
		query.setString(1,status);
		return query.list();
	}
}
