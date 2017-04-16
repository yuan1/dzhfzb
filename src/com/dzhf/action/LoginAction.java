package com.dzhf.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dzhf.model.DataInfo;
import com.dzhf.model.Users;
import com.dzhf.service.CommonServ;
import com.dzhf.service.LoginServ;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author dzxy ������
 * @LoginAction.java
 * ����ʱ�䣺@2016��3��24�� @����8:40:43
 */

@Controller("login")
@Scope("prototype")
public class LoginAction extends ActionSupport {
	private static final long serialVersionUID = -3320297459816934680L;
	private Users users;
	@Resource
	private LoginServ loginServ;
	@Resource
	private CommonServ commonServ;
	

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public String login() {
		return SUCCESS;
	}

	/**
	 * ��¼��֤
	 * 
	 * @return
	 */
	public String submit() {
		Users users2 = loginServ.findUserLoginMess(users);
		if (users2 != null) {
			ActionContext.getContext().getSession().put("users", users2);
			ActionContext.getContext().getSession().put("webname","��Ἧ�ž�Ӫָ�����ϵͳ");
			ActionContext.getContext().getSession().put("usersid",users2.getId());
			ActionContext.getContext().getSession().put("topMenusList",commonServ.findTopMenus(users2.getId()));
			ActionContext.getContext().getSession().put("dept", users2.getDept());
			ActionContext.getContext().getSession().put("depttype", users2.getDept().getDept_type().getId());
			ActionContext.getContext().getSession().put("dept_id", users2.getDept().getId());
			ActionContext.getContext().getSession().put("role", users2.getRole().getId());
			return "ok";
		} else {
			ActionContext.getContext().put("info", "�û��������������˺��쳣");
			return "quit";
		}
	}

	/**
	 * �˳�
	 * 
	 * @return
	 */
	public String quit() {
		ActionContext.getContext().getSession().clear();
		return "quit";
	}
}
