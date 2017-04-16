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
 * @author dzxy 惠晓东
 * @LoginAction.java
 * 创建时间：@2016年3月24日 @上午8:40:43
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
	 * 登录验证
	 * 
	 * @return
	 */
	public String submit() {
		Users users2 = loginServ.findUserLoginMess(users);
		if (users2 != null) {
			ActionContext.getContext().getSession().put("users", users2);
			ActionContext.getContext().getSession().put("webname","恒丰集团经营指标分析系统");
			ActionContext.getContext().getSession().put("usersid",users2.getId());
			ActionContext.getContext().getSession().put("topMenusList",commonServ.findTopMenus(users2.getId()));
			ActionContext.getContext().getSession().put("dept", users2.getDept());
			ActionContext.getContext().getSession().put("depttype", users2.getDept().getDept_type().getId());
			ActionContext.getContext().getSession().put("dept_id", users2.getDept().getId());
			ActionContext.getContext().getSession().put("role", users2.getRole().getId());
			return "ok";
		} else {
			ActionContext.getContext().put("info", "用户名或密码错误或账号异常");
			return "quit";
		}
	}

	/**
	 * 退出
	 * 
	 * @return
	 */
	public String quit() {
		ActionContext.getContext().getSession().clear();
		return "quit";
	}
}
