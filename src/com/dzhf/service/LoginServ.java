package com.dzhf.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dzhf.dao.PropertyDao;
import com.dzhf.dao.UsersDao;
import com.dzhf.model.Users;
import com.dzhf.util.BasicUtil;

/**
 * @author xuwei
 * @version 2015-8-13 上午10:21:08
 */
@Service
public class LoginServ {
	@Resource
	private UsersDao usersDao;
	@Resource
	private PropertyDao propertyDao;

	/**
	 * 登录验证
	 * 
	 * @param users
	 * @return
	 */
	public Users findUserLoginMess(Users users) {
		List<Users> list = usersDao.getByParmeter(new String[] { "username",
				"password", "del" }, " id asc", users.getUsername().trim(),
				BasicUtil.md5(users.getPassword().trim()), "1");
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 获得手续费
	 * 
	 * @return
	 */
	public String getCharge() {
		return propertyDao.findAll(new String[] { "type" }, "id", "charge")
				.get(0).getP_name();
	}
}
