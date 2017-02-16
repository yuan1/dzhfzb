package com.dzhf.daoImpl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.dzhf.commonDao.BaseDaoImpl;
import com.dzhf.dao.UsersDao;
import com.dzhf.model.Users;

/**
 * 
 * @author dzxy 惠晓东
 * @UsersDaoImpl.java
 * 创建时间：@2016年3月24日 @上午8:35:31
 */
@Repository
public class UsersDaoImpl extends BaseDaoImpl<Users> implements UsersDao {

	@Override
	public void delUser(int id) {
		Session session = getSession();
		Query query = session
				.createQuery("update Users as users set del=0 where id=?");
		query.setInteger(0, id);
		query.executeUpdate();
	}

}
