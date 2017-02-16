package com.dzhf.dao;

import com.dzhf.commonDao.BaseDao;
import com.dzhf.model.Users;

/**   
 * @author xuwei
 * @version 2015-8-13 ионГ10:21:54 
 */
public interface UsersDao extends BaseDao<Users>{
	void delUser(int id);
}
