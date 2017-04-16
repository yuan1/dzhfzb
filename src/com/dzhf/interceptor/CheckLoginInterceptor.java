package com.dzhf.interceptor;

import java.util.Map;

import javax.annotation.Resource;

import com.dzhf.dao.UsersDao;
import com.dzhf.model.Menus;
import com.dzhf.util.BasicUtil;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * @author dzxy 惠晓东
 * @CheckLoginInterceptor.java
 * 创建时间：@2016年3月24日 @上午8:36:05
 */
public class CheckLoginInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 3366375191291074925L;
	public static final String LOGIN_KEY = "LOGIN";
	public static final String LOGIN_PAGE = "global.quit";
	@Resource
	private UsersDao usersDao;
	

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		String url = actionInvocation.getInvocationContext().getName();
		Map<?, ?> session = actionInvocation.getInvocationContext().getSession();
		String allow = "Res_tx,Res_tx1,Login_login,Login_submit,error,Sym_findDeptTree,Notice_noticeList,Sym_checkIdCard,Sym_findDeptTreeForScope,Sym_checkUserName,Sym_getRoleTree";
		if (allow.contains(url)) {
			if (url.equals("Notice_noticeList")) {
				if (session.isEmpty()) {
					return "quit";
				}
			}
			return actionInvocation.invoke();
		} else {
			if (session.isEmpty()) {
				return "quit";
			} else {
				int user_id = BasicUtil.getUsers().getId();
				boolean flag = false;
				for (Menus menus : usersDao.getById(user_id).getRole().getMenus()) {
					if (menus.getUrl() != null) {
						if (menus.getUrl().contains(url)) {
							flag = true;
							break;
						}
					}
				}
				if (flag) {
					return actionInvocation.invoke();
				} else {
					return "error";
				}
			}
		}

	}

}